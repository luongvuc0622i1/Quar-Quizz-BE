package com.controller.user;

import com.model.dto.ChangPasswordDTO;
import com.model.jwt.AppUser;
import com.service.jwt.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<AppUser> changePassword(@PathVariable Long id, @Valid @RequestBody ChangPasswordDTO changPasswordDTO) {
        Optional<AppUser> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (userOptional.get().getPassword().equals(changPasswordDTO.getOldPassword())) {
            userOptional.get().setPassword(changPasswordDTO.getNewPassword());
            return new ResponseEntity<>(userService.save(userOptional.get()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable Long id) {
        Optional<AppUser> optional = userService.findById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

}
