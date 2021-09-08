package com.yn.electricity;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * @ClassName: TransactionalConfig
 * @Author: zzs
 * @Date: 2021/3/19 9:08
 * @Description: 事务处理
 */
@Configuration
public class TransactionalConfig {

    private TransactionManager dataSourceTransactionManager;

    @Autowired
    public TransactionalConfig(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    /**
     * 创建事务通知
     * @return
     */
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("find*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("list*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("create*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");

        NameMatchTransactionAttributeSource trxAttributeSource = new NameMatchTransactionAttributeSource();
        trxAttributeSource.setProperties(properties);

        return new TransactionInterceptor(dataSourceTransactionManager, trxAttributeSource);
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }

}
