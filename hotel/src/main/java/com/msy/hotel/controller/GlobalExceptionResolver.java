package com.msy.hotel.controller;

import com.msy.hotel.exception.CustomerException;
import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wudagai
 * @date: 2021/10/29 15:15
 * @description:\
 * 通过 @ControllerAdvice 注解，我们可以在一个地方对所有 @Controller 注解的控制器进行管理。
 * 注解了 @ControllerAdvice 的类的方法可以使用 @ExceptionHandler、 @InitBinder、 @ModelAttribute 注解到方法上
 * ，这对所有注解了 @RequestMapping 的控制器内的方法都有效。
 *
 * @ControllerAdvice + @ExceptionHandler 进行全局的 Controller 层异常处理。
 * 只要设计得当，就再也不用在 Controller 层进行 try-catch 了！
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler({Exception.class})//只要出现Exception异常就会执行对用的异常处理器方法
    @ResponseBody
    public ResponseModel exceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseModel.error(ResCode.ERROR);
    }

    @ExceptionHandler({CustomerException.class})//只要出现CustomerException异常就会执行对用的异常处理器方法
    @ResponseBody
    public ResponseModel customerExceptionHandler(CustomerException e){
        e.printStackTrace();
        System.out.println(111);
        return ResponseModel.error(ResCode.ERROR);
    }

}
