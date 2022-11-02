package com.example.demo.enums;

/**
 * @author xiaoduozhang
 * @date 2019/5/29 16:34
 */
public enum PushStyleEnum {
    /**
     * 0:保留值
     */
    Retain,
    /**
     * 已关注用户新动态push
     */
    PUSH_NEW_FEED,
    /**
     * 运营型push
     */
    PUSH_ACTIVITY,
    /**
     * 私信群聊push
     */
    PUSH_IM,
    /**
     * 评论
     */
    PUSH_COMMENT,
    /**
     * 转发
     */
    PUSH_FORWARD,
    /**
     * 互动型push
     */
    PUSH_INTERACT,
    /**
     * 圈子精华动态push
     */
    PUSH_FEED_ELITE,
    /**
     * 圈子最热push
     */
    PUSH_FEED_HOT;

}
