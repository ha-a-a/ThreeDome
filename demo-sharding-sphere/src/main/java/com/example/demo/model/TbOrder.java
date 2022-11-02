package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author mengyuetang
 */
@Entity
@Table
@Data
public class TbOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private long userId;
  private String orderNo;
  private Date orderTime;
  private String merchant;
}
