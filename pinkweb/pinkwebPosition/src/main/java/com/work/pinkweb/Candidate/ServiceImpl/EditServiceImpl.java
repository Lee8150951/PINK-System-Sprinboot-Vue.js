package com.work.pinkweb.Candidate.ServiceImpl;

import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.Candidate.Mapper.EditMapper;
import com.work.pinkweb.Candidate.Service.EditService;
import com.work.pinkweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class EditServiceImpl implements EditService {
    @Autowired
    private EditMapper editMapper;
    @Override
    public Candidate getCandidateInfo(Integer id) {
        return editMapper.getCandidateInfo(id);
    }

    @Override
    public Integer modifyUserInfo(User user) {
        return editMapper.modifyUserInfo(user);
    }

    @Override
    public Integer modifyCandidateInfo(Candidate candidate) {
        return editMapper.modifyCandidateInfo(candidate);
    }

    @Override
    public String updateAvatar(Integer uid, String name, String url, Date created_time, MultipartFile file) {
        try {
            FileUtil.uploadFile(file.getBytes(), url, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        editMapper.avatarUpdate(uid, name);
        return "updateAvatar-success";
    }


}
