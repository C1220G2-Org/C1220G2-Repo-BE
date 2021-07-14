package com.codegym.spring_boot_sprint_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    // Replace with your email here:
    public static final String MY_EMAIL = "meetingroomc1220g2@gmail.com";
    // Replace password!!
    public static final String MY_PASSWORD = "rong2dau";
    // And receiver!
    public static final String FRIEND_EMAIL = "tieusau12@gmail.com";

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(MailConfig.MY_EMAIL);
        mailSender.setPassword(MailConfig.MY_PASSWORD);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}