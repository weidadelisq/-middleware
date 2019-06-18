package com.example.demo.service;

import config.CustomException;
import org.springframework.http.ResponseEntity;

/**
 * @Auther: HP
 * @Date: 2019/6/15 17:52
 * @Description:
 */
public interface ESservice {

    /**
     * 创建索引
     *
     * @param index       索引
     * @param shardsNum   主分片数
     * @param replicasNum 副本分片数
     * @return org.springframework.http.ResponseEntity
     * @throws CustomException
     * @creator Conn
     * @date 2018/10/19
     */
    ResponseEntity createIndexSettings(String index, Integer shardsNum, Integer replicasNum) throws CustomException;

}
