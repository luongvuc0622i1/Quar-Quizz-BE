package com.controller.mail;

import com.model.dto.Mail;
import com.model.jwt.AppUser;
import com.service.jwt.user.IUserService;
import com.service.mail.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/sender")
    public ResponseEntity<Mail> forgetPassword (
//            @RequestBody Optional<AppUser> appUser
            ) {
        Mail mail = new Mail();
        mail.setMailFrom("quarquizteam@gmail.com");
        mail.setMailTo("luong.vu1012@gmail.com");
        mail.setMailSubject("Password Change");
        mail.setMailContent("You recently changed the password associated with your account \n" +
                "If you did not make this change and believe your Quarquiz account has been compromised, please contact support.");

        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<Mail> acceptRegistration(@RequestParam String email, @RequestParam String username) {
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setMailFrom("quarquizteam@gmail.com");
        mail.setMailSubject("Thanks for signing up.");
        mail.setMailContent("Hello " + username + "," + "\n\nThank you for signing up for our team!" +
                "We are looking forward to seeing you there.\n\n" +
                "Best, \n" +
                "Quarquizzteam");
        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }

    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestParam String username) {
        Optional<AppUser> user = Optional.ofNullable(userService.findByUsername(username));
        if (!user.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        double randomDouble = Math.random();
        randomDouble = randomDouble *1000000 + 1;
        int OTP = (int) randomDouble;
        user.get().setOtp(String.valueOf(OTP));
        userService.save(user.get());
        Mail mail = new Mail();
        mail.setMailTo(user.get().getEmail());
        mail.setMailFrom("quarquizteam@gmail.com");
        mail.setMailSubject("Password Reset");
        mail.setMailContent("Hi " + username + ",\n\n" +
                "OTP code " + OTP + " is valid for 1 time to change password, used to authenticate the password change request at Quar Quizz website. " +
                "For security reasons, do not share this OTP with anyone! \n\n" +
                "Best, \n" +
                "Quarquizzteam");
        mailService.sendEmail(mail);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/confirmOtp")
    public ResponseEntity<?> confirmOtp(@RequestParam String otp, String email) {
        Optional<AppUser> user = Optional.ofNullable(userService.findAppUserByEmail(email));
        if (!user.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        if (otp.equals(user.get().getOtp())) {
            user.get().setPassword(userService.randomPassword());
            user.get().setOtp(null);
            userService.save(user.get());
            Mail mail = new Mail();
            mail.setMailTo(email);
            mail.setMailFrom("quarquizteam@gmail.com");
            mail.setMailSubject("Reset Password Successfully");
            mail.setMailContent("Hello " + user.get().getUsername() + ",\n\n" +
                    "We are sorry about your problem!\n" +
                    "New Password: " + user.get().getPassword() + "\n" +
                    "Please change Your password! \n\n" +
                    "Best,\n" +
                    "Quarquizzteam");
            mailService.sendEmail(mail);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("wrong otp", HttpStatus.OK);
        }
    }

}
