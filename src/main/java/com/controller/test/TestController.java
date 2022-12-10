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
@RequestMapping("manager/tests")
public class TestController {
    @Autowired
    private ITestService testService;

    @GetMapping("")
    public ResponseEntity<Iterable<Test>> getAll() {
        Iterable<Test> testList = testService.findAll();
        System.out.println(testList);
        return new ResponseEntity<>(testList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Test> add(@RequestBody Test test) {
        return new ResponseEntity<>(testService.save(test), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> findById(@PathVariable Long id) {
        Optional<Test> optional = testService.findById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
//        Optional<Wallet> walletDelete = walletService.findById(id);
//        if (!walletDelete.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        walletDelete.get().setStatus(0);
//        walletService.save(walletDelete.get());
//        return new ResponseEntity<>(walletDelete.get(), HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Wallet> editWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
//        Optional<Wallet> walletOptional = walletService.findById(id);
//        if (!walletOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        wallet.setId(walletOptional.get().getId());
//        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
//    }
}
