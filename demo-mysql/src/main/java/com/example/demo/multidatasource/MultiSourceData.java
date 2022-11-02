package com.example.demo.multidatasource;

import lombok.Data;

import javax.persistence.*;

/**
 * @author mengyuetang
 */
@Data
@Entity
@Table
public class MultiSourceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String userName;
    private String passport;
    private String name;

}
