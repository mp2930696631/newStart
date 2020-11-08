package com.hz.observer.v3.event;

import com.hz.observer.v3.SourceObj;

/**
 * 事件接口，其中包含获取事件源对象的方法
 *
 * @Auther zehua
 * @Date 2020/11/8 7:37
 */
public interface Event {
    SourceObj getSource();
}
