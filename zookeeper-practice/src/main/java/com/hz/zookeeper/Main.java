package com.hz.zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 15:10
 */
public class Main {
    public static Person person = new Person();

    public static List list = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        /*person.setName("zehua");

        new Thread(()->{
            while (person.getName().equals("zehua")){

            }
        }).start();

        Thread.sleep(1000);

        person.setName("huazai");*/

        /*new Thread(() -> {
            while (list.isEmpty()) {

            }
        }).start();

        Thread.sleep(1000);
        list.add(10);*/

        MyThread myThread = new MyThread();
        new Thread(myThread).start();

        while (myThread.gettList().isEmpty()){
            synchronized (person){

            }
        }
    }

    public static class MyThread implements Runnable {
        private  List tList = new ArrayList();

        public List gettList() {
            return tList;
        }

        public void settList(List tList) {
            this.tList = tList;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           gettList().add(1);



        }
    }

    public static class Person {
        private String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
