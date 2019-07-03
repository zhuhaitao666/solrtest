package com.ep.solrtest.service.impl;

import com.ep.solrtest.bean.Joke;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ep.solrtest.mapper.JokeMapper;
import com.ep.solrtest.service.JokeService;
import tk.mybatis.mapper.entity.Example;

@Service
public class JokeServiceImpl implements JokeService{

    @Resource
    private JokeMapper jokeMapper;

    @Override
    public Joke getJokeById(Integer id) {
        Example example=new Example(Joke.class);
        example.createCriteria().andEqualTo("id",id);
        return jokeMapper.selectOneByExample(example);
//        return jokeMapper.getJokeById(id);
    }

}
