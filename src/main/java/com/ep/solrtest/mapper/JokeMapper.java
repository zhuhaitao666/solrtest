package com.ep.solrtest.mapper;

import com.ep.solrtest.bean.Joke;
import tk_mybatis_mapper.MyMapper;


public interface JokeMapper extends MyMapper<Joke> {
    Joke getJokeById(Integer id);
}