package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.CleanOrderSettingService;
import org.springframework.stereotype.Component;

/**
 * @Author: chenfuxian
 * @Date: 2020/10/8 20:13
 */

@Component("cleanOrderSettingJob")
public class CleanOrderSettingJob {

    @Reference
    private CleanOrderSettingService cleanOrderSettingService;

    //任务执行的方法
    public void cleanOrderSetting(){
        cleanOrderSettingService.cleanOrderSetting();
    }
}
