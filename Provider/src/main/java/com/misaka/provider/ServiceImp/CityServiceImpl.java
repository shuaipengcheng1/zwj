package com.misaka.provider.ServiceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.misaka.InterFace.CityService;
import com.misaka.provider.Mapper.CityMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Service(interfaceClass = CityService.class,timeout = 30000,version = "1.0.0")
public class CityServiceImpl implements CityService {
    @Resource
    CityMapper cityMapper;
    @Override
    public String GetCity() {
        return cityMapper.GetCity();
    }
}
