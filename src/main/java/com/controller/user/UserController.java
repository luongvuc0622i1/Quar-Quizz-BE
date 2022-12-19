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

    @PutMapping("changePassword/{id}")
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

    @GetMapping("user")
    public ResponseEntity<Iterable<AppUser>> getAll() {
        Iterable<AppUser> userList = userService.findAll();
        System.out.println(userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("manager/getUserRoles")
    public ResponseEntity<Iterable<AppUser>> getAppUserByRoleUser() {
        Iterable<AppUser> userList = userService.findAppUserByRolesUser();
        System.out.println(userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("admin/getUserManagerRoles")
    public ResponseEntity<Iterable<AppUser>> getAppUserByRoleUserManager() {
        Iterable<AppUser> userList = userService.findAppUserByRolesUserManager();
        System.out.println(userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    @PostMapping("admin/lock/{id}")
    public ResponseEntity <AppUser> lockAccountByIdUser(@PathVariable Long id){
        Optional<AppUser> user = userService.findById(id);
        if(user.isPresent()) {
            userService.lockAccountById(id);
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }
    @PostMapping("admin/open/{id}")
    public ResponseEntity <AppUser> openAccountByIdUser(@PathVariable Long id){
        Optional<AppUser> user = userService.findById(id);
        if(user.isPresent()) {
            userService.openAccountById(id);
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }
    @GetMapping("changeManager")
    public ResponseEntity<Iterable<AppUser>> changeManager(@RequestParam ("name") String name){
        Iterable<AppUser> user=userService.changeManager(name);
        if(!user.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
