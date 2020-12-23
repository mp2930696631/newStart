package com.hz.zookeeper.distributedLock;


/**
 * @author zehua
 * @date 2020/12/23 15:05
 */
public class Main {
    private static ThreadLocal<ReenterDistributedLock> threadLocal = ThreadLocal.withInitial(() -> {
        return new ReenterDistributedLock();
    });

    private static ThreadLocal<String> threadLocalName = ThreadLocal.withInitial(() -> {
        return Thread.currentThread().getName();
    });

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            threadLocal.get().lock();
            System.out.println(threadLocalName.get() + " working.......");
            try {
                Thread.sleep(2000);
                this.method01();
                this.method02();
                this.method03();
                this.method04();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().unlock();
        }

        private void method01() {
            threadLocal.get().lock();
            System.out.println(threadLocalName.get() + " reenter method01");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().unlock();
        }

        private void method02() {
            threadLocal.get().lock();
            System.out.println(threadLocalName.get() + " reenter method02");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().unlock();
        }

        private void method03() {
            threadLocal.get().lock();
            System.out.println(threadLocalName.get() + " reenter method03");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().unlock();
        }

        private void method04() {
            threadLocal.get().lock();
            System.out.println(threadLocalName.get() + " reenter method04");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.get().unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread()).start();
        }

        /*List<String> children = new ArrayList<>();
        children.add("08");
        children.add("12");
        children.add("09");
        children.add("20");
        children.add("07");

        String nodePathSuffix = "12";
        String[] strs = new String[1];
        strs[0] = "0";

        for (String child : children) {
            if (child.compareTo(nodePathSuffix) < 0) {
                if (child.compareTo(strs[0]) >= 0) {
                    strs[0] = child;
                }
            }
        }*/
    }

}
