package com.rookie.gktalk.services.Impl;

import com.rookie.gktalk.mapper.AdminMapper;
import com.rookie.gktalk.pojo.Admin;
import com.rookie.gktalk.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public int addOneAdmin(Admin admin){
        return adminMapper.addAdmin(admin);
    }

    @Override
    public Admin searchAdmin(int admin_id){
        return adminMapper.searchAdmin(admin_id);
    }
}
