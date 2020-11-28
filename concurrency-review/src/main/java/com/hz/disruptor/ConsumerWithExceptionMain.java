package com.hz.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.function.Consumer;

/**
 * @author zehua
 * @date 2020/11/27 17:56
 */
public class ConsumerWithExceptionMain {

    public static IntegerEvent newInstance() {
        return new IntegerEvent();
    }

    public static void onEvent(IntegerEvent integerEvent, long l, boolean b) {
        System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " sequence: " + l + " endOfBatch: " + b);
    }

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        Disruptor<IntegerEvent> disruptor = new Disruptor<>(OneProducerMain::newInstance,
                bufferSize,
                DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWith(OneProducerMain::onEvent);
        disruptor.handleEventsWith((integerEvent, l, b) -> System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " other consumer1"));
        disruptor.handleEventsWith((integerEvent, l, b) -> System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " other consumer2"));
        EventHandler<IntegerEvent> eh = (integerEvent, l, b) -> {
            System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " other consumer3");
            throw new Exception("异常");
        };
        disruptor.handleEventsWith(eh);

        disruptor.handleExceptionsFor(eh).with(new ExceptionHandler<IntegerEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, IntegerEvent event) {
                ex.printStackTrace();
                System.out.println("----=====================================1");
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                ex.printStackTrace();
                System.out.println("----==================================================2");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                ex.printStackTrace();
                System.out.println("----===================================================3");
            }
        });
        // 注意，这句代码需要放在异常处理代码的下方
        disruptor.start();

        RingBuffer<IntegerEvent> ringBuffer = disruptor.getRingBuffer();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    int num = (int) (Math.random() * 5000);
                    System.out.println(Thread.currentThread().getName() + "----put " + num);
                    ringBuffer.publishEvent((event, equence, data) -> event.setIntNum(data), num);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "producer" + i).start();
        }
    }

}
