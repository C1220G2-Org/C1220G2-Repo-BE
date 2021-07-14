package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.config.MailConfig;
import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api/roombooking")
@CrossOrigin(origins = "http://localhost:4200/")
public class SimpleEmailExampleController {
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MailConfig.FRIEND_EMAIL);
        message.setSubject("thu nghiem gui email,");
        message.setText("Hello, thang de");
        // Send Message!
        this.emailSender.send(message);
        return "Email Sent!";
    }

    @ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String sendHtmlEmail(@RequestParam(value = "code") String code,
                                @RequestParam(value = "userId") Long userId,
                                @RequestParam(value = "startDateVariable") String startDateVariable,
                                @RequestParam(value = "endDateVariable") String endDateVariable,
                                @RequestParam(value = "startHourVariable") String startHourVariable,
                                @RequestParam(value = "endHourVariable") String endHourVariable,
                                @RequestParam(value = "meetingRoomName", defaultValue = "") String meetingRoomName
    ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        String user = String.valueOf(userId);
        String codeString = String.valueOf(code);
        String url = "http://localhost:8080/api/roombooking/agree?userId=".concat(user).concat("&code=").concat(codeString).concat("&startDateVariable=").concat(startDateVariable)
                .concat("&endDateVariable=").concat(endDateVariable).concat("&startHourVariable=").concat(startHourVariable).concat("&endHourVariable=").concat(endHourVariable).concat("&meetingRoomName=").concat(meetingRoomName);
        String urlnotagree = "http://localhost:8080/api/roombooking/notagree?userId=".concat(user).concat("&code=").concat(codeString).concat("&startDateVariable=").concat(startDateVariable)
                .concat("&endDateVariable=").concat(endDateVariable).concat("&startHourVariable=").concat(startHourVariable).concat("&endHourVariable=").concat(endHourVariable).concat("&meetingRoomName=").concat(meetingRoomName);
        boolean multipart = true;
        User userObject = userService.findById(userId);
        String htmlMsg = "" +
                "<div style='background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,51,163,0.7539390756302521) 25%, rgba(0,212,255,1) 100%);height: 50px'></div>" +
                "<h3 style='; font-family: Roboto, sans-serif;'> User : " + userObject.getName() + ". có email: " + userObject.getEmail() + " này đã đăng\n" +
                "    ký hơn 2 ngày, bạn có cho phép đăng ký không?</h3>\n" +
                "<h5>Ngày đăng ký: </h5> \n" +
                startDateVariable + "đến " + endDateVariable + "</br>" +
                "<h5>Giờ đăng ký: </h5> \n" +
                startHourVariable + "  đến " + endHourVariable + "</br>" +
                "<h5>Phòng đăng ký: </h5> \n" +
                meetingRoomName + "</br>" +
                "<div style='height: 30px'> </div>" +
                "<a href=\"" + urlnotagree + "\" style='display:inline-block;text-decoration:none;background-color: black;color:#ffffff;padding:13px;border:0px solid #76b900;font-family: Roboto, sans-serif' >Không\n" +
                "    cho phép</a>\n" +
                "<a  href=\"" + url + "\"  style='display:inline-block;text-decoration:none;background-color: blue;color:#ffffff;padding:13px;border:0px solid #76b900;font-family: Roboto, sans-serif'>Cho phép</a>"
                + "<div style='height: 30px'> </div>"
                + "<div style='background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,51,163,0.7539390756302521) 25%, rgba(0,212,255,1) 100%);height: 50px'></div>";
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        message.setContent(htmlMsg, "text/html; charset=UTF-8");
//        message.setContent(htmlMsg, "text/html");
        helper.setTo(MailConfig.FRIEND_EMAIL);
        helper.setSubject("Thư gửi từ hệ thống đặt phòng họp");
        this.emailSender.send(message);
        return "Email Sent http!";
    }

    public String sendUserMail(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "startDateVariable") String startDateVariable,
            @RequestParam(value = "endDateVariable") String endDateVariable,
            @RequestParam(value = "startHourVariable") String startHourVariable,
            @RequestParam(value = "endHourVariable") String endHourVariable,
            @RequestParam(value = "agree") String agree,
            @RequestParam(value = "meetingRoomName", defaultValue = "") String meetingRoomName
    ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        String user = String.valueOf(userId);
        String url = "http://localhost:4200/dat-phong-hop/man-hinh";
        boolean multipart = true;
        User userObject = userService.findById(userId);
        String htmlMsg = "" +
                "<div style='background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,51,163,0.7539390756302521) 25%, rgba(0,212,255,1) 100%);height: 50px'></div>" +
                "<h3 style='; font-family: Roboto, sans-serif;'> User : " + userObject.getName() + ". có email: " + userObject.getEmail() + " này đã đăng\n" +
                "    ký hơn 2 ngày, và bạn  <span style='color: red'>" + agree + "</span> bởi Admin  </h3>\n" +
                "<h5>Ngày đăng ký: </h5> \n" +
                startDateVariable + "đến " + endDateVariable + "</br>" +
                "<h5>Giờ đăng ký: </h5> \n" +
                startHourVariable + "  đến " + endHourVariable + "</br>" +
                "<h5>Phòng đăng ký: </h5> \n" +
                meetingRoomName + "</br>" +
                "<div style='height: 30px'> </div>" +
                "<a href=\"" + url + "\" style='display:inline-block;text-decoration:none;background-color: black;color:#ffffff;padding:13px;border:0px solid #76b900;font-family: Roboto, sans-serif' >Quay lại\n" +
                "    trang chủ</a>\n"
                + "<div style='height: 30px'> </div>"
                + "<div style='background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,51,163,0.7539390756302521) 25%, rgba(0,212,255,1) 100%);height: 50px'></div>";
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        message.setContent(htmlMsg, "text/html; charset=UTF-8");
        helper.setTo(userObject.getEmail());
        helper.setSubject("Thư gửi từ hệ thống đặt phòng họp");
        this.emailSender.send(message);
        return "Email Sent http!";
    }
}