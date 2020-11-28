package com.hz.disruptor;

/**
 * @author zehua
 * @date 2020/11/27 17:18
 */
public class IntegerEvent {
    private Integer intNum;

    public IntegerEvent() {
    }

    public IntegerEvent(int intNum) {
        this.intNum = intNum;
    }

    public Integer getIntNum() {
        return intNum;
    }

    public void setIntNum(Integer intNum) {
        this.intNum = intNum;
    }

    @Override
    public String toString() {
        return "IntegerEvent{" +
                "intNum=" + intNum +
                '}';
    }
}
