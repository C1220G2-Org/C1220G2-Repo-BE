package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.*;
import com.codegym.spring_boot_sprint_1.model.dto.CountBookingsPerMonthDto;
import com.codegym.spring_boot_sprint_1.model.dto.RoomBookingDto;
import com.codegym.spring_boot_sprint_1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/roombooking")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class RoomBookingController {
    @Autowired
    private IRoomBookingService roomBookingService;
    @Autowired
    private IMeetingRoomService meetingRoomService;
    @Autowired
    private IMeetingTypeService meetingTypeService;
    @Autowired
    private ICountBookingsPerMonthService countBookingsPerMonthService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoomBookingPendingService roomBookingPendingService;
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private SimpleEmailExampleController simpleEmailExampleController;
    @Autowired
    private NotificationController notificationController;

    @GetMapping("/{idMeetingRoom}")
    public ResponseEntity<List<RoomBookingDto>> getAllRoomBooking(@PathVariable Long[] idMeetingRoom) {
        List<RoomBooking> temp = new ArrayList<>();
        for (Long aLong : idMeetingRoom) {
            temp.addAll(this.roomBookingService.findAll(aLong));
        }
        List<RoomBookingDto> dtoList = new ArrayList<>();
        for (RoomBooking rb : temp) {
            RoomBookingDto roomBookingDto = new RoomBookingDto();
            roomBookingDto.setIdRoom(rb.getId());
            roomBookingDto.setDescription(rb.getUser().getName());
            roomBookingDto.setLocation("Phòng: " + rb.getMeetingRoom().getName() +
                    ". Tầng: " + rb.getMeetingRoom().getFloor());
            roomBookingDto.setSubject(rb.getContent());
            roomBookingDto.setColor(rb.getMeetingRoom().getColor());
            String tempSdt = rb.getStartDate();
            String tempSdt2 = tempSdt.replace(" ", "T");
            roomBookingDto.setStartTime(tempSdt2);
            String tempEdt = rb.getEndDate();
            String tempEdt2 = tempEdt.replace(" ", "T");
            roomBookingDto.setEndTime(tempEdt2);
            dtoList.add(roomBookingDto);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.
                OK);
    }

    @PostMapping("/postBookingRoomPending")
    public ResponseEntity<List<RoomBookingPending>> savePending(@RequestBody List<RoomBookingPending> list) {
        for (int i = 0; i < list.size(); i++) {
            roomBookingPendingService.save(list.get(i));
        }
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PostMapping("/postBookingRoomArray")
    public ResponseEntity<List<RoomBooking>> save(@RequestBody List<RoomBooking> list) {
        for (int i = 0; i < list.size(); i++) {
            roomBookingService.save(list.get(i));
        }
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @GetMapping("/findBookingRoomById/{id}")
    public ResponseEntity<List<RoomBooking>> getBookingRoomById(Long id) {
        List<RoomBooking> list = roomBookingService.findBookingRoomById(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ListMeetingRoom")
    public ResponseEntity<List<MeetingRoom>> listMeetingRoom() {
        List<MeetingRoom> list = meetingRoomService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ListMeetingType")
    public ResponseEntity<List<MeetingType>> istMeetingType() {
        List<MeetingType> list = meetingTypeService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/searchEmpty")
    public ResponseEntity<List<MeetingRoom>> search(@RequestParam(value = "meetingRoomId", defaultValue = "") String meetingRoomId,
                                                    @RequestParam(value = "meetingTypeId") Long meetingTypeId,
                                                    @RequestParam(value = "startDateVariable", defaultValue = "") String startDateVariable,
                                                    @RequestParam(value = "endDateVariable", defaultValue = "") String endDateVariable,
                                                    @RequestParam(value = "startHourVariable", defaultValue = "") String startHourVariable,
                                                    @RequestParam(value = "endHourVariable", defaultValue = "") String endHourVariable,
                                                    @RequestParam(value = "capacity") int capacity
    ) {
        List<MeetingRoom> list;
        String date1 = startDateVariable + " " + startHourVariable;
        String date2 = endDateVariable + " " + endHourVariable;
        if (meetingRoomId.equals("")) {
            list = meetingRoomService.listSearchEmpty(meetingRoomId, startDateVariable, endDateVariable, date1, date2, capacity);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            list = meetingRoomService.searchFullEmpty(meetingRoomId, startDateVariable, endDateVariable, date1, date2, capacity);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/searchArray")
    public ResponseEntity<List<RoomBooking>> searchArray(@RequestBody List<RoomBooking> list) {
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PostMapping("/saveCount")
    public ResponseEntity<CountBookingsPerMonth> saveCount(@RequestBody CountBookingsPerMonthDto countBookingsPerMonthDto) {
        CountBookingsPerMonth countBookingsPerMonth = new CountBookingsPerMonth();
        countBookingsPerMonth.setCount(countBookingsPerMonthDto.getCount());
        countBookingsPerMonth.setMonthYear(countBookingsPerMonthDto.getMonthYear());
        countBookingsPerMonth.setUser(countBookingsPerMonthDto.getUser());
        return new ResponseEntity<>(countBookingsPerMonthService.save(countBookingsPerMonth), HttpStatus.CREATED);
    }

    // lưu số lần đặt
    @PutMapping("/saveCount/{id}")
    public ResponseEntity<CountBookingsPerMonth> update(@PathVariable Long id, @RequestBody CountBookingsPerMonthDto countBookingsPerMonthDto) {
        Optional<CountBookingsPerMonth> present = countBookingsPerMonthService.findById(id);
        if (!present.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CountBookingsPerMonth countBookingsPerMonth = new CountBookingsPerMonth();
        countBookingsPerMonth.setCount(countBookingsPerMonthDto.getCount());
        countBookingsPerMonth.setMonthYear(countBookingsPerMonthDto.getMonthYear());
        countBookingsPerMonth.setUser(countBookingsPerMonthDto.getUser());
        countBookingsPerMonth.setId(present.get().getId());
        return new ResponseEntity<>(countBookingsPerMonthService.save(countBookingsPerMonth), HttpStatus.OK);
    }

    // đếm số lần đặt
    @GetMapping("/CountBookingsPerMonth")
    public ResponseEntity<CountBookingsPerMonth> countBookingsPerMonth(@RequestParam(value = "userId") Long userId,
                                                                       @RequestParam(value = "monthYear") String monthYear) {
        CountBookingsPerMonth object = countBookingsPerMonthService.findByUserAndAndMonthYearContains(userId, monthYear);
        if (object == null) {
            CountBookingsPerMonth countBookingsPerMonth = new CountBookingsPerMonth(1, monthYear, userService.findById(userId));
            countBookingsPerMonthService.save(countBookingsPerMonth);
            return new ResponseEntity<>(countBookingsPerMonth, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    // đồng ý email
    @GetMapping("/agree")
    public ResponseEntity<RoomBooking> agree(@RequestParam(value = "userId") Long userId,
                                             @RequestParam(value = "code") String code,
                                             @RequestParam(value = "startDateVariable", defaultValue = "") String startDateVariable,
                                             @RequestParam(value = "endDateVariable", defaultValue = "") String endDateVariable,
                                             @RequestParam(value = "startHourVariable", defaultValue = "") String startHourVariable,
                                             @RequestParam(value = "endHourVariable", defaultValue = "") String endHourVariable,
                                             @RequestParam(value = "meetingRoomName", defaultValue = "") String meetingRoomName,
                                             HttpServletResponse response) throws IOException, MessagingException {
        List<RoomBookingPending> list = roomBookingPendingService.findAllByCode(code);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        if (list.get(0).getStatus() == 1) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() != 0) {
                continue;
            }
            RoomBooking roomBooking = new RoomBooking();
            roomBooking.setUser(userService.findById(userId));
            roomBooking.setContent(list.get(i).getContent());
            roomBooking.setEndDate(list.get(i).getEndDate());
            roomBooking.setStartDate(list.get(i).getStartDate());
            roomBooking.setMeetingRoom(list.get(i).getMeetingRoom());
            roomBooking.setMeetingType(list.get(i).getMeetingType());
            roomBooking.setRegistrationDate(LocalDate.now().format(formatter));
            roomBookingService.save(roomBooking);
            list.get(i).setStatus(1);
            roomBookingPendingService.save(list.get(i));
        }
        simpleEmailExampleController.sendUserMail(userId, startDateVariable, endDateVariable, startHourVariable, endHourVariable, "được xác nhận", meetingRoomName);
        notificationService.save("Yêu cầu đặt phòng của bạn đã được xác nhận", "", userService.findById(userId).getId(), "lightskyblue", null);
//        Long[] notificationId = {userService.findById(userId).getId()};
//        notificationController.getNotification(notificationId);
        response.sendRedirect("http://localhost:4200/dat-phong-hop/agree/" + userId + "/" + startDateVariable + "/" + endDateVariable + "/" + startHourVariable + "/" + endHourVariable + "/" + meetingRoomName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/notagree")
    public ResponseEntity<RoomBooking> notagree(@RequestParam(value = "userId") Long userId,
                                                @RequestParam(value = "code") String code,
                                                @RequestParam(value = "startDateVariable", defaultValue = "") String startDateVariable,
                                                @RequestParam(value = "endDateVariable", defaultValue = "") String endDateVariable,
                                                @RequestParam(value = "startHourVariable", defaultValue = "") String startHourVariable,
                                                @RequestParam(value = "endHourVariable", defaultValue = "") String endHourVariable,
                                                @RequestParam(value = "meetingRoomName", defaultValue = "") String meetingRoomName,
                                                HttpServletResponse response) throws IOException, MessagingException {
        List<RoomBookingPending> list = roomBookingPendingService.findAllByCode(code);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        for (int i = 0; i < list.size(); i++) {
            roomBookingPendingService.deleteById(list.get(i).getId());
        }
        simpleEmailExampleController.sendUserMail(userId, startDateVariable, endDateVariable, startHourVariable, endHourVariable, "bị từ chối", meetingRoomName);
        notificationService.save("yêu cầu đặt phòng của bạn đã bị từ chối", "", userService.findById(userId).getId(), "lightskyblue", null);
        response.sendRedirect("http://localhost:4200/dat-phong-hop/notagree/" + userId + "/" + startDateVariable + "/" + endDateVariable + "/" + startHourVariable + "/" + endHourVariable + "/" + meetingRoomName);
        return new ResponseEntity<>(HttpStatus.LOOP_DETECTED);
    }

    //    Long code
    @Autowired
    private StatisticsDTOService statisticsDTOService;


    @GetMapping("/statistics")
    public ResponseEntity<Page<StatisticsDTO>> getListUserStatistic(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Pageable pageable) {
        Page<StatisticsDTO> userList = statisticsDTOService.statisticUser(startDate, endDate, pageable);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}