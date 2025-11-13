package va.renowave_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SessionConfig {

    @Value("${session.cookie.domain-name}")
    private String domainName;

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseSecureCookie(true);
        cookieSerializer.setUseHttpOnlyCookie(true);
        cookieSerializer.setSameSite("None");
        cookieSerializer.setDomainName(domainName);
        return cookieSerializer;
    }

}
