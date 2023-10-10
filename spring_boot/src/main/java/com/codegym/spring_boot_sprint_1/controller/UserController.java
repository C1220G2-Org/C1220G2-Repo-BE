package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Role;
import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.model.dto.PasswordDto;
import com.codegym.spring_boot_sprint_1.model.dto.UserDto;
import com.codegym.spring_boot_sprint_1.service.IRoleService;
import com.codegym.spring_boot_sprint_1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/role-list")
    public List<Role> getListRole() {
        return roleService.findAll();
    }

    @GetMapping("/user-list")
    public ResponseEntity<Page<User>> getListUser(Pageable pageable) {
        Page<User> userList = userService.findAll(pageable);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/get-user/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/get-user-by-email")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public User findUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);
    }

//    @PatchMapping("/user-update/{id}")
//    @ResponseBody
//    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user) {
//        String checkEmail = user.getEmail();
//        String name;
//        String email;
//        Long departmentId;
//        String avatar;
//        User userFindById = userService.findById(userId);
//        if (userFindById == null) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Người dùng không tồn tại."));
//        }
//        if (!userService.findById(userId).getEmail().equals(checkEmail) && !userService.isEmailExist(user.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Email đã tồn tại. Vui lòng sử dụng email khác."));
//        }
//        UserDto userDto = new UserDto();
//        if (user.getName() != null) {
//            userDto.setName(standardized(user.getName()));
//        }
//        name = userDto.getName();
//        userDto.setEmail(user.getEmail());
//        email = userDto.getEmail();
//        userDto.setDepartment(user.getDepartment());
//        departmentId = userDto.getDepartment();
//        userDto.setAvatar(user.getAvatar());
//        avatar = userDto.getAvatar();
//        userService.updateUser(email, name, departmentId, avatar, userId);
//        userService.updateRole(user.getRoles(), userId);
//        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("/user-delete/{id}")
    @ResponseBody
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable("id") Long id) {
        String date = String.valueOf(LocalDateTime.now());
        String checkdate = "";
        checkdate = date.replace("T", " ");
        checkdate = checkdate.substring(0, 19);
        System.out.println(checkdate);
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("Xóa người dùng thành công !"));
    }

    @PostMapping("/user-save")
    public ResponseEntity<MessageResponse> saveUser(@RequestBody UserDto user) {
        Byte userStatus = 1;
        Boolean b = userService.isUsernameExist(user.getUsername());
        if (!userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tên đăng nhập hoặc email đã tồn tại. Vui lòng nhập lại."));
        }
        if (Boolean.TRUE.equals(b)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tên đăng nhập hoặc email đã tồn tại. Vui lòng nhập lại."));
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(encoder.encode(user.getPassword()));
        userDto.setName(standardized(user.getName()));
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.isGender());
        userDto.setWorkPlace(user.getWorkPlace());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setStatus(user.isStatus());
        User userEntity = new User(userDto.getUsername(), userDto.getPassword(), userDto.isGender(), userDto.getWorkPlace(), userDto.getPhoneNumber(),
                userDto.getAddress(), userDto.getEmail(), userDto.isStatus(), userDto.getName());
        userService.save(userEntity);
        Long id = userService.findUserByEmail(userDto.getEmail()).getId();
        userService.setRole(id, user.getRoles());
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @PatchMapping("/user-change-password/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> changeUserPassword(@PathVariable("id") Long id, @RequestBody PasswordDto passwordDto) {
        String oldPassword = passwordDto.getOldPassword();
        String newPassword = passwordDto.getNewPassword();
        User user = userService.findById(id);
        if (!userPasswordCheck(oldPassword, user)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu cũ không đúng. Vui lòng nhập lại."));
        } else {
            userService.updateUserPassword(encoder.encode(newPassword), id);
            return ResponseEntity.ok(new MessageResponse("Đổi mật khẩu thành công"));
        }
    }

    public boolean userPasswordCheck(String password, User user) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        String encodedPassword = user.getPassword();
        return passencoder.matches(password, encodedPassword);
    }

    public String standardized(String string) {
        string = string.trim().toLowerCase();
        string = string.replaceAll("\\s+", " ");
        String[] temp = string.split(" ");
        // sau khi tách xong, gán xâu st thành sâu rỗng
        string = "";
        for (int i = 0; i < temp.length; i++) {
            string += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) // nếu tempt[i] không phải từ cuối cùng
                string += " ";   // cộng thêm một khoảng trắng
        }
        return string;
    }
}

