package com.yn.electricity.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yn.electricity.util.SpringUtils;
import java.util.List;

/**
 * @ClassName: DataSourceInitFunc
 * @Author: zzs
 * @Date: 2021/1/28 11:39
 * @Description: 用于拉取nacos配置文件配置的sentinel规则
 */
public class DataSourceInitFunc implements InitFunc {
    private String remoteAddress;
    private String groupId;
    private String dataId;

    private void getConfig(){
        SentinelConfig config = SpringUtils.getBean(SentinelConfig.class);
        this.remoteAddress = config.getRemoteAddress();
        this.groupId = config.getGroupId();
        this.dataId = config.getDataId();
    }

    @Override
    public void init() {
        getConfig();

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
