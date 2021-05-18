package com.rookie.gktalk.controller;

import com.rookie.gktalk.services.Impl.MailServiceImpl;
import com.rookie.gktalk.utils.common.Result;
import com.rookie.gktalk.utils.common.StringUtil;
import com.rookie.gktalk.utils.validate.DataAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    MailServiceImpl mailService;

    @GetMapping("/invitation")
    public Object postEmail(@RequestBody Map<String,String> RequestBody, HttpServletRequest request){
        String receiver = RequestBody.get("receiver");

        DataAssert.isTrue(StringUtil.isValid(receiver,StringUtil.EMAIL_REGEX),"请输入正确的邮件！");

        String invitationCode = mailService.sendMailForInvitation(receiver);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("invitationCode",invitationCode);

        return new Result(200,"成功发送邮件",null);
    }
}
