//package com.chainsys.movieticket.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.chaninsys.movieticket.util.EmailUtil;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class EmailController {
//
//    @Autowired
//    EmailUtil emailUtil;
//
//    @GetMapping("/sendEmail")
//    public String sendEmail(HttpSession session, @RequestParam("email") String toEmail) {
//         System.out.println("from mail"+toEmail);
//        String subject = "Email Verification for Parking Reservation in Park Smart";
//        String body = "Dear user,\n\n" + "Thank you for reserving a parking spot with us.\n\n"
//                + "Please click on the following link to verify your email address and confirm your reservation:\n"
//                + "https://example.com/verify?email=" + toEmail + "\n\n"
//                + "If you have any questions or concerns, feel free to contact us.\n\n" + "Best regards,\n"
//                + "Park Smart Team";
//
//        emailUtil.sendVerificationEmail(toEmail, subject, body);
//
//        return "home";
//    }
//
//}
//
