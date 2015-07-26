package demo.configs;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig{

	@Bean
    public JedisConnectionFactory connectionFactory() {
 
        // ここにRedisへの接続設定を追加する。
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        System.out.println("redis host name: " + jedisConnectionFactory.getHostName());
        // 設定を変更する場合は以下のように必要な設定を行う。
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setHostName("localhost");
//        jedisConnectionFactory.setTimeout(60);
//        jedisConnectionFactory.setPassword("password");
//        jedisConnectionFactory.set... 
 
        return jedisConnectionFactory;
    }
}