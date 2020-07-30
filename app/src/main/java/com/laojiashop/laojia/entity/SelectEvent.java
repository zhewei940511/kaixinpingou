package com.laojiashop.laojia.entity;

/**
 * 选择类型
 */
public class SelectEvent {
    public final String event;
    public final  String eventway;
    public static SelectEvent getInstance(String event,String eventway) {
        return new SelectEvent(event,eventway);
    }

    private SelectEvent(String event,String eventway) {
        this.event = event;
        this.eventway=eventway;
    }
}
