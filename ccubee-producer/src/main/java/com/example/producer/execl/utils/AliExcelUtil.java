package com.example.producer.execl.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import com.example.producer.execl.listener.ExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.FileMagic;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zongkun
 * @since 2019-5-27 17:40
 */
@Slf4j
public final class AliExcelUtil {
    public static void main(String[] args) {
        writeExcelTest();
    }

    /**
     * 测试方法
     */
    public static void writeExcelTest() {
        String sheetName = "测试easyExcel";
        String worksheetTitle = "订单列表";
        String[] names = {"字符串", "long型", "int型:10000", "double型", "Float型", "Date型", "BigDecimal型", "Short型"};
        List<List<Object>> resultList = createTestListObject();
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("E://aliExcel.xls"));
            writeExcel(out, sheetName, worksheetTitle, names, resultList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 输出excle文件
     *
     * @param out            输出流
     * @param sheetName      sheet名称，可以为null
     * @param worksheetTitle sheet1的头部标题，可以为null
     * @param names          每列的名称
     * @param resultList     输出的数据结果集合
     */
    public static void writeExcel(OutputStream out, String sheetName, String worksheetTitle, String[] names, List<List<Object>> resultList) {
        try {
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 3);
            if (StringUtils.isBlank(sheetName)) {
                sheetName = "sheet1";
            }
            sheet1.setSheetName(sheetName);
            createWidth(sheet1, names);
            sheet1.setHead(createHead(worksheetTitle, names));
            //or 设置自适应宽度
            //sheet1.setAutoWidth(Boolean.TRUE);
            writer.write1(resultList, sheet1);
            //关闭资源
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表头
     *
     * @param worksheetTitle 表头名称
     * @param names          每列名称 取;分割的第一个
     * @return
     */
    public static List<List<String>> createHead(String worksheetTitle, String[] names) {
        List<List<String>> head = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            List<String> headCoulumn = new ArrayList<>();
            if (StringUtils.isNotBlank(worksheetTitle)) {
                headCoulumn.add(worksheetTitle);//第一行
                headCoulumn.add(worksheetTitle);//第二行
            }
            if (names[i].indexOf(":") > -1) {
                headCoulumn.add(names[i].split(":")[0]);//第三行
            } else {
                headCoulumn.add(names[i]);//第三行
            }
            head.add(headCoulumn);
        }
        return head;
    }

    /**
     * 创建表头
     *
     * @param sheet1 sheet对象
     * @param names  每列名称 名称中如果带有:，则将:后的第一个作为每列的宽度
     */
    public static void createWidth(Sheet sheet1, String[] names) {
        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        for (int i = 0; i < names.length; i++) {
            if (names[i].indexOf(":") > -1) {
                columnWidth.put(i, Integer.parseInt(names[i].split(":")[1]));
            }
        }
        if (columnWidth.size() > 0) {
            sheet1.setColumnWidthMap(columnWidth);
        }
    }

    public static List<List<Object>> createTestListObject() {
        List<List<Object>> object = new ArrayList<List<Object>>();
        for (int i = 0; i < 1000; i++) {
            List<Object> da = new ArrayList<Object>();
            da.add("字符串" + i);
            da.add(Long.valueOf(187837834l + i));
            da.add(Integer.valueOf(2233 + i));
            da.add(Double.valueOf(2233.00 + i));
            da.add(Float.valueOf(2233.0f + i));
            da.add(new Date());
            da.add(new BigDecimal("3434343433554545" + i));
            da.add(Short.valueOf((short) i));
            object.add(da);
        }
        return object;
    }

    /**
     * 从Excel中读取文件，读取的文件是一个DTO类，该类必须继承BaseRowModel
     * 具体实例参考 ： MemberMarketDto.java
     * 参考：https://github.com/alibaba/easyexcel
     * 字符流必须支持标记，FileInputStream 不支持标记，可以使用BufferedInputStream 代替
     * BufferedInputStream bis = new BufferedInputStream(new FileInputStream(...));
     */
    public static <T extends BaseRowModel> List<T> readExcel(final InputStream inputStream, final Class<? extends BaseRowModel> clazz) {
        if (null == inputStream) {
            throw new NullPointerException("the inputStream is null!");
        }
        ExcelListener<T> listener = new ExcelListener<>();
        // 这里因为EasyExcel-1.1.1版本的bug，所以需要选用下面这个标记已经过期的版本
        ExcelReader reader = new ExcelReader(inputStream, valueOf(inputStream), null, listener);
        // ExcelReader reader = new ExcelReader(inputStream, inputStream, listener);
        reader.read(new Sheet(1, 1, clazz));
        return listener.getRows();
    }


    public static void writeExcel(final File file, List<? extends BaseRowModel> list) {
        try (OutputStream out = new FileOutputStream(file)) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet,  有模型映射关系
            Class<? extends BaseRowModel> t = list.get(0).getClass();
            Sheet sheet = new Sheet(1, 0, t);
            writer.write(list, sheet);
            writer.finish();
        } catch (IOException e) {
            log.warn("fail to write to excel file: file[{}]", file.getName(), e);
        }
    }

    /**
     * 根据输入流，判断为xls还是xlsx，该方法原本存在于easyexcel 1.1.0 的ExcelTypeEnum中。
     */
    public static ExcelTypeEnum valueOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (FileMagic.OLE2.equals(fileMagic)) {
                return ExcelTypeEnum.XLS;
            }
            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }
            throw new IllegalArgumentException("excelTypeEnum can not null");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Author fucc
     * @Description 导出excel 支持一张表导出多个sheet
     * @Param OutputStream 输出流
     * Map<String, List>  sheetName和每个sheet的数据
     * ExcelTypeEnum 要导出的excel的类型 有ExcelTypeEnum.xls 和有ExcelTypeEnum.xlsx
     * @Date 上午12:16 2019/5/23
     */
    public static void createExcelStreamMutilByEaysExcel(HttpServletResponse response, Map<String, List<? extends BaseRowModel>> SheetNameAndDateList, ExcelTypeEnum type) throws UnsupportedEncodingException {
        try {
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + "default" + type.getValue());
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, type, true);
            setSheet(SheetNameAndDateList, writer);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author fucc
     * @Description //setSheet数据
     * @Date 上午12:39 2019/5/23
     */
    private static void setSheet(Map<String, List<? extends BaseRowModel>> SheetNameAndDateList, ExcelWriter writer) {
        int sheetNum = 1;
        for (Map.Entry<String, List<? extends BaseRowModel>> stringListEntry : SheetNameAndDateList.entrySet()) {
            Sheet sheet = new Sheet(sheetNum, 0, stringListEntry.getValue().get(0).getClass());
            sheet.setSheetName(stringListEntry.getKey());
            writer.write(stringListEntry.getValue(), sheet);
            sheetNum++;
        }
    }
}
