package service;

import config.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    /**
     * 修改索引
     *
     * @param index       索引
     * @param replicasNum 副本分片数
     * @return org.springframework.http.ResponseEntity
     * @throws CustomException
     * @creator Conn
     * @date 2018/10/19
     */
    ResponseEntity updateIndexSettings(String index, Integer replicasNum) throws CustomException;
    /**
     * 删除索引
     *
     * @param index 索引
     * @return org.springframework.http.ResponseEntity
     * @throws CustomException
     * @creator Conn
     * @date 2018/10/19
     */
    ResponseEntity deleteIndex(String index) throws CustomException;
    /**
     * 获得索引配置信息
     *
     * @param index 索引
     * @param type  类型
     * @return org.springframework.http.ResponseEntity
     * @throws CustomException
     * @creator Conn
     * @date 2018/10/24
     */
    ResponseEntity getIndexSettings(String index, String type) throws CustomException;

}
