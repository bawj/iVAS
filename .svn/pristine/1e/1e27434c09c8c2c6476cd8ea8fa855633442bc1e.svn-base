//package com.yn.electricity;
//
//import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
//import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
//
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.apache.ibatis.session.Configuration;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.springframework.stereotype.Component;
//import javax.sql.DataSource;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.text.DateFormat;
//import java.util.*;
//
//
//@Slf4j
//@AllArgsConstructor
////@Aspect
//@Intercepts({
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
//        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
//})
//@Component
//public class SqlLogsInterceptor extends AbstractSqlParserHandler implements Interceptor {
//
//    private DataSource dataSource;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Map<String,Object> sqlMap = new HashMap<>();
//        Boolean hasSqlLogs = true;
//        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
//        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
//        this.sqlParser(metaObject);
//        // 先判断是不是SELECT操作 不是直接过滤
//        MappedStatement mappedStatement =
//                (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
//            return invocation.proceed();
//        }
//
//        final Object[] args = invocation.getArgs();
//        //获取执行方法的位置
//        String namespace = mappedStatement.getId();
//        //获取mapper名称
//        String className = namespace.substring(0,namespace.lastIndexOf("."));
//        //获取方法名
//        String methedName= namespace.substring(namespace.lastIndexOf(".") + 1,namespace.length());
//        //获取当前mapper 的方法
//        Method[] ms = Class.forName(className).getMethods();
//        for(Method m : ms){
////            if(m.getName().equals(methedName)){
////获取注解  来判断是不是要储存sql
////                SqlLogs annotation = m.getAnnotation(SqlLogs.class);
////                if(annotation != null){
////                    hasSqlLogs = annotation.hasSqlLog();
////                }
////            }
//        }
//        //如果是有注解值为true，便获取sql处理参数
//        if(hasSqlLogs){
//            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
//            // 执行的SQL语句
//            String originalSql = boundSql.getSql();
//            // SQL语句的参数
//            Object parameterObject = boundSql.getParameterObject();
//            if(parameterObject != null){
//                originalSql = showSql(null, boundSql);
//            }
//            System.err.println("sql :"+originalSql);
//        }
//        return invocation.proceed();
//
//    }
//
//    /**
//     * 生成拦截对象的代理
//     *
//     * @param target 目标对象
//     * @return 代理对象
//     */
//    @Override
//    public Object plugin(Object target) {
//        if (target instanceof StatementHandler) {
//            return Plugin.wrap(target, this);
//        }
//        return target;
//    }
//
//    /**
//     * 获取参数值
//     * @param obj
//     * @return
//     */
//    private static String getParameterValue(Object obj) {
//        String value = null;
//        if (obj instanceof String) {
//            value = "'" + obj.toString() + "'";
//        } else if (obj instanceof Date) {
//            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
//            value = "to_date('" + formatter.format(obj) + "','yyyy-MM-dd hh24:mi:ss')";
//        } else {
//            if (obj != null) {
//                value = obj.toString();
//            } else {
//                value = "";
//            }
//
//        }
//        System.err.println("获取值: "+value);
//        return value;
//    }
//
//    /***
//     * sql 参数替换
//     * @param configuration
//     * @param boundSql
//     * @return
//     */
//    public static String showSql(Configuration configuration, BoundSql boundSql) {
//        //获取参数对象
//        Object parameterObject = boundSql.getParameterObject();
//        //获取当前的sql语句有绑定的所有parameterMapping属性
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        //去除空格
//        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
//        if (parameterMappings.size() > 0 && parameterObject != null) {
//            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//            /* 如果参数满足：org.apache.ibatis.type.TypeHandlerRegistry#hasTypeHandler(java.lang.Class<?>)
//            org.apache.ibatis.type.TypeHandlerRegistry#TYPE_HANDLER_MAP
//            * 即是不是属于注册类型(TYPE_HANDLER_MAP...等/有没有相应的类型处理器)
//             * */
//            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
//            } else {
//                //装饰器，可直接操作属性值 ---》 以parameterObject创建装饰器
//                //MetaObject 是 Mybatis 反射工具类，通过 MetaObject 获取和设置对象的属性值
//                MetaObject metaObject = configuration.newMetaObject(parameterObject);
//                //循环 parameterMappings 所有属性
//                for (ParameterMapping parameterMapping : parameterMappings) {
//                    //获取property属性
//                    String propertyName = parameterMapping.getProperty();
//                    System.err.println("propertyName: "+propertyName);
//                    //是否声明了propertyName的属性和get方法
//                    if (metaObject.hasGetter(propertyName)) {
//                        Object obj = metaObject.getValue(propertyName);
//                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
//                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
//                        //判断是不是sql的附加参数
//                        Object obj = boundSql.getAdditionalParameter(propertyName);
//                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
//                    }
//                }
//            }
//        }
//        return sql;
//    }
//
//}
