package controller;

import com.example.demo.service.ESserviceImpl;
import config.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        ResponseEntity result = null;
        ESserviceImpl es = new ESserviceImpl();
        try {
            result = es.createIndexSettings(index, shardsNum, replicasNum);
            //return ResultInfo.success().build(result);
            return result;
        } catch (CustomException e) {
            e.printStackTrace();
            return result;

            // return ResultInfo.failure(e.getMessage());
        }
    }
}


