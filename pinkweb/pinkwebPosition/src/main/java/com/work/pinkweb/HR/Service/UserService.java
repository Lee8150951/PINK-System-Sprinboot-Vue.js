package com.work.pinkweb.HR.Service;

import com.work.pinkweb.Entity.User;

public interface UserService {
    Integer updateAvatarById(Integer uid, String avatar);
    User getUserById(Integer uid);
}
