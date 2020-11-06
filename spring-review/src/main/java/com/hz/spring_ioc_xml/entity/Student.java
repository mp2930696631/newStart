package com.hz.spring_ioc_xml.entity;

import java.util.*;

/**
 * @Auther zehua
 * @Date 2020/11/6 5:31
 */
public class Student extends Person {
    private int[] scores;
    private List<String> list;
    private Map<String, Object> map;
    private Set<Object> set;
    private Properties properties;

    public Student() {
    }

    public Student(String name, int age, Address address, int[] scores, List<String> list, Map<String, Object> map, Set<Object> set) {
        super(name, age, address);
        this.scores = scores;
        this.list = list;
        this.map = map;
        this.set = set;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Student{" +
                "scores=" + Arrays.toString(scores) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", properties=" + properties +
                ", address=" + address +
                '}';
    }
}
