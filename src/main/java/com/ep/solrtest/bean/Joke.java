package com.ep.solrtest.bean;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Table(name = "joke")
@Data
public class Joke implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @Field("id")//该属性对应solr索引库中的id域
    private String id;


    @Column(name = "diynum")
    private Integer diynum;

    @Column(name = "topid")
    private Integer topid;

    @Column(name = "cid")
    private Short cid;

    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    @Field("title1")
    private String title;

    @Column(name = "sd1")
    private String sd1;

    @Column(name = "sd2")
    private String sd2;

    @Column(name = "sd3")
    private String sd3;

    @Column(name = "sd4")
    private String sd4;

    @Column(name = "sd5")
    private String sd5;

    @Column(name = "info")
    @Field("info")
    private String info;

    @Column(name = "voter")
    private Integer voter;

    @Column(name = "total")
    private Integer total;

    @Column(name = "display")
    private Byte display;

    @Column(name = "`top`")
    private Byte top;

    @Column(name = "dateline")
    private Integer dateline;

    private static final long serialVersionUID = 1L;

}