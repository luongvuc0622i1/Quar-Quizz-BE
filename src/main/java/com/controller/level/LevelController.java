package com.controller.level;

import com.model.Level;
import com.model.Test;
import com.service.level.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private ILevelService levelService;

    @GetMapping("")
    public ResponseEntity<Iterable<Level>> getAll() {
        Iterable<Level> levelList = levelService.findAll();
        System.out.println(levelList);
        return new ResponseEntity<>(levelList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> findById(@PathVariable Long id) {
        Optional<Level> optional = levelService.findById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }
}
