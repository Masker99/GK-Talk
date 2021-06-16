package com.rookie.gktalk.services;

import com.rookie.gktalk.pojo.Admin;

public interface AdminService {
    int addOneAdmin(Admin admin);
    Admin searchAdmin(int admin_id);
}
