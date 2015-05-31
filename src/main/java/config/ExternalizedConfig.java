package config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
@Configuration
//@Configuration ←どちらでも可
public class ExternalizedConfig {

    /** 最初から用意されている変数 */
    @Value("${spring.profiles.active}")
    private String activeProfile;

    /** コマンドラインから設定する値 */
    @Value("${message}")
    private String message;

    /** アプリケーション内の設定ファイルの値 */
    @Value("${env.name}")
    private String name;

    /** keyがアンダースコア区切りの値 */
    @Value("${UNSCO_VAL}")
    private String unscoVal;

    /** 環境変数の利用 */
    @Value("${JAVA_HOME}")
    private String javaHome;

    /** 環境変数の利用（設定ファイル経由） */
    @Value("${jHome}")
    private String jHomeViaFile;

	public String getActiveProfile() {
		return activeProfile;
	}

	public void setActiveProfile(String activeProfile) {
		this.activeProfile = activeProfile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnscoVal() {
		return unscoVal;
	}

	public void setUnscoVal(String unscoVal) {
		this.unscoVal = unscoVal;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public String getjHomeViaFile() {
		return jHomeViaFile;
	}

	public void setjHomeViaFile(String jHomeViaFile) {
		this.jHomeViaFile = jHomeViaFile;
	}

    
}
  