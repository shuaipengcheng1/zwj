package com.misaka.provider.ServiceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.misaka.InterFace.CityService;
import com.misaka.InterFace.TypeService;
import com.misaka.provider.Mapper.TypeMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Service(interfaceClass = TypeService.class,timeout = 30000,version = "1.0.0")
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;
    @Override
    public String GetType() {
        return typeMapper.GetType();

    }
}
