package com.example.demo.common;

import java.util.List;

/**
 *
 * @author kaenry
 * @date 2016/6/17
 * ICommonService
 * common interface
 */
public interface ICommonService<T, E> {
    /**
     * save entity
     * @param entity
     * @return
     * @throws Exception
     */
    T save(T entity) throws Exception;

    /**
     * delete by id
     * @param id
     * @throws Exception
     */
    void delete(E id) throws Exception;

    /**
     * find by id
     * @param id
     * @return
     */
    T findById(E id);

    /**
     * find all entities
     * @return
     */
    List<T> findAll();
}
