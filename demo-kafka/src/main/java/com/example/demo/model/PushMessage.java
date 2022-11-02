package com.example.demo.model;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.enums.PushStyleEnum;
import com.example.demo.enums.PushTypeEnum;
import com.example.demo.enums.SourceServerTypeEnum;
import com.example.demo.enums.TargetTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author xiaoduozhang
 * @date 2019/5/29 16:33
 */
@Data
public class PushMessage {
    /**
     * 消息唯一ID，消息发送方生成（是否需要，待商议）
     */
    private String pushId;
    /**
     * APPid ，狐友传1
     */
    private String appId;
    /**
     * @see TargetTypeEnum
     * 发送目标类型，全量/指定cid列表
     */
    private TargetTypeEnum targetType;
    /**
     * @see SourceServerTypeEnum
     * 消息发送方服务器类型，1私信组，2核心组，3运营后台
     */
    private SourceServerTypeEnum sourceServer;
    /**
     * @see PushStyleEnum
     * 1已关注用户新动态push，2运营型push
     */
    private PushStyleEnum pushStyle;
    /**
     * 设备cid列表
     */
    private List<String> targetList;
    /**
     * 设备token
     */
    private List<String> deviceToken;
    /**
     * @see PushTypeEnum
     * 推送类型，1通知栏消息，2透传消息
     */
    private PushTypeEnum pushType;
    /**
     * 设置在通知栏展示的通知的标题, 不允许全是空白字符, 长度小于50(pushType为1时必填)
     */
    private String title;
    /**
     * 设置在通知栏展示的通知描述, 不允许全是空白字符, 长度小于128,(pushType为1时必填)
     */
    private String content;
    /**
     * 通知栏跳转协议，(pushType为1时必填)
     */
    private String protocolUrl;
    /**
     * 可选项, 定时发送消息, timeToSend是以毫秒为单位的时间，单位：ms
     */
    private long timeToSend;
    /**
     * 可选项，通知栏的图片
     */
    private String imgUrl;
    /**
     * 可选项,2020/01/02 运营型push的任务id
     */
    private Long businessId;
    /**
     * 可选项, 消息的生命周期, 若用户离线, 设置消息在服务器保存的时间, 单位: ms
     */
    private long timeToLive;
    /**
     * 可选项，拓展字段，json字串，需要发送方根据厂商推送文档来构造数据
     */
    private JSONObject extraJson;
    /**
     * 业务方埋点需要透传给APP的拓展字段
     */
    private String pingback;
}