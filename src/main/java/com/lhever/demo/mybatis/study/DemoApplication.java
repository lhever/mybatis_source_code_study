package com.lhever.demo.mybatis.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/21 11:53
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/21 11:53
 * @modify by reason:{方法名}:{原因}
 */
@SpringBootApplication(exclude = {
        //禁止数据源自动配置
        RabbitAutoConfiguration.class,
        RedisAutoConfiguration.class,
        MongoAutoConfiguration.class,
        /*, ConsulAutoConfiguration.class*/

})
public class DemoApplication {
    public static void main(String[] args) {
        //运行spring boot
        SpringApplication.run(DemoApplication.class, args);
    }



}
