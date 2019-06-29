package com.lyh.demo.controller;

import config.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ESserviceImpl;

/**
 * @Author: lishuangqiang
 * @Date: 2019/6/15
 * @Description:
 */

@RestController
/*
@RequestMapping("es")
*/
public class EScontroller {

    ResponseEntity result = null;
    /*@Autowired
    ESserviceImpl iElasticSearchService ;*/
   ESserviceImpl iElasticSearchService = new ESserviceImpl();
    /**
     * 创建索引
     *
     * @param index       索引
     * @param shardsNum   主分片数
     * @param replicasNum 副本分片数
     * @return net.conn.es.elasticsearch.utils.ResultInfo
     * @creator Conn
     * @date 2018/10/19
     */
    @PutMapping("/createIndexSettings")
    public ResponseEntity createIndexSettings(@RequestParam(name = "index") String index,
                                              @RequestParam(name = "shardsNum", defaultValue = "5") Integer shardsNum,
                                              @RequestParam(name = "replicasNum", defaultValue = "1") Integer replicasNum) {

        try {
            result = iElasticSearchService.createIndexSettings(index, shardsNum, replicasNum);
            //return ResultInfo.success().build(result);
            return result;
        } catch (CustomException e) {
            e.printStackTrace();
            return result;

            // return ResultInfo.failure(e.getMessage());
        }
    }


    /**
     * 修改索引
     *
     * @param index       索引
     * @param replicasNum 副本分片数
     * @return net.conn.es.elasticsearch.utils.ResultInfo
     * @creator Conn
     * @date 2018/10/19
     */
    @PutMapping("/updateIndexSettings")
    public ResponseEntity updateIndexSettings(@RequestParam(name = "index") String index,
                                          @RequestParam(name = "replicasNum", defaultValue = "") Integer replicasNum) {
        ResponseEntity result = null;

        try {
            result = iElasticSearchService.updateIndexSettings(index, replicasNum);
            return result;
        } catch (CustomException e) {
            e.printStackTrace();
            return result;
        }
    }
    /**
     * 删除索引
     *
     * @param index 索引
     * @return net.conn.es.elasticsearch.utils.ResultInfo
     * @creator Conn
     * @date 2018/10/19
     */
    @DeleteMapping("/deleteIndex")
    public ResponseEntity deleteIndex(@RequestParam(name = "index") String index) {
        try {
            ResponseEntity result = iElasticSearchService.deleteIndex(index);
            return result;

        } catch (CustomException e) {
            e.printStackTrace();
            return result;

        }
    }
    /**
     * 获得索引配置信息
     *
     * @param index 索引
     * @param type  类型
     * @return net.conn.es.elasticsearch.utils.ResultInfo
     * @creator Conn
     * @date 2018/10/24
     */
    @GetMapping("/getIndexSettings")
    public ResponseEntity getIndexSettings(@RequestParam(name = "index", defaultValue = "resource") String index,
                                       @RequestParam(name = "type", defaultValue = "resource") String type) {
        try {

            ResponseEntity result = iElasticSearchService.getIndexSettings(index, type);
            return result;

        } catch (CustomException e) {
            e.printStackTrace();
            return result;

        }
    }

}


