package com.work.pinkweb.HR.Controller;

import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.HR.Service.RecruitmentService;
import com.work.pinkweb.HR.Service.UserService;
import com.work.pinkweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/hire/hire_profile/avatar")
    public String regUser(HttpServletRequest request, @RequestParam("file") MultipartFile file , @RequestParam("ehrid")Integer uid ){

        System.out.println(uid);

        //1.保存到硬盘上
        String fileName=file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath("static/hrAvatar/");
        fileName=System.currentTimeMillis()+fileName;

        try{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date created_time = new Date();

        userService.updateAvatarById(uid,fileName);
        System.err.println(userService.getUserById(uid));
        return "上传成功";



    }
}
