package com.example.sales_post.Service;

import com.example.sales_post.DAO.SalesPostDaoImpl;
import com.example.sales_post.Entity.InquiryEntity;
import com.example.sales_post.Entity.SalesPostEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SalesPostServiceImpl implements SalesPostService {
    private final SalesPostDaoImpl salesPostDaoImpl;
    private final ObjectMapper objectMapper;

    public SalesPostServiceImpl(@Autowired SalesPostDaoImpl salesPostDaoImpl,
                                @Autowired ObjectMapper objectMapper) {
        this.salesPostDaoImpl = salesPostDaoImpl;
        this.objectMapper = objectMapper;
    }

    @Override
    public JSONObject create(JSONObject jsonObject) {
        SalesPostEntity salesPostEntity = jsonToEntity(jsonObject);
        String result = salesPostDaoImpl.create(salesPostEntity);
        return resultJsonObject(result);
    }

    @Override
    public JSONObject readRecentByWriter(JSONObject jsonObject) {
        Map<String, Object> salesPostMap = salesPostDaoImpl.readRecentByWriter((String) jsonObject.get("postWriter"));

        SalesPostEntity salesPostEntity = (SalesPostEntity) salesPostMap.get("data");
        String result = (String) salesPostMap.get("result");
        JSONObject resultJsonObject;

        if (result.equals("success")) {
            resultJsonObject = entityToJson(salesPostEntity);
        } else {
            resultJsonObject = resultJsonObject(result);
        }
        return resultJsonObject;
    }

    @Override
    public JSONObject readAllByWriter(JSONObject jsonObject) {
        Map<String, Object> salesPostMap = salesPostDaoImpl.readAllByWriter((String) jsonObject.get("postWriter"));

        List<SalesPostEntity> salesPostEntityList = (List<SalesPostEntity>) salesPostMap.get("data");
        String result = (String) salesPostMap.get("result");
        List<JSONObject> jsonObjectList = new ArrayList<>();

        if (result.equals("success")) {
            for (SalesPostEntity entity : salesPostEntityList) {
                JSONObject resultJsonObject = entityToJson(entity);
                jsonObjectList.add(resultJsonObject);
            }
            jsonObjectList.add(resultJsonObject(result));
        } else {
            jsonObjectList.add(resultJsonObject(result));
        }
        return resultJsonObject(jsonObjectList);
    }

    @Override
    public JSONObject readAll() {
        Map<String, Object> saelsPostMap = salesPostDaoImpl.readAll();

        List<SalesPostEntity> salesPostEntityList = (List<SalesPostEntity>) saelsPostMap.get("data");
        String result = (String) saelsPostMap.get("result");
        List<JSONObject> jsonObjectList = new ArrayList<>();

        if (result.equals("success")) {
            for (SalesPostEntity entity : salesPostEntityList) {
                JSONObject resultJsonObject = entityToJson(entity);
                jsonObjectList.add(resultJsonObject);
            }
            jsonObjectList.add(resultJsonObject(result));
        } else {
            jsonObjectList.add(resultJsonObject(result));
        }
        return resultJsonObject(jsonObjectList);
    }

    @Override
    public JSONObject update(JSONObject jsonObject) {
        SalesPostEntity salesPostEntity = jsonToEntity(jsonObject);
        String result = salesPostDaoImpl.update(salesPostEntity);
        return resultJsonObject(result);
    }

    @Override
    public JSONObject delete(JSONObject jsonObject) {
        Long postNumber = Long.valueOf((String) jsonObject.get("salesPostNumber"));
        String result = salesPostDaoImpl.delete(postNumber);
        return resultJsonObject(result);
    }

    @Override
    public SalesPostEntity jsonToEntity(JSONObject jsonObject) {
        return objectMapper.convertValue(jsonObject, SalesPostEntity.class);
    }

    @Override
    public JSONObject entityToJson(SalesPostEntity salesPostEntity) {
        JSONObject jsonObject = new JSONObject(objectMapper.convertValue(salesPostEntity, Map.class));
        return jsonObject;
    }

    @Override
    public JSONObject resultJsonObject(String result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public JSONObject resultJsonObject(List<JSONObject> jsonObjectList){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonObjectList);
        return jsonObject;
    }
}