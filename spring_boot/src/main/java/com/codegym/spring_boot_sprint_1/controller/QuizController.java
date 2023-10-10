package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.*;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.model.dto.QuizDto;
import com.codegym.spring_boot_sprint_1.model.dto.ResultDto;
import com.codegym.spring_boot_sprint_1.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api/quiz")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class QuizController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizCategoryService quizCategoryService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ResultService resultService;

    @GetMapping("/quiz-category-list")
    public ResponseEntity<Page<QuizCategory>> getQuizCategoryList(Pageable pageable) {
        Page<QuizCategory> categoryList = quizCategoryService.findAll(pageable);
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/quiz-category-list/search")
    public ResponseEntity<Page<QuizCategory>> searchQuizCategory(@RequestParam("keyword") String keyword, Pageable pageable) {
        Page<QuizCategory> quizCategoryList = quizCategoryService.searchByName(keyword, pageable);
        if (quizCategoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizCategoryList, HttpStatus.OK);
    }

    @GetMapping("/quiz-list/search")
    public ResponseEntity<List<Quiz>> searchQuiz(@RequestParam("keyword") String keyword, Pageable pageable) {
        List<Quiz> quizList = quizService.searchByKeyword(keyword, pageable).getContent();
        if (quizList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @GetMapping("/get-quiz-category/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public QuizCategory findQuizCategory(@PathVariable("id") Long id) {
        return quizCategoryService.findById(id);
    }

    @GetMapping("/quiz-list")
    public ResponseEntity<Page<Quiz>> getQuizList(Pageable pageable) {
        Page<Quiz> quizList = quizService.findAll(pageable);
        if (quizList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }


    @PostMapping("/quiz-category-save")
    public ResponseEntity<MessageResponse> saveQuizCategory(@RequestBody QuizCategory quizCategory) {
        quizCategoryService.save(quizCategory);
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @GetMapping("/get-quiz/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Quiz findQuestionById(@PathVariable("id") Long id) {
        return quizService.findById(id);
    }

    @PostMapping("/quiz-save-without-question")
    public Quiz saveQuiz(@RequestBody QuizDto quizDto) {
        Quiz quiz = new Quiz(quizDto.getTitle(), quizDto.getDescription(),quizDto.getTimer(),
                quizDto.getUnitScore(), quizDto.getTargetScore(), LocalDateTime.now(),
                quizCategoryService.findById(quizDto.getQuizCategory()),quizDto.isEnabled());
        return quizService.save(quiz);
    }

    @PatchMapping("/quiz-update/{id}")
    public ResponseEntity<MessageResponse> updateQuizAndChooseQuestion(@PathVariable("id") Long id, @RequestBody Set<Question> questionList) {
        Quiz quiz = quizService.findById(id);
        quiz.setQuestions(questionList);
        quizService.save(quiz);
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @DeleteMapping("/quiz-delete/{id}")
    @ResponseBody
    public ResponseEntity<MessageResponse> deleteQuiz(@PathVariable("id") Long id) {
        String date = String.valueOf(LocalDateTime.now());
        String checkdate = "";
        checkdate = date.replace("T", " ");
        checkdate = checkdate.substring(0, 19);
        System.out.println(checkdate);
        quizService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công !"));
    }


    @PostMapping("/result-save")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> saveQuizCategory(@RequestBody ResultDto resultDto) {
        System.out.println("saved result");

        Quiz quiz = quizService.findById(resultDto.getQuiz());
        resultService.save(new Result(resultDto.getUsername(), quiz, LocalDateTime.now(), resultDto.getScores()));
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @GetMapping("/result-list/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Result>> getResults(@PathVariable("id") Long quizId, Pageable pageable) {
        Quiz quiz = quizService.findById(quizId);
        Page<Result> results = resultService.findByQuizId(quiz,pageable);
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
