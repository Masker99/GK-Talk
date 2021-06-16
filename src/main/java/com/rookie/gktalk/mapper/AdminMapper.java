package com.rookie.gktalk.mapper;

import com.rookie.gktalk.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    int addAdmin(Admin admin);
    Admin searchAdmin(int admin_id);
}
