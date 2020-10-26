package com.zehua.testEnum;

public enum LearnTarget {
    U1("初级"), U2("中级"), U3("高级");

    private String target;

    LearnTarget(String target) {
        this.target = target;
    }

    public void print() {
        System.out.println(this.target);
    }

}
