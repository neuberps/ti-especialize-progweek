package br.tie.progweek.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix="msg")
public class ConfigProperties {

    public ConfigProperties() {
        super();
    }

    private String successtitle;
    private String usercreatesuccesstext;
    private String userupdatesuccesstext;
    private String userdeletesuccesstext;
    private String errortitle;
    private String usercreateerrortext;
    private String userupdateerrortext;
    private String userdeleteerrortext;
    private String userlisterrortext;
    private String userediterrortext;
    private String uservalidemailerrortext;
    private String warningtitle;
    private String useremailexistwarningtext;

}
