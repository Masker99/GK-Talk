package com.rookie.gktalk.controller;

import com.rookie.gktalk.utils.common.VerificationCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerificationCodeController {
    @GetMapping("/verifyCode")
    public void getVerifiCode(HttpServletRequest request,HttpServletResponse response) {
        VerificationCode vc = new VerificationCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = request.getSession();
        session.setAttribute("captcha", text);

        try {
            VerificationCode.output(image, response.getOutputStream());
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
