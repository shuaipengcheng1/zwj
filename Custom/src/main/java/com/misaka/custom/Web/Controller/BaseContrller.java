package com.misaka.custom.Web.Controller;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.misaka.InterFace.CityService;
import com.misaka.InterFace.TimeService;
import com.misaka.InterFace.TypeService;
import com.misaka.InterFace.VipService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseContrller {
    //    获取dubbo对象
    @Reference(interfaceClass = VipService.class, timeout = 30000, version = "1.0.0")
    VipService vipService;
    @Reference(interfaceClass = TimeService.class, timeout = 30000, version = "1.0.0")
    TimeService timeService;
    @Reference(interfaceClass = CityService.class, timeout = 30000, version = "1.0.0")
    CityService cityService;
    @Reference(interfaceClass = TypeService.class, timeout = 30000, version = "1.0.0")
    TypeService typeService;

    //返回列表
    @RequestMapping(value = "/page")
    public Object Page(int pages, HttpServletResponse response) {
//设置跨域
        response.setHeader("Access-Control-Allow-Headers","*");

        response.setHeader("Access-Control-Allow-Origin", "*");

        return vipService.GetVip(pages);
    }

    @RequestMapping(value = "/City")
    public Map<String, Object> City(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[10];
        String[] strings1 = new String[0];
        try {
            strings1 = objectMapper.readValue(cityService.GetCity(), strings.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> m = new HashMap<>();
        m.put("message", strings1);
        return m;
    }

    @RequestMapping(value = "/Time")
    public Map<String, Object> Time(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[10];
        String[] strings1 = new String[0];
        try {
            strings1 = objectMapper.readValue(timeService.GetTime(), strings.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> m = new HashMap<>();
        m.put("message", strings1);
        return m;
    }

    @RequestMapping(value = "/Type")
    public Map<String, Object> Type(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[10];
        String[] strings1 = new String[0];
        try {
            strings1 = objectMapper.readValue(typeService.GetType(), strings.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> m = new HashMap<>();
        m.put("message", strings1);
        return m;
    }
}
