package com.example.demoes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;


/**
 * @author tangmengyue
 * @ClassName Feed.java
 * @Description TODO
 * @createTime 2023年02月14日 11:29:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "index_name", type = "table_name")
public class Feed {

    private String personId;
    @Id
    private String _id;

}
