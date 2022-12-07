package com.service.test;

import com.model.Test;
import com.repository.test.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService implements ITestService {
    @Autowired
    private ITestRepository testRepository;

    @Override
    public Iterable<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    public Test save(Test model) {
        return testRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        testRepository.deleteById(id);
    }
}
