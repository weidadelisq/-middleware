package com.example.demo.service;

import config.CustomException;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.support.AbstractClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Author: lishuangqiang
 * @Date: 2019/6/15
 * @Description:
 */

public class ESserviceImpl   implements   ESservice{
    @Autowired
    private TransportClient client;
    @Override
    public ResponseEntity createIndexSettings(String index, Integer shardsNum, Integer replicasNum) throws CustomException {
        if (index == null) {
            throw new CustomException("索引不能为空");
        }
        try {

            //分片数及副本数
            Settings.Builder sb = Settings.builder()
                    .put("index.number_of_shards", shardsNum)
                    .put("index.number_of_replicas", replicasNum);


            //创建索引
            CreateIndexResponse response = client.admin().indices()
                    .prepareCreate(index)
                    .setSettings(sb)
                    .get();

            return new ResponseEntity(response.isAcknowledged(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("创建索引业务层异常");
        }
    }
}
