package com.example.demo.log.pojo;

import com.example.demo.constant.GlobalCons;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Data
@Entity
@Table(name = "operate_log")
@DynamicUpdate
@DynamicInsert
public class OperateLogPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private OpModuleType moduleName;
    private OpWebType webName;
    private String account;
    private String operator;
    private String memo;
    private String action;
    private String remark;
    private String ip;
    @DateTimeFormat(pattern = GlobalCons.DATA_FORMAT)
    @JsonFormat(pattern = GlobalCons.DATA_FORMAT, timezone = GlobalCons.TIME_ZONE)
    private Date createTime;
    @DateTimeFormat(pattern = GlobalCons.DATA_FORMAT)
    @JsonFormat(pattern = GlobalCons.DATA_FORMAT, timezone = GlobalCons.TIME_ZONE)
    private Date updateTime;

}
