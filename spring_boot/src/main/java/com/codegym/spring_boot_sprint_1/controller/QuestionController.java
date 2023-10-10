package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Category;
import com.codegym.spring_boot_sprint_1.model.Option;
import com.codegym.spring_boot_sprint_1.model.Question;
import com.codegym.spring_boot_sprint_1.model.dto.MessageResponse;
import com.codegym.spring_boot_sprint_1.model.dto.OptionDto;
import com.codegym.spring_boot_sprint_1.model.dto.QuestionDto;
import com.codegym.spring_boot_sprint_1.service.impl.CategoryService;
import com.codegym.spring_boot_sprint_1.service.impl.OptionService;
import com.codegym.spring_boot_sprint_1.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api/questions")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OptionService optionService;

    @GetMapping("/category-list")
    public ResponseEntity<Page<Category>> getCategoryList(Pageable pageable) {
        Page<Category> categoryList = categoryService.findAll(pageable);
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/category-list/search")
    public ResponseEntity<Page<Category>> searchCategory(@RequestParam("keyword") String keyword, Pageable pageable) {
        Page<Category> categoryList = categoryService.searchByName(keyword, pageable);
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/question-list/search")
    public ResponseEntity<List<Question>> searchQuestion(@RequestParam("keyword") String keyword, @RequestParam("categoryId") Long categoryId) {
        List<Question> questionList = questionService.searchByKeywordAndCategoryId(keyword, categoryId);
        if (questionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @GetMapping("/question-list/search-by-category-id")
    public ResponseEntity<List<Question>> searchQuestionByCategoryId(@RequestParam("categoryId") Long categoryId) {
        List<Question> questionList = questionService.searchQuestionByCategoryId(categoryId);
        if (questionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @GetMapping("/get-category/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Category findCategory(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/question-list")
    public ResponseEntity<Page<Question>> getQuestionList(Pageable pageable) {
        Page<Question> questionList = questionService.findAll(pageable);
        if (questionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }


    @PostMapping("/category-save")
    public ResponseEntity<MessageResponse> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @GetMapping("/get-question/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Question findQuestionById(@PathVariable("id") Long id) {
        return questionService.findById(id);
    }

    @PostMapping("/question-save")
    public ResponseEntity<MessageResponse> saveQuestion(@RequestBody QuestionDto questionDto) {
        Question question = questionService.save(new Question(questionDto.getTitle(), questionDto.getCorrectOption(), questionDto.getType(), LocalDateTime.now(), true,
                categoryService.findById(questionDto.getCategory())));
        List<OptionDto> list = questionDto.getOptions();
        List<Option> optionList = new ArrayList<>();
        Option option;
        for (int i = 0; i < list.size(); i++) {
            option = new Option(list.get(i).getName());
            optionList.add(optionService.save(option));
        }
        question.setOptions(optionList);
        questionService.save(question);
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @PostMapping("/option-save")
    public ResponseEntity<MessageResponse> saveOption(@RequestBody Option option) {
        optionService.save(option);
        return ResponseEntity.ok(new MessageResponse("Thêm mới thành công !"));
    }

    @DeleteMapping("/question-delete/{id}")
    @ResponseBody
    public ResponseEntity<MessageResponse> deleteQuestion(@PathVariable("id") Long id) {
        String date = String.valueOf(LocalDateTime.now());
        String checkdate = "";
        checkdate = date.replace("T", " ");
        checkdate = checkdate.substring(0, 19);
        System.out.println(checkdate);
        questionService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công !"));
    }
}
