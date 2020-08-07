package com.example.producer.execl.dao;

import com.example.producer.execl.vo.DemoData;

import java.util.List;

/**
 * @author ccubee
 * @since 20-2-28 9:02
 */

public class DemoDAO {
    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
