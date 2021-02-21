package com.hz.v4.advice;

import com.hz.v4.MethodChain;

/**
 * @author zehua
 * @date 2021/2/18 15:19
 */
public interface AdviceI {

    Object invoke(MethodChain mc) throws Exception;

}
