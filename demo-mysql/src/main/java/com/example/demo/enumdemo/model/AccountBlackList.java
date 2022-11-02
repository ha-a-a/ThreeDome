package com.example.demo.enumdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author kevinzou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "account_blacklist")
public class AccountBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String userName;

    private String passport;

    private String mobile;

    private String currentCid;


    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private BlockTypeEnum blockType;

    private String blockReason;

    private String operator;

    private String memo;

}
