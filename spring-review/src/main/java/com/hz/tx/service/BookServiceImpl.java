package com.hz.tx.service;

import com.hz.tx.dao.BookDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * propagation: 传播特性
 * isolation: 事务隔离级别
 * timeout: 超时时间，只有在具有控制权的事务上设置才有效，例如乳如果都是request类型，则需要在外层方法上面设置
 * readOnly: 只读事务，只允许读操作，如果你的一个事务总中只有读，最好设置一下这个属性
 * rollbackFor:
 * rollbackForClassName:
 * noRollbackFor:
 * noRollbacnForClassName:
 * <p>
 * 传播特性
 * 首先明确几点
 * 1、spring默认只会对Error和RuntimeException进行回滚
 * 2、如果一个方法上加了@Transcational，表示当前方法是一个事务
 * 3、同一个类调用该类的事务方法的话，就相当于普通的方法调用，被调用的方法不会走AOP
 * 4、最外层的事务是没有运行在任何事物中的，所以在最外层事务指定mandatory会报错
 * 5、如果你在某个事务内部使用的try catch的话，就不会进行回滚，相当于aop失效了（想想那五个advice是怎么执行的）
 * required: 如果这个事务运行在另一个事务中，两个事务就合并为一个事务，由外围事务控制，也就是这个事务如果出错，不管有没有catch（注意，不是在这个事务内部使用catch，而是在外围事务使用），整个事务都会回滚
 * requires_new：运行在自己的一个新的事务中，挂起当前事务
 * nested：嵌套事务，这个事务如果运行在另一个事务中，则，这个事务是另一个事务的子事务
 * supports: 如果这个事务运行在另一个事务内，则这个事务被当成一个事务，如果这个事务不是运行在另一个事务内，则它被当成普通方法
 * not_supported： 这个事务不应该运行在某个事务中，和没加事务控制一样
 * mandatory: 当前方法必须运行在事务内部，如果没有，则抛出异常
 * never: 这个事务不应该运行在某个事务内，否则抛出异常,这个事务内的代码都不会被执行
 *
 * @Auther zehua
 * @Date 2020/11/6 19:30
 */
@Service
public class BookServiceImpl {
    @Autowired
    private BookDaoImpl bookDaoImpl;

    //@Transactional()
    public void checkout(String username, int id) {
        //ArithmeticException

        bookDaoImpl.getPrice(id);

        bookDaoImpl.updatebStockById(id);
        //try {
            bookDaoImpl.updateAccountByName(username);
        //} catch (Exception e) {

        //}

        int i = 1 / 0;
    }

}
