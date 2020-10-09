package com.itheima.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.CleanOrderSettingDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: chenfuxian
 * @Date: 2020/10/8 20:21
 */
@Service(interfaceClass = CleanOrderSettingService.class)
public class CleanOrderSettingServiceImpl implements CleanOrderSettingService {

   @Autowired
   private CleanOrderSettingDao cleanOrderSettingDao;


    @Override
    public void cleanOrderSetting() {
        cleanOrderSettingDao.cleanOrderSetting();
    }
}
