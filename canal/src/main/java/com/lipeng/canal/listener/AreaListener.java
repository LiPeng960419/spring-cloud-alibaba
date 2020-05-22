package com.lipeng.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/22 14:23
 */
@CanalEventListener //声明当前的类是canal的监听类
@Slf4j
public class AreaListener {

    @ListenPoint(schema = "demo0", table = "tb_area")
    public void areaListener(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        log.info("tb_area数据发生改变,eventType:" + eventType.name());
        //获取改变之前的数据
        rowData.getBeforeColumnsList()
                .forEach((c) -> log.info("改变前的数据:" + c.getName() + "::" + c.getValue()));
        //获取改变之后的数据
        rowData.getAfterColumnsList()
                .forEach((c) -> log.info("改变之后的数据:" + c.getName() + "::" + c.getValue()));
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            log.info("最新的数据:" + column.getValue());
        }
    }

}