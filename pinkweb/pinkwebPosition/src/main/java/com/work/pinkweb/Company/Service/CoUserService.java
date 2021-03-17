package com.work.pinkweb.Company.Service;
import com.work.pinkweb.Entity.User;

public interface CoUserService {
    Integer insertOneUser(User user);
    User getByPhone(String phone);
}
