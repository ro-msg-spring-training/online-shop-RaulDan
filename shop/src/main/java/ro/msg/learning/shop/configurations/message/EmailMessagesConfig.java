package ro.msg.learning.shop.configurations.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class EmailMessagesConfig {

    @Bean
    public MessageSource  emailMessages(){
        ResourceBundleMessageSource emailMessages=new ResourceBundleMessageSource();
        emailMessages.setBasename("email_messages");
        return emailMessages;
    }
}
