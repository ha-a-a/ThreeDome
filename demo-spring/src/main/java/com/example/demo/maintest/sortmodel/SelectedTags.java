package com.example.demo.maintest.sortmodel;

import lombok.Data;

import java.util.Objects;

@Data
public class SelectedTags {
    private Integer id;
    /**
     * 标签id
     */
    private String tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签入库（生效）时间
     */
    private Long startTime;
    /**
     * 标签创建时间
     */
    private Long createTime;
    /**
     * 标签更新时间
     */
    private Long updateTime;
    /**
     * 操作人
     */
    private String creator;

    @Override
    public String toString() {
        return "SelectedTags{" +
                "id=" + id +
                ", tagId='" + tagId + '\'' +
                ", tagName='" + tagName + '\'' +
                ", startTime=" + startTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof SelectedTags) {
            SelectedTags tags = (SelectedTags) o;
            return Objects.equals(tagId, tags.tagId);
        }
        return false;
    }
        @Override
        public int hashCode () {
            return Objects.hash(tagId);
        }
    }
