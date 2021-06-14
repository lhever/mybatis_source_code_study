package com.lhever.demo.mybatis.study;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

@Configuration
@MapperScan(
        basePackages = {
                "com.hikvision.sc.demo.dao"
        },
        sqlSessionTemplateRef = "defaultSqlSessionTemplate")
public class DemoDataSourceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.hikvision.sc.demo.***.service..*.*(..))";

    @Qualifier("defaultSqlSessionFactory")
    @Autowired
    private SqlSessionFactory defaultSqlSessionFactory;


    @Bean(name = "defaultDs")
    @ConfigurationProperties(prefix = "spring.datasource.data")
    public DruidDataSource defaultDs() {
        return new DruidDataSource();
    }


    // 创建事务管理器, 使用JPA事物管理器
    @Bean(name = "defaultTransactionManager")
    @Primary
    public PlatformTransactionManager defaultTransactionManager() {
        PlatformTransactionManager jpaTransactionManager = new DataSourceTransactionManager(defaultDs());
        Properties prop = new Properties();
        prop.setProperty("proxy-target-class", "true");
        return jpaTransactionManager;
    }


    @Value("${mybatis.pagehelper.enable}")
    private Boolean pageHelperEnable;


    @Bean(name = "defaultTxAdvice")
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        // required事务
        source.addTransactionalMethod("insert*", txAttrRequired);
        source.addTransactionalMethod("put*", txAttrRequired);
        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("add*", txAttrRequired);
        source.addTransactionalMethod("register*", txAttrRequired);
        source.addTransactionalMethod("edit*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("set*", txAttrRequired);
        source.addTransactionalMethod("merge*", txAttrRequired);
        source.addTransactionalMethod("del*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);
        source.addTransactionalMethod("remove*", txAttrRequired);
        source.addTransactionalMethod("use*", txAttrRequired);
        source.addTransactionalMethod("start*", txAttrRequired);
        source.addTransactionalMethod("generate*", txAttrRequired);
        source.addTransactionalMethod("sync*", txAttrRequired);
        source.addTransactionalMethod("finish*", txAttrRequired);
        source.addTransactionalMethod("grade*", txAttrRequired);
        source.addTransactionalMethod("execute*", txAttrRequired);
        source.addTransactionalMethod("exec*", txAttrRequired);
        source.addTransactionalMethod("modify*", txAttrRequired);
        source.addTransactionalMethod("change*", txAttrRequired);
        //required readOnly事务
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("*", txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(defaultTransactionManager(), source);
    }

    @Bean("defaultAdvisor")
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "defaultSqlSessionFactory")
    public SqlSessionFactory defaultSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(defaultDs());// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("com.hikvision.sc.demo.pojo");// 指定基包

        Set<Resource> res = new HashSet<>();
        Resource[] resources1 = new PathMatchingResourcePatternResolver().
                getResources("classpath*:com.hikvision.sc.demo.dao.*");
        if (resources1 != null) {
            Stream.of(resources1).forEach( i -> res.add(i));
        }

        Resource[] resources2 = new PathMatchingResourcePatternResolver().
                getResources("classpath*:mapper/*Mapper.xml");
        if (resources2 != null) {
            Stream.of(resources2).forEach( i -> res.add(i));
        }
        Resource[] resources = res.toArray(new Resource[0]);


        fb.setMapperLocations(resources);
//        fb.setPlugins(new Interceptor[]{getDefaultPageInterceptor()});
        return fb.getObject();
    }


    @Bean(name = "defaultSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(defaultSqlSessionFactory());
        return template;
    }


    public PageInterceptor getDefaultPageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        //配置postgresql数据库的方言
//        properties.setProperty("dialect", "com.github.pagehelper.dialect.helper.HsqldbDialect");
        pageInterceptor.setProperties(properties);
        /* PageHelper分页配置结束 */
        return pageInterceptor;
    }



    @PostConstruct
    public void addPageInterceptor() {
        if (pageHelperEnable != null && pageHelperEnable) {
            PageInterceptor interceptor = getDefaultPageInterceptor();
            defaultSqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }






}
