package com.controller.user;

import com.model.dto.ChangPasswordDTO;
import com.model.dto.Mail;
import com.model.jwt.AppUser;
import com.service.jwt.user.IUserService;
import com.service.jwt.user.UserService;
import com.service.mail.IMailService;
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

    @Autowired
    private IMailService mailService;

    @PutMapping("user/changePassword/{id}")
    public ResponseEntity<AppUser> changePassword(@PathVariable Long id, @Valid @RequestBody ChangPasswordDTO changPasswordDTO) {
        Optional<AppUser> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (userOptional.get().getPassword().equals(changPasswordDTO.getOldPassword())) {
            userOptional.get().setPassword(changPasswordDTO.getNewPassword());
            userService.save(userOptional.get());
            Mail mail = new Mail();
            mail.setMailTo(userOptional.get().getEmail());
            mail.setMailFrom("quarquizteam@gmail.com");
            mail.setMailSubject("Password Change Successfully");
            mail.setMailContent("Hi " + userOptional.get().getUsername() + ",\n\n" +
                    "Your password has been changed successfully!\n" +
                    "Thank you for using our service, enjoy it!\n\n" +
                    "Best,\n" +
                    "quarquizzteam");
            mailService.sendEmail(mail);
            return new ResponseEntity<>(userOptional.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("user/{id}")
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
    @Autowired
    private UserService userService1;
    @PutMapping("admin/changeManager")
    public ResponseEntity <Optional <AppUser>> changeManager(@RequestParam("name") String name){
        Optional<AppUser> user=userService1.findByUser(name);

        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService1.changeManager(name);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("admin/changeUser")
    public ResponseEntity <Optional <AppUser>> changeUser(@RequestParam("name") String name){
        Optional<AppUser> user=userService1.findByUser(name);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService1.changeUser(name);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
