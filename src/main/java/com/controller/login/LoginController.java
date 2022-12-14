package com.controller.login;


import com.model.dto.JwtResponse;
import com.model.dto.LoginForm;
import com.model.dto.Mail;
import com.model.dto.TokenDto;
import com.model.jwt.AppUser;
import com.model.jwt.MessageResponse;
import com.model.jwt.Role;
import com.service.jwt.JwtService;
import com.service.jwt.role.IRoleService;
import com.service.jwt.user.IUserService;
import com.service.mail.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMailService mailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token mới
            String token = jwtService.createToken(authentication);
            AppUser user1 = userService.getUserByUsername(loginForm.getUsername());
            JwtResponse jwtResponse = new JwtResponse(user1.getId(), user1.getUsername(), token, user1.getRoles());
            AppUser account = userService.findByUsername(loginForm.getUsername());

            if (account.getStatus() != null) {
                String status = account.getStatus();
                if ("0".equals(status)) {
                    return ResponseEntity.ok(new MessageResponse("lock"));
                }
            }

            return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AppUser user) {
        if (userService.getUserByUsername(user.getUsername()) == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(3L).get());
            user.setRoles(roles);
            AppUser appUser = userService.save(user);
            Mail mail = new Mail();
            mail.setMailTo(user.getEmail());
            mail.setMailFrom("quarquizteam@gmail.com");
            mail.setMailSubject("Thanks for signing up.");
            mail.setMailContent("Hello " + user.getUsername() + "," + "\n\nThank you for signing up for our team!" +
                    "We are looking forward to seeing you there.\n\n" +
                    "Best, \n" +
                    "Quarquizzteam");
            mailService.sendEmail(mail);
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        } if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("nouser"));
        } if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("noemail"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/oauth/fb")
    public ResponseEntity<JwtResponse> facebook(@RequestBody TokenDto tokenDto) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String[] fields = {"email", "name"};
        User user = facebook.fetchObject("me", User.class, fields);
        String userName = user.getName();
        AppUser userFace = new AppUser();
        if (userService.getUserByUsername(userName) != null) {
            userFace = userService.getUserByUsername(userName);
        } else {
            userFace.setUsername(user.getName());
            userFace.setPassword("123456");
            userFace.setEmail(user.getEmail());
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(2L).get());
            userFace.setRoles(roles);
        }
        userService.save(userFace);
        LoginForm loginForm = new LoginForm(userFace.getUsername(), userFace.getPassword());
        ResponseEntity<JwtResponse> jwtResponseResponseEntity = (ResponseEntity<JwtResponse>) login(loginForm);
        return jwtResponseResponseEntity;
    }

}
