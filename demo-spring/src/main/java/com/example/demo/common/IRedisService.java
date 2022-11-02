package com.example.demo.common;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/1/28
 * @Desc
 */
public interface IRedisService<T, E> {

   /**
    *@Func
    *@Param
    *@Return
    *@Author  mengyuetang
    *@CreateTime  2019/1/28
    */
    T put(T entity);

    /**
     *@Func
     *@Param
     *@Return
     *@Author  mengyuetang
     *@CreateTime  2019/1/28
     */
    void removeAll(T entity);

    /**
     *@Func
     *@Param
     *@Return
     *@Author  mengyuetang
     *@CreateTime  2019/1/28
     */
    T get(T entity);

}
