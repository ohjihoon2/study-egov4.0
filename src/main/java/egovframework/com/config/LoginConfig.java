package egovframework.com.config;

import egovframework.com.cmm.config.EgovLoginConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

	@Value("${Globals.login.Lock}")
	private Boolean lock;

	@Value("${Globals.login.LockCount}")
	private int lookCount;

    @Bean
    public EgovLoginConfig egovLoginConfig(){
        EgovLoginConfig egovLoginConfig = new EgovLoginConfig();

        egovLoginConfig.setLock(lock);
        egovLoginConfig.setLockCount(lookCount);

        return egovLoginConfig;
    }
}
