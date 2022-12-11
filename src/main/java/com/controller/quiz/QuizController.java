package com.controller.quiz;

import com.model.Category;
import com.model.Level;
import com.model.Quiz;
import com.model.TypeQuiz;
import com.service.quiz.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.CardTerminal;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    public IQuizService quizService;

    @GetMapping("")
    public ResponseEntity<Iterable<Quiz>> getAll() {
        Iterable<Quiz> testList = quizService.findAll();
        System.out.println(testList);
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Quiz>> showPageQuiz(@PageableDefault(value = 10) Pageable pageable) {
        Page<Quiz> quizzes = quizService.findQuizPage(pageable);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> QuizOptional = quizService.findById(id);
        return QuizOptional.map(Quiz -> new ResponseEntity<>(Quiz, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> editQuiz(@PathVariable Long id, @RequestBody Quiz Quiz) {
        Optional<Quiz> QuizOptional = quizService.findById(id);
        if (!QuizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz.setId(id);
        quizService.save(Quiz);
        return new ResponseEntity<>(Quiz, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable Long id) {
        Optional<Quiz> QuizOptional = quizService.findById(id);
        if (!QuizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quizService.remove(id);
        return new ResponseEntity<>(QuizOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.save(quiz), HttpStatus.CREATED);
    }


//    @GetMapping("/category")
//    public ResponseEntity<Page<Category>> getCategory(Pageable pageable) {
//        Page<Category> quizCategories = quizService.findAllCategory(pageable);
//        if (!quizCategories.iterator().hasNext()) {
//            new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(quizCategories, HttpStatus.OK);
//    }

    @GetMapping("/type")
    public ResponseEntity<Iterable<TypeQuiz>> getQuizType() {
        Iterable<TypeQuiz> quizTypes = quizService.findAllTypeQuiz();
        if (!quizTypes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizTypes, HttpStatus.OK);
    }

    @GetMapping("/level")
    public ResponseEntity<Page<Level>> getQuizLevel(Pageable pageable) {
        Page<Level> quizLevel = quizService.findAllLevel(pageable);
        if (!quizLevel.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizLevel, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<Iterable<Quiz>> getQuizByName(@RequestParam ("name") String name) {
        Iterable<Quiz> quizzes = quizService.findAllQuizByName(name);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/searchName")
    public ResponseEntity<Page<Quiz>> searchPageQuiz(@PageableDefault(value = 10) Pageable pageable, @RequestParam("name") String name) {
        Page<Quiz> quizzes = quizService.findQuizByNameContaining(name, pageable);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/searchType")
    public ResponseEntity<Iterable<Quiz>> getQuizByType(@RequestParam("type") String type) {
        Iterable<Quiz> quizzes = quizService.findQuizByTypeContaining(type);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/searchCategory")
    public ResponseEntity<Iterable<Quiz>> getQuizByCategory(@RequestParam("category") String category) {
        Iterable<Quiz> quizzes = quizService.findQuizByCategoryContaining(category);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/searchLevel")
    public ResponseEntity<Iterable<Quiz>> getQuizByLevel(@RequestParam("level") String level) {
        Iterable<Quiz> quizzes = quizService.findQuizByLevelContaining(level);
        if (!quizzes.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
}
