package com.service.level;

import com.model.Level;
import com.repository.ILevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LevelService implements ILevelService {
    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public Iterable<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> findById(Long id) {
        return levelRepository.findById(id);
    }

    @Override
    public Level save(Level model) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
