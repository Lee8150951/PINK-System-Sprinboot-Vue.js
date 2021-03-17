package com.work.pinkweb.Candidate.Service;

import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface EditService {
    Candidate getCandidateInfo(Integer id);
    Integer modifyUserInfo(User user);
    Integer modifyCandidateInfo(Candidate candidate);

    String updateAvatar(Integer uid, String name, String url, Date created_time, MultipartFile file);
}
