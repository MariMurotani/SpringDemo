package config;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="rel")
public class RelaxedConfig {

    @NotNull
    private String unscoVal;

    private String name;

	public String getUnscoVal() {
		return unscoVal;
	}

	public void setUnscoVal(String unscoVal) {
		this.unscoVal = unscoVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
  