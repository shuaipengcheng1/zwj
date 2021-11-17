package com.misaka.InterFace;

import com.github.pagehelper.PageInfo;
import com.misaka.Domain.Vip_Shop;

public interface VipService {
//    根据返回Vip列表对象
PageInfo<Vip_Shop> GetVip(int pages);
}
