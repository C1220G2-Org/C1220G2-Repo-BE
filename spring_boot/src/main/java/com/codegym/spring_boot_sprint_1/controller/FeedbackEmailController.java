package com.codegym.spring_boot_sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:4200/")
public class FeedbackEmailController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String sendHtmlEmail(@RequestParam(value = "title") String title,
                                @RequestParam(value = "roomName") String roomName,
                                @RequestParam(value = "feedbackType") String feedbackType,
                                @RequestParam(value = "errorType") String errorType,
                                @RequestParam(value = "content") String content,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "email2") String email2
    ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        String htmlMsg = "" +
                "<div style='background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,51,163,0.7539390756302521) 25%, rgba(0,212,255,1) 100%);height: 50px'></div>" +
                "<h3 style='; font-family: Roboto, sans-serif;'> Phản hồi của bạn đã được xử lý</h3>\n" +
                "<h5>Tiêu đề: </h5> \n" +
                "<p>" + title + "</p>" + "</br>" +
                "<h5>Tên phòng: </h5> \n" +
                "<p>" + roomName + "</p>" + "</br>" +
                "<h5>Loại phản hồi: </h5> \n" +
                "<p>" + feedbackType + "</p>" + "</br>" +
                "<h5>Loại lỗi: </h5> \n" +
                "<p>" + errorType + "</p>" + "</br>" +
                "<h5>Nội dung phản hồi: </h5> \n" +
                "<p>" + content + "</p>" + "</br>" +
                "<div style='height: 30px'> </div>";
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(email);
        helper.addCc(email2);
        helper.setSubject("Thư gửi từ hệ thống đặt phòng họp");
        this.emailSender.send(message);
        return "Email Sent http!";
    }
}