package com.controller.exam;

import com.model.ExamQuiz;
import com.model.ExamTest;
import com.service.exam.IExamQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/examQuiz")
public class ExamQuizController {

    @Autowired
    private IExamQuizService examQuizService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<ExamQuiz>> getAllExamQuiz (){
        List<ExamQuiz> examQuizList= (List<ExamQuiz>) examQuizService.findAll();
        if(examQuizList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(examQuizList,HttpStatus.OK);
    }

    @GetMapping("/findExamQuizById/{id}")
    public ResponseEntity<ExamQuiz> findExamQuizById(@PathVariable Long id) {
        Optional<ExamQuiz> examQuizOptional = examQuizService.findById(id);
        return examQuizOptional.map(ExamQuiz -> new ResponseEntity<>(ExamQuiz, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/create")
    public ResponseEntity<ExamQuiz> createExamQuiz(@RequestBody ExamQuiz examQuiz) {
        return new ResponseEntity<>(examQuizService.saveExamQuiz(examQuiz), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ExamQuiz> editExamQuiz(@PathVariable Long id, @RequestBody ExamQuiz examQuiz) {
        Optional<ExamQuiz> examQuizOptional = examQuizService.findById(id);
        if (!examQuizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examQuiz.setId(id);
        examQuizService.saveExamQuiz(examQuiz);
        return new ResponseEntity<>(examQuiz, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ExamQuiz> deleteExamQuiz(@PathVariable Long id) {
        Optional<ExamQuiz> examQuizOptional = examQuizService.findById(id);
        if (!examQuizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examQuizService.remove(id);
        return new ResponseEntity<>(examQuizOptional.get(), HttpStatus.NO_CONTENT);
    }

}
