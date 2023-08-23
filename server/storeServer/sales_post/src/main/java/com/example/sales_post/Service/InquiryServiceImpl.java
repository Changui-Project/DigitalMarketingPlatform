package com.example.sales_post.Service;

import com.example.sales_post.DAO.InquiryDaoImpl;
import com.example.sales_post.DAO.SalesPostDaoImpl;
import com.example.sales_post.Entity.InquiryEntity;
import com.example.sales_post.Entity.SalesPostEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InquiryServiceImpl implements InquriyService {
    private final InquiryDaoImpl inquiryDaoImpl;
    private final SalesPostDaoImpl salesPostDaoImpl;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(InquiryServiceImpl.class);

    public InquiryServiceImpl(@Autowired InquiryDaoImpl inquiryDaoImpl,
                              @Autowired SalesPostDaoImpl salesPostDaoImpl,
                              @Autowired ObjectMapper objectMapper) {
        this.inquiryDaoImpl = inquiryDaoImpl;
        this.salesPostDaoImpl = salesPostDaoImpl;
        this.objectMapper = objectMapper;
    }

    public JSONObject create(JSONObject jsonObject){
        Map<String, Object> inquiryMap = jsonToEntity(jsonObject);
        InquiryEntity inquiryEntity = (InquiryEntity) inquiryMap.get("data");
        String JTEresult = (String) inquiryMap.get("result");
        String result;
        if(JTEresult.equals("success")){
            result = inquiryDaoImpl.create(inquiryEntity);
        } else {
            result = JTEresult;
        }
        return resultJsonObject(result);
    }

    public JSONObject readRecentByWriter(JSONObject jsonObject){
        Long postNumber = Long.valueOf((String) jsonObject.get("salesPostNumber"));
        Map<String, Object> inquiryMap = inquiryDaoImpl.readRecentByWriter(postNumber, (String) jsonObject.get("inquiryWriter"));

        InquiryEntity inquiryEntity = (InquiryEntity) inquiryMap.get("data");
        String result = (String) inquiryMap.get("result");
        JSONObject resultJsonObject;

        if (result.equals("success")) {
            resultJsonObject = entityToJson(inquiryEntity);
        } else{
            resultJsonObject = resultJsonObject(result);
        }
        return resultJsonObject;
    }

    public JSONObject readAllByWriter(JSONObject jsonObject) {
        String postNumberStr = (String) jsonObject.get("salesPostNumber");
        Long postNumber = Long.valueOf(postNumberStr);
        Map<String, Object> inquiryMap = inquiryDaoImpl.readAllByWriter(postNumber, (String) jsonObject.get("inquiryWriter"));

        List<InquiryEntity> inquiryEntityList = (List<InquiryEntity>) inquiryMap.get("data");
        String result = (String) inquiryMap.get("result");
        List<JSONObject> jsonObjectList = new ArrayList<>();

        if (result.equals("success")) {
            for (InquiryEntity entity : inquiryEntityList) {
                JSONObject resultJsonObject = entityToJson(entity);
                jsonObjectList.add(resultJsonObject);
            }
            jsonObjectList.add(resultJsonObject(result));
        } else{
            jsonObjectList.add(resultJsonObject(result));
        }
        return resultJsonObject(result);
    }

    @Override
    public JSONObject readAll() {
        Map<String, Object> inquiryMap = inquiryDaoImpl.readAll();

        List<InquiryEntity> inquiryEntityList = (List<InquiryEntity>) inquiryMap.get("data");
        String result = (String) inquiryMap.get("result");
        List<JSONObject> jsonObjectList = new ArrayList<>();

        if (result.equals("success")) {
            for (InquiryEntity entity : inquiryEntityList) {
                JSONObject resultJsonObject = entityToJson(entity);
                jsonObjectList.add(resultJsonObject);
            }
            jsonObjectList.add(resultJsonObject(result));
        } else{
            jsonObjectList.add(resultJsonObject(result));
        }
        return resultJsonObject(result);
    }

    public JSONObject update(JSONObject jsonObject){
        Map<String, Object> inquiryMap = jsonToEntity(jsonObject);
        InquiryEntity inquiryEntity = (InquiryEntity) inquiryMap.get("data");
        String JTEresult = (String) inquiryMap.get("result");
        String result;
        if(JTEresult.equals("success")){
            result = inquiryDaoImpl.update(inquiryEntity);
        } else {
            result = JTEresult;
        }
        return resultJsonObject(result);
    }

    public JSONObject delete(JSONObject jsonObject){
        Long inquiryNumber = Long.valueOf((String) jsonObject.get("inquiryNumber"));
        String result = inquiryDaoImpl.delete(inquiryNumber);
        return resultJsonObject(result);
    }

    public Map<String, Object> jsonToEntity(JSONObject jsonObject){
        InquiryEntity inquiryEntity = objectMapper.convertValue(jsonObject, InquiryEntity.class);
        Long salesPostNumber = Long.valueOf((String) jsonObject.get("salesPostNumber"));

        Map<String, Object> salesPostMap = salesPostDaoImpl.read(salesPostNumber);
        String result = (String) salesPostMap.get("result");

        Map<String, Object> inquiryMap = new HashMap<>();
        if(result.equals("success")){
            inquiryEntity.setSalesPostEntity((SalesPostEntity) salesPostMap.get("data"));
            inquiryMap.put("data", inquiryEntity);
            inquiryMap.put("result", result);
        } else{
            inquiryMap.put("result", result);
        }

        return inquiryMap;
    }

    @Override
    public JSONObject entityToJson(InquiryEntity inquiryEntity) {
        JSONObject jsonObject = new JSONObject(objectMapper.convertValue(inquiryEntity, Map.class));
        return jsonObject;
    }

    public JSONObject resultJsonObject(String result){
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