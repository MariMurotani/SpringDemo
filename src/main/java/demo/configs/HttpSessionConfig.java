package demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig{
	//	 valueをアノテーションでプロパティ設定を取得
	//	 application.propertiesの設定をargsパラメータで上書きできる
	@Value("${spring.redis.host}")
    private String argSHost;
	
	@Value("${spring.redis.port}")
    private int argIPort;
	
	//	application.propertiesデフォルト設定にないものだとNULLPが出るので
	//@Value("${spring.redis.password}")
	private String argSPass;
			
	@Bean
    public JedisConnectionFactory connectionFactory() {
 
        // ここにRedisへの接続設定を追加する。
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        if(argSHost != null){
        	jedisConnectionFactory.setHostName(argSHost);
        }
        jedisConnectionFactory.setPort(argIPort);
        if(argSPass != null){
        	jedisConnectionFactory.setPassword(argSPass);
        }
        
    	System.out.println("redis host name: " + jedisConnectionFactory.getHostName());
    	System.out.println("redis host port: " + jedisConnectionFactory.getPort());
    	System.out.println("redis host pass: " + jedisConnectionFactory.getPassword());
    	
    	// 設定を変更する場合は以下のように必要な設定を行う。
//        jedisConnectionFactory.setTimeout(60);
//        jedisConnectionFactory.set... 
 
        return jedisConnectionFactory;
    }
	
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
	    return ConfigureRedisAction.NO_OP;
	}
}