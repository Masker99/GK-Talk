package com.rookie.gktalk;

import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GktalkApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void testRedis() {
        User user = userService.selectUserByUserName("joke");
        System.out.println(user.toString());

        User user1 = userService.selectUserByUserName("joke");
        System.out.println(user1.toString());
    }

}
