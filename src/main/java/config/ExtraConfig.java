package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ExtraConfig {
    /** 最初から用意されている変数 */
    //@Value("${spring.profiles.active}")
    //private String activeProfile;

    /** アプリケーション外から設定する値 */
    //@Value("${message}")
    //private String message;
	
    /** アプリケーション内の設定ファイルの値 */
    //@Value("${env.name}")
    //private String name;
		
    /** keyがアンダースコア区切りの値 */
    //@Value("${UNSCO_VAL}")
    //private String unscoVal;

    /** 環境変数の利用１ */
    //@Value("${JAVA_HOME}")
    //private String javaHome;

    /** 環境変数の利用２ */
    //@Value("${jHome}")
    //private String jHomeViaFile;

   /* public String getActiveProfile() {
        return activeProfile;
    }*/

    /*public String getJavaHome() {
        return javaHome;
    }

    public String getjHomeViaFile() {
        return jHomeViaFile;
    }*/
}
