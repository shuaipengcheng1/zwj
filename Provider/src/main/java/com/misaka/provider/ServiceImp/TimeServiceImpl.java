package com.misaka.provider.ServiceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.misaka.InterFace.CityService;
import com.misaka.InterFace.TimeService;
import com.misaka.provider.Mapper.TimeMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Service(interfaceClass = TimeService.class,timeout = 30000,version = "1.0.0")
public class TimeServiceImpl implements TimeService {
    @Resource
    TimeMapper timeMapper;
    @Override
    public String GetTime() {
        return timeMapper.GetTime();
    }
}
