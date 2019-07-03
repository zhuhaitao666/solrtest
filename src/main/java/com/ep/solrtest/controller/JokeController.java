package com.ep.solrtest.controller;

import com.ep.solrtest.bean.Joke;
import com.ep.solrtest.service.JokeService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class JokeController {
    @Autowired
    private JokeService jokeService;

    @RequestMapping("/test")
    public Joke getJokeById(Integer id) {
        return jokeService.getJokeById(id);
    }

    @RequestMapping("/add")
    public String addOrUpdate()//把数据添加到solr索引库
    {
        //1.获取solr服务器核心接口
        String url="http://localhost:8080/solr/core_demo";
        //2.创建solr请求客户端
        HttpSolrClient solrClient=new HttpSolrClient.Builder(url).build();
        //创建对象
        Joke joke=new Joke();
        joke.setId("4021");
        joke.setInfo("最近，分手的情侣很多，宋仲基和宋乔慧离婚，李晨和范冰冰分手，这难道就是传说中的基李太美效应?");
        joke.setAuthor("朱海涛");
        joke.setTitle("鸡你太美");
        //3.通过UpdateResponse接口添加
        try {
            UpdateResponse response=solrClient.addBean(joke);
            //4.事务提交
            solrClient.commit();
            solrClient.close();
        } catch (Exception e) {
            System.out.println("添加失败");
            e.printStackTrace();
        }
        return "添加成功";
    }
    @RequestMapping("/deleteById")
    public String delete(){
        String info="删除成功";
        //1.获取solr核心接口
        String url="http://localhost:8080/solr/core_demo";
        //2.获取httpSolrClient客户端
        HttpSolrClient client=new HttpSolrClient.Builder(url).build();
        Integer id=4021;
        try {
            //3.通过接口执行方法
            UpdateResponse updateResponse=  client.deleteById(String.valueOf(id));
            //4.执行事务
            client.commit();
            client.close();
        } catch (SolrServerException e) {
            info="删除失败";
            e.printStackTrace();
        } catch (IOException e) {
            info="删除失败";
            e.printStackTrace();
        }
        return info;
    }

    @RequestMapping("/findAll")
    public List<Joke> simpleQuery() throws IOException, SolrServerException {
        //1.获取solr核心接口
        String url="http://localhost:8080/solr/core_demo";
        //2.获取httpSolrClient客户端
        HttpSolrClient client=new HttpSolrClient.Builder(url).build();
        //3.获取参数实例对象 注意SolrParams是抽象类，需要找它实现类
        SolrParams solrParams = new SolrQuery("*:*");
        QueryResponse queryResponse= client.query(solrParams);
        List <Joke> jokes=queryResponse.getBeans(Joke.class);
        try {
            client.commit();
            client.close();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jokes;
    }
    @RequestMapping("/find")
    public List<Joke> ComplexQuery() throws IOException, SolrServerException {
        //1.获取solr核心接口
        String url="http://localhost:8080/solr/core_demo";
        //2.获取httpSolrClient客户端
        HttpSolrClient client=new HttpSolrClient.Builder(url).build();
        //3.获取参数实例对象 注意SolrParams是抽象类，需要找它实现类
        SolrParams solrParams = new SolrQuery();
        QueryResponse queryResponse= client.query(solrParams);
        List <Joke> jokes=queryResponse.getBeans(Joke.class);
        client.commit();
        client.close();
        return jokes;
    }

}
