package com.misaka.provider.ServiceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.misaka.Domain.Vip_Shop;
import com.misaka.InterFace.CityService;
import com.misaka.InterFace.VipService;
import com.misaka.provider.Mapper.VipMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Service(interfaceClass = VipService.class,timeout = 30000,version = "1.0.0")
public class VipServiceImpl implements VipService {
    @Resource
    VipMapper vipMapper;
    @Override
    public PageInfo<Vip_Shop> GetVip(int pages) {
//        pageHelper分页
        PageHelper.startPage(pages,16);
        PageInfo<Vip_Shop> pageInfo = new PageInfo<Vip_Shop>(vipMapper.GetVip());

        return pageInfo;

    }
}
