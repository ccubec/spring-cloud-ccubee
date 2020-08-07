package com.example.producer.execl.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ccubee
 * @since 20-3-8 16:07
 */
@Slf4j
public class ExcelNewListener<T> extends AnalysisEventListener<T> {


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    List<T> list = new ArrayList<>();
    @Override
    public void invoke(T object, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(object));
        list.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("read {} rows %n", list.size());
    }

    public List<T> getRows() {
        return list;
    }
}
