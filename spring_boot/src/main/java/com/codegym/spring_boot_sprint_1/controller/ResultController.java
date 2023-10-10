package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Quiz;
import com.codegym.spring_boot_sprint_1.model.Result;
import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.model.dto.ResultDto;
import com.codegym.spring_boot_sprint_1.service.impl.QuizService;
import com.codegym.spring_boot_sprint_1.service.impl.ResultService;
import com.codegym.spring_boot_sprint_1.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api/results")
public class ResultController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserServiceImpl userService;


}
