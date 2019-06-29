package service;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import config.CustomException;
import config.ElasticSearchConfig;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lishuangqiang
 * @Date: 2019/6/15
 * @Description:
 */
@Service
public class ESserviceImpl   implements   ESservice{


    ElasticSearchConfig  elasticSearchConfig=new ElasticSearchConfig();
    TransportClient client=elasticSearchConfig.transportClient();
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
    @Override
    public ResponseEntity updateIndexSettings(String index, Integer replicasNum) throws CustomException {
        if (index == null) {
            throw new CustomException("索引不能为空");
        }
        if (replicasNum == null) {
            throw new CustomException("副本数不能为空");
        }

        try {
            //创建条件
            Settings.Builder sb = Settings.builder();

            //如果需要更改副本数 则加进条件  注:索引分片数在索引创建好了之后就不能调整了，只能重建索引
            sb.put("index.number_of_replicas", replicasNum);

            UpdateSettingsResponse response = client.admin().indices()
                    .prepareUpdateSettings(index)
                    .setSettings(sb)
                    .get();
            return new ResponseEntity(response.isAcknowledged(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("修改索引业务层异常");
        }
    }
    /**
     * 删除索引
     *
     * @param index 索引
     * @return org.springframework.http.ResponseEntity
     * @throws CustomException
     * @creator Conn
     * @date 2018/10/19
     */
    @Override
    public ResponseEntity deleteIndex(String index) throws CustomException {

        try {
            DeleteIndexResponse response = client.admin().indices()
                    .prepareDelete(index)
                    .get();
            return new ResponseEntity(response.isAcknowledged(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("删除索引业务层异常");
        }
    }
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
    @Override
    public ResponseEntity getIndexSettings(String index, String type) throws CustomException {

        try {
            GetSettingsResponse response = client.admin().indices()
                    .prepareGetSettings(index)
                    .get();

            Map<String, Object> map = new HashMap<>();
            for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) {
                String index2 = cursor.key;
                Settings settings = cursor.value;
                Integer shards = settings.getAsInt("index.number_of_shards", null);
                Integer replicas = settings.getAsInt("index.number_of_replicas", null);

                map.put("index", index2);
                map.put("shards", shards);
                map.put("replicas", replicas);
            }

            return new ResponseEntity(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("获得索引配置信息业务层异常");
        }
    }


}
