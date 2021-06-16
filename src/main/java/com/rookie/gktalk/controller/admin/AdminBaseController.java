package com.rookie.gktalk.controller.admin;

import com.rookie.gktalk.services.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminBaseController {
    @Autowired
    AdminServiceImpl adminService;

    @RequestMapping("/login")
    public Object adminLogin(@RequestBody Map<String,String> body,
                             HttpServletRequest request){


        return null;
    }
}
