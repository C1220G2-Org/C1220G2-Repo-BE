package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Department;
import com.codegym.spring_boot_sprint_1.model.Role;
import com.codegym.spring_boot_sprint_1.model.dto.BookingHistoryDto;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.model.dto.PasswordDto;
import com.codegym.spring_boot_sprint_1.model.dto.UserDto;
import com.codegym.spring_boot_sprint_1.service.IDepartmentService;
import com.codegym.spring_boot_sprint_1.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.service.IUserService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    private static final String NOT_USED_VALUE = "Chưa sử dụng";
    private static final String USED_VALUE = "Đã sử dụng";
    private static final String USING_VALUE = "Đang sử dụng";

    //    khahq's code
    @GetMapping("/booking-history/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingHistoryDto>> getBookingHistoryOfUser(@PathVariable Long userId) {
        List<Object[]> bookings = this.userService.findAllBookingHistoryOfUser(userId);
        List<BookingHistoryDto> bookingHistoryDtos = new ArrayList<>();
        for (Object[] booking : bookings) {
            BookingHistoryDto bookingDto = new BookingHistoryDto();
            bookingDto.setRoomName((String) booking[0]);
            bookingDto.setContent((String) booking[1]);

            bookingDto.setRegistrationDate(convertFromDateToString((Date) booking[2]));

            bookingDto.setFloor(Integer.parseInt("" + booking[3]));

            String[] endDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[4]);
            bookingDto.setEndDate(endDateTime[0]);
            bookingDto.setEndTime(endDateTime[1]);


            String[] startDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[5]);
            bookingDto.setStartDate(startDateTime[0]);
            bookingDto.setStartTime(startDateTime[1]);

            bookingDto.setMeetingType((String) booking[6]);

            // find the status of booking history DTO.
            LocalDateTime endDateTimeObj = LocalDateTime.parse(bookingDto.getEndDate() + "T" + bookingDto.getEndTime());
            LocalDateTime startDateTimeObj = LocalDateTime.parse(bookingDto.getStartDate() + "T" + bookingDto.getStartTime());
            LocalDateTime dateTimeNow = LocalDateTime.now();
            if (dateTimeNow.isBefore(startDateTimeObj)) {
                bookingDto.setStatus(UserController.NOT_USED_VALUE);
            } else if (dateTimeNow.isAfter(endDateTimeObj)) {
                bookingDto.setStatus(UserController.USED_VALUE);
            } else {
                bookingDto.setStatus(UserController.USING_VALUE);
            }

            bookingDto.setBookingId(Integer.parseInt("" + booking[7]));
            bookingDto.setUserId(Integer.parseInt("" + booking[8]));

            bookingHistoryDtos.add(bookingDto);
        }
        return new ResponseEntity<>(bookingHistoryDtos, HttpStatus.OK);
    }

    @GetMapping("/search-booking-history")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingHistoryDto>> searchUserBookingHistory(@RequestParam(name = "userId") Long userId,
                                                                            @RequestParam(name = "roomName") String roomName,
                                                                            @RequestParam(name = "startDate") String startDate,
                                                                            @RequestParam(name = "endDate") String endDate,
                                                                            @RequestParam(name = "status") String status,
                                                                            @RequestParam(name = "meetingType") String meetingType,
                                                                            @RequestParam(name = "registrationDate") String registrationDate) {
        meetingType = meetingType.isEmpty() ? null : meetingType;
        if (startDate.equals("null")) startDate = null;
        if (endDate.equals("null")) endDate = null;
        if (registrationDate.equals("null")) registrationDate = null;

        List<Object[]> bookings = this.userService.searchUserBookingHistory(userId, roomName, startDate, endDate, meetingType, registrationDate);


        List<BookingHistoryDto> bookingHistoryDtos = new ArrayList<>();
        for (Object[] booking : bookings) {
            BookingHistoryDto bookingDto = new BookingHistoryDto();
            bookingDto.setRoomName((String) booking[0]);
            bookingDto.setContent((String) booking[1]);

            bookingDto.setRegistrationDate(convertFromDateToString((Date) booking[2]));

            bookingDto.setFloor(Integer.parseInt("" + booking[3]));

            String[] endDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[4]);
            bookingDto.setEndDate(endDateTime[0]);
            bookingDto.setEndTime(endDateTime[1]);


            String[] startDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[5]);
            bookingDto.setStartDate(startDateTime[0]);
            bookingDto.setStartTime(startDateTime[1]);

            bookingDto.setMeetingType((String) booking[6]);

            // find the status of booking history DTO.
            LocalDateTime endDateTimeObj = LocalDateTime.parse(bookingDto.getEndDate() + "T" + bookingDto.getEndTime());
            LocalDateTime startDateTimeObj = LocalDateTime.parse(bookingDto.getStartDate() + "T" + bookingDto.getStartTime());
            LocalDateTime dateTimeNow = LocalDateTime.now();
            if (dateTimeNow.isBefore(startDateTimeObj)) {
                bookingDto.setStatus(UserController.NOT_USED_VALUE);
            } else if (dateTimeNow.isAfter(endDateTimeObj)) {
                bookingDto.setStatus(UserController.USED_VALUE);
            } else {
                bookingDto.setStatus(UserController.USING_VALUE);
            }

            bookingDto.setBookingId(Integer.parseInt("" + booking[7]));
            bookingDto.setUserId(Integer.parseInt("" + booking[8]));


            if (status.isEmpty()) {
                bookingHistoryDtos.add(bookingDto);
            } else {
                if (bookingDto.getStatus().equals(status)) {
                    bookingHistoryDtos.add(bookingDto);
                }
            }
        }
        return new ResponseEntity<>(bookingHistoryDtos, HttpStatus.OK);
    }


    @GetMapping("/bookings-all-users")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingHistoryDto>> getBookingsOfAllUsers() {
        List<Object[]> bookings = this.userService.getAllBookingHistoryOfAllUsers();

        List<BookingHistoryDto> bookingHistoryDtos = new ArrayList<>();
        for (Object[] booking : bookings) {
            BookingHistoryDto bookingDto = new BookingHistoryDto();
            bookingDto.setRoomName((String) booking[0]);
            bookingDto.setContent((String) booking[1]);

            bookingDto.setRegistrationDate(convertFromDateToString((Date) booking[2]));

            bookingDto.setFloor(Integer.parseInt("" + booking[3]));

            String[] endDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[4]);
            bookingDto.setEndDate(endDateTime[0]);
            bookingDto.setEndTime(endDateTime[1]);


            String[] startDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[5]);
            bookingDto.setStartDate(startDateTime[0]);
            bookingDto.setStartTime(startDateTime[1]);

            bookingDto.setMeetingType((String) booking[6]);

            // find the status of booking history DTO.
            LocalDateTime endDateTimeObj = LocalDateTime.parse(bookingDto.getEndDate() + "T" + bookingDto.getEndTime());
            LocalDateTime startDateTimeObj = LocalDateTime.parse(bookingDto.getStartDate() + "T" + bookingDto.getStartTime());
            LocalDateTime dateTimeNow = LocalDateTime.now();
            if (dateTimeNow.isBefore(startDateTimeObj)) {
                bookingDto.setStatus(UserController.NOT_USED_VALUE);
            } else if (dateTimeNow.isAfter(endDateTimeObj)) {
                bookingDto.setStatus(UserController.USED_VALUE);
            } else {
                bookingDto.setStatus(UserController.USING_VALUE);
            }

            bookingDto.setBookingId(Integer.parseInt("" + booking[7]));
            bookingDto.setUserId(Integer.parseInt("" + booking[8]));

            bookingHistoryDtos.add(bookingDto);
        }

        return new ResponseEntity<>(bookingHistoryDtos, HttpStatus.OK);
    }


    @GetMapping("/search-booking-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingHistoryDto>> searchBookingHistoryOnAdmin(@RequestParam(name = "userId") Long userId,
                                                                               @RequestParam(name = "roomName") String roomName,
                                                                               @RequestParam(name = "startDate") String startDate,
                                                                               @RequestParam(name = "endDate") String endDate,
                                                                               @RequestParam(name = "status") String status,
                                                                               @RequestParam(name = "meetingType") String meetingType,
                                                                               @RequestParam(name = "registrationDate") String registrationDate) {
        meetingType = meetingType.isEmpty() ? null : meetingType;
        if (startDate.equals("null")) startDate = null;
        if (endDate.equals("null")) endDate = null;
        if (registrationDate.equals("null")) registrationDate = null;

        if (userId == 0) userId = null;

        List<Object[]> bookings = this.userService.searchBookingHistoryOnAdmin(roomName, userId, startDate, endDate, meetingType, registrationDate);


        List<BookingHistoryDto> bookingHistoryDtos = new ArrayList<>();
        for (Object[] booking : bookings) {
            BookingHistoryDto bookingDto = new BookingHistoryDto();
            bookingDto.setRoomName((String) booking[0]);
            bookingDto.setContent((String) booking[1]);

            bookingDto.setRegistrationDate(convertFromDateToString((Date) booking[2]));

            bookingDto.setFloor(Integer.parseInt("" + booking[3]));

            String[] endDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[4]);
            bookingDto.setEndDate(endDateTime[0]);
            bookingDto.setEndTime(endDateTime[1]);

            String[] startDateTime = convertFromTimestampToDateTimeTokens((Timestamp) booking[5]);
            bookingDto.setStartDate(startDateTime[0]);
            bookingDto.setStartTime(startDateTime[1]);

            bookingDto.setMeetingType((String) booking[6]);

            // find the status of booking history DTO.
            LocalDateTime endDateTimeObj = LocalDateTime.parse(bookingDto.getEndDate() + "T" + bookingDto.getEndTime());
            LocalDateTime startDateTimeObj = LocalDateTime.parse(bookingDto.getStartDate() + "T" + bookingDto.getStartTime());
            LocalDateTime dateTimeNow = LocalDateTime.now();
            if (dateTimeNow.isBefore(startDateTimeObj)) {
                bookingDto.setStatus(UserController.NOT_USED_VALUE);
            } else if (dateTimeNow.isAfter(endDateTimeObj)) {
                bookingDto.setStatus(UserController.USED_VALUE);
            } else {
                bookingDto.setStatus(UserController.USING_VALUE);
            }

            bookingDto.setBookingId(Integer.parseInt("" + booking[7]));
            bookingDto.setUserId(Integer.parseInt("" + booking[8]));


            if (status.isEmpty()) {
                bookingHistoryDtos.add(bookingDto);
            } else {
                if (bookingDto.getStatus().equals(status)) {
                    bookingHistoryDtos.add(bookingDto);
                }
            }
        }

        return new ResponseEntity<>(bookingHistoryDtos, HttpStatus.OK);
    }


    private static String convertFromDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private static String[] convertFromTimestampToDateTimeTokens(Timestamp dateTime) {
        String dateTimeStr = dateTime.toLocalDateTime().toString();
        String[] dateTimeTokens = dateTimeStr.split("T");
        String date = dateTimeTokens[0];
        String time = dateTimeTokens[1] + ":00";
        return new String[]{date, time};
    }

    // -------------------------------------------------------------------------------------------


    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/role-list")
    public List<Role> getListRole() {
        return roleService.findAll();
    }

    @GetMapping("/department-list")
    public List<Department> getListDepartment() {
        return departmentService.findAll();
    }

    @GetMapping("/user-list")
    public ResponseEntity<Page<User>> getListUser(Pageable pageable) {
        Page<User> userList = userService.findAll(pageable);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/user-search")
    @ResponseBody
    public List<User> getSearchList(@RequestParam("keyword") String keyword) {
        return userService.searchUser(keyword);
    }

    @GetMapping("/get-user/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PatchMapping("/user-update/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user) {
        String checkEmail = user.getEmail();
        String name;
        String email;
        Long departmentId;
        String avatar;
        User userFindById = userService.findById(userId);
        if (userFindById == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Người dùng không tồn tại."));
        }
        if (!userService.findById(userId).getEmail().equals(checkEmail) && !userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email đã tồn tại. Vui lòng sử dụng email khác."));
        }
        UserDto userDto = new UserDto();
        if (user.getName() != null) {
            userDto.setName(standardized(user.getName()));
        }
        name = userDto.getName();
        userDto.setEmail(user.getEmail());
        email = userDto.getEmail();
        userDto.setDepartment(user.getDepartment());
        departmentId = userDto.getDepartment();
        userDto.setAvatar(user.getAvatar());
        avatar = userDto.getAvatar();
        userService.updateUser(email, name, departmentId, avatar, userId);
        userService.updateRole(user.getRoles(), userId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/user-update-avatar/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> updateUserAvatar(@PathVariable("id") Long userId, @RequestBody String avatar) {
        User userFindById = userService.findById(userId);
        if (userFindById == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Người dùng không tồn tại."));
        }
        userService.saveAvatar(avatar, userId);
        return ResponseEntity.ok(new MessageResponse("Cập nhật ảnh thành công."));
    }

    @DeleteMapping("/user-delete/{id}")
    @ResponseBody
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable("id") Long id) {
        String date = String.valueOf(LocalDateTime.now());
        String checkdate = "";
        checkdate = date.replace("T", " ");
        checkdate = checkdate.substring(0, 19);
        System.out.println(checkdate);
        if (userService.userIsDelete(id, checkdate).size() != 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Người dùng không thể xóa !"));
        }
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
        userDto.setDepartment(user.getDepartment());
        userService.save(userDto.getEmail(), userDto.getName(),
                userDto.getPassword(), userDto.getUsername(),
                userDto.getDepartment(), userStatus);
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

