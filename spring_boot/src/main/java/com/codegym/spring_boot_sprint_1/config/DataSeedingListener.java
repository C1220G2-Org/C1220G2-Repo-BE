package com.codegym.spring_boot_sprint_1.config;

import com.codegym.spring_boot_sprint_1.model.*;
import com.codegym.spring_boot_sprint_1.repositories.*;
import com.codegym.spring_boot_sprint_1.until.EncryptPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IFeedbackTypeRepository feedbackTypeRepository;
    @Autowired
    private IMeetingTypeRepository meetingTypeRepository;
    @Autowired
    private ITypeErrorRepository typeErrorRepository;
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String SECRET = "123456";
    private static final String USERNAME_ADMIN = "admin1234";
    private static final String USERNAME_USER = "user1234";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Tạo dữ liệu ban đầu cho bảng department
        if (departmentRepository.findAll().isEmpty()) {
            Department department1 = new Department("Đào tạo");
            Department department2 = new Department("Hành chính");
            Department department3 = new Department("Tuyển sinh");
            departmentRepository.save(department1);
            departmentRepository.save(department2);
            departmentRepository.save(department3);
        }
//        Tạo dữ liệu ban đầu cho bảng feedback_type
        if (feedbackTypeRepository.findAll().isEmpty()) {
            FeedbackType feedback1 = new FeedbackType("Phản hồi phòng họp");
            FeedbackType feedback2 = new FeedbackType("Phản hồi lỗi kỹ thuật");
            feedbackTypeRepository.save(feedback1);
            feedbackTypeRepository.save(feedback2);
        }
//        Tạo dữ liệu bảng phụ meeting_type
        if (meetingTypeRepository.findAll().isEmpty()) {
            MeetingType meetingType = new MeetingType("Một lần");
            meetingTypeRepository.save(meetingType);
        }
//        Tạo dữ liệu bảng phụ type_error
        if (typeErrorRepository.findAll().isEmpty()) {
            TypeError typeError1 = new TypeError("Lỗi đăng nhập");
            TypeError typeError2 = new TypeError("Lỗi giao diện");
            TypeError typeError3 = new TypeError("Lỗi khác");
            typeErrorRepository.save(typeError1);
            typeErrorRepository.save(typeError2);
            typeErrorRepository.save(typeError3);
        }
//        Tạo 2 account mặc định
        if (roleRepository.findByName(ROLE_ADMIN) == null) {
            roleRepository.save(new Role(ROLE_ADMIN));
        }
        if (roleRepository.findByName(ROLE_USER) == null) {
            roleRepository.save(new Role(ROLE_USER));
        }
        //them admin
        if (!userRepository.findByUsername(USERNAME_ADMIN).isPresent()) {
            User admin = new User();
            admin.setName(USERNAME_ADMIN);
            admin.setEmail("admin@gmail.com");
            admin.setUsername(USERNAME_ADMIN);
            admin.setStatus(true);
            admin.setPassword(EncryptPasswordUtils.EncodePassword(SECRET));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(ROLE_ADMIN));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
        //Them Member
        if (!userRepository.findByUsername(USERNAME_USER).isPresent()) {
            User user = new User();
            user.setName(USERNAME_USER);
            user.setEmail("user@gmail.com");
            user.setUsername(USERNAME_USER);
            user.setStatus(true);
            user.setPassword(EncryptPasswordUtils.EncodePassword(SECRET));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(ROLE_USER));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}