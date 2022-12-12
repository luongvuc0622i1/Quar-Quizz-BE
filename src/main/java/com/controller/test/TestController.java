package com.controller.test;

import com.model.Category;
import com.model.Test;
import com.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class TestController {
    @Autowired
    private ITestService testService;

    @GetMapping("user/tests")
    public ResponseEntity<Iterable<Test>> getAll() {
        Iterable<Test> testList = testService.findAll();
        System.out.println(testList);
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }

    @PostMapping("manager/tests")
    public ResponseEntity<Test> add(@RequestBody Test test) {
        return new ResponseEntity<>(testService.save(test), HttpStatus.CREATED);
    }

    @GetMapping("user/tests/{id}")
    public ResponseEntity<Test> findById(@PathVariable Long id) {
        Optional<Test> optional = testService.findById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }
}
