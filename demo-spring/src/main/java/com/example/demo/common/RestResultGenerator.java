package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kaenry
 * @date 2016/9/20
 * RestResultGenerator
 */
public class RestResultGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RestResultGenerator.class);

    /**
     * normal
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(ServerCode code, T data) {
        RestResult<T> result = RestResult.newInstance();
        result.setResult(data);
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        if (logger.isDebugEnabled()) {
            logger.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * normal
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(ServerCode code) {

        return genResult(code, null);
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {

        return genResult(ServerCode.SUCCESS, data);
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(ServerCode code, T data) {

        return genResult(code, data);
    }

    /**
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult() {

        return genResult(ServerCode.FAIL);
    }

    /**
     * success no message
     *
     * @return
     */
    public static RestResult genSuccessResult() {
        return genSuccessResult(null);
    }
}
