package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.*;
import com.codegym.spring_boot_sprint_1.model.dto.*;
import com.codegym.spring_boot_sprint_1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private FeedbackDto feedbackDto;
    @Autowired
    private IFeedbackService feedbackService;
    @Autowired
    private IMeetingRoomService meetingRoomService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IHandleFeedbackService handleFeedbackService;
    @Autowired
    private ITypeErrorService typeErrorService;
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private FeedbackEmailController feedbackEmailController;
    @Autowired
    private NotificationController notificationController;

    @GetMapping("meeting-dto-list")
    public ResponseEntity<List<FeedbackMeetingRoomDto>> getListMeetingRoom() {
        List<MeetingRoom> meetingRoomList = meetingRoomService.findAll();
        List<FeedbackMeetingRoomDto> feedbackMeetingRoomDtoList = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRoomList) {
            FeedbackMeetingRoomDto feedbackMeetingRoomDto = new FeedbackMeetingRoomDto();
            feedbackMeetingRoomDto.setId(meetingRoom.getId());
            feedbackMeetingRoomDto.setName(meetingRoom.getName());
            feedbackMeetingRoomDtoList.add(feedbackMeetingRoomDto);
        }
        return new ResponseEntity<>(feedbackMeetingRoomDtoList, HttpStatus.OK);
    }

    @GetMapping("user-dto-list")
    public ResponseEntity<List<UserFeedbackDto>> getListUser(Pageable pageable) {
        List<User> userList = userService.findAllByList();
        List<UserFeedbackDto> userFeedbackDtoList = new ArrayList<>();
        for (User user : userList) {
            UserFeedbackDto userFeedbackDto = new UserFeedbackDto();
            userFeedbackDto.setId(user.getId());
            userFeedbackDto.setUsername(user.getUsername());
            userFeedbackDtoList.add(userFeedbackDto);
        }
        return new ResponseEntity<>(userFeedbackDtoList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<FeedbackDto>> getAllFeedback(Pageable pageable) {
        List<Feedback> feedbackList = feedbackService.findAllFeedback();
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDto = new FeedbackDto();
            transformFeedback(feedback, feedbackDto);
            feedbackDtoList.add(feedbackDto);
        }
        Page<FeedbackDto> feedbackDtoPage = new PageImpl<>(feedbackDtoList, pageable, feedbackDtoList.size());
        return new ResponseEntity<>(feedbackDtoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findFeedbackById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Feedback feedback = feedbackOptional.get();
            feedbackDto = new FeedbackDto();
            transformFeedback(feedback, feedbackDto);
            return new ResponseEntity<>(feedbackDto, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createTechnicalFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setFeedbackTime(String.valueOf(LocalDateTime.now()));
        feedbackService.saveTechnicalFeedback(feedbackDto.getFeedbackTitle().trim(), feedbackDto.getFeedbackTime(), feedbackDto.getFeedbackContent().trim(),
                null, feedbackDto.isStatus(), feedbackDto.getFeedbackType(), feedbackDto.getMeetingRoom(),
                feedbackDto.getTypeError(), feedbackDto.getUserFeedback());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/handle-feedback/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<HandleFeedback> handleFeedback(@PathVariable Long id, @RequestBody HandleFeedbackDto handleFeedbackDto) throws MessagingException {
        Optional<Feedback> feedbackOptional = feedbackService.findFeedbackById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Feedback feedback = feedbackOptional.get();
            if (feedback.isStatus() == true) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            feedback.setStatus(true);
            String default_message = "Phản hồi về "+feedback.getFeedbackTitle().toLowerCase()+" của bạn đã được xử lý";
            String image = null;
            if (feedback.getImage() != null){
                image = String.join(",",handleFeedbackDto.getImage());
            }
            handleFeedbackService.save(handleFeedbackDto.getContent().trim(), image, id);
            notificationService.save(handleFeedbackDto.getContent().trim(), image, handleFeedbackDto.getUser(), "lightskyblue", id);
            notificationService.save(default_message, image, feedback.getUserFeedback().getId(), "lightskyblue", id);
            Long[] userId = {handleFeedbackDto.getUser(), feedback.getUserFeedback().getId()};
            notificationController.getNotification(userId);
            String title = feedback.getFeedbackTitle();
            String roomName = "Trống";
            String feedbackType = feedback.getFeedbackType().getName();
            String errorType = "Trống";
            String content = feedback.getFeedbackContent();
            String email = feedback.getUserFeedback().getEmail();
            if (feedback.getMeetingRoom() != null) {
                roomName = feedback.getMeetingRoom().getName();
            }
            if (feedback.getTypeError() != null) {
                errorType = feedback.getTypeError().getName();
            }
            User user2 = userService.findById(handleFeedbackDto.getUser());
            feedbackEmailController.sendHtmlEmail(title, roomName, feedbackType, errorType, content, email, user2.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FeedbackDto>> search(@RequestParam String keyWord,
                                                    @RequestParam(defaultValue = "") String status) {
        List<Feedback> feedbackList;
        if ("not".equals(status)) {
            feedbackList = feedbackService.searchNotStatus(keyWord);
        } else {
            feedbackList = feedbackService.search(keyWord, Boolean.parseBoolean(status));
        }
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDto = new FeedbackDto();
            transformFeedback(feedback, feedbackDto);
            feedbackDtoList.add(feedbackDto);
        }
        return new ResponseEntity<>(feedbackDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FeedbackDto> delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void transformFeedback(Feedback feedback, FeedbackDto feedbackDto) {
        feedbackDto.setId(feedback.getId());
        feedbackDto.setFeedbackTitle(feedback.getFeedbackTitle());
        feedbackDto.setFeedbackContent(feedback.getFeedbackContent());
        feedbackDto.setFeedbackTime(feedback.getFeedbackTime());
        if (feedback.getFeedbackType() == null) {
            feedbackDto.setFeedbackType(null);
        } else {
            feedbackDto.setFeedbackType(feedback.getFeedbackType().getId());
        }
        if (feedback.getImage() == null){
            feedbackDto.setImage(null);
        }else {
            String[] arrImage = feedback.getImage().split(",");
            feedbackDto.setImage(arrImage);
        }
        feedbackDto.setStatus(feedback.isStatus());
        if (feedback.getMeetingRoom() == null) {
            feedbackDto.setMeetingRoom(null);
        } else {
            feedbackDto.setMeetingRoom(feedback.getMeetingRoom().getId());
        }
        if (feedback.getTypeError() == null) {
            feedbackDto.setTypeError(null);
        } else {
            feedbackDto.setTypeError(feedback.getTypeError().getId());
        }
        if (feedback.getUserFeedback() == null) {
            feedbackDto.setUserFeedback(null);
        } else {
            feedbackDto.setUserFeedback(feedback.getUserFeedback().getId());
        }
    }

    @GetMapping("/list/type")
    public ResponseEntity<List<TypeError>> findAllTypeError() {
        List<TypeError> typeErrorList = typeErrorService.findAll();
        if (typeErrorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(typeErrorList, HttpStatus.OK);
        }
    }

    @GetMapping("/searchUser")
    public ResponseEntity<List<FeedbackDto>> search(@RequestParam(name = "userId") Long userId) {
        List<Feedback> feedbackList = feedbackService.searchUser(userId);
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDto = new FeedbackDto();
            transformFeedback(feedback, feedbackDto);
            feedbackDtoList.add(feedbackDto);
        }
        return new ResponseEntity<>(feedbackDtoList, HttpStatus.OK);
    }

    @PostMapping("/create/technical")
    public ResponseEntity<Feedback> create(@RequestBody FeedbackTechnicalDto feedbackTechnicalDto) {
        feedbackTechnicalDto.setFeedbackTime(String.valueOf(LocalDateTime.now()));
        Feedback feedback = new Feedback();
        feedback.setFeedbackTime(feedbackTechnicalDto.getFeedbackTime());
        String str = String.join(",", feedbackTechnicalDto.getImage());
        feedback.setImage(str);
        feedbackService.save(
                feedbackTechnicalDto.getFeedbackTitle(),
                feedbackTechnicalDto.getFeedbackTime(),
                feedbackTechnicalDto.getFeedbackContent(),
                feedback.getImage(),
                feedbackTechnicalDto.isStatus(),
                feedbackTechnicalDto.getFeedbackType(),
                feedbackTechnicalDto.getTypeError(),
                feedbackTechnicalDto.getUserFeedback()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}