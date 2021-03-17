package com.work.pinkweb.Candidate.Controller;

import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.Candidate.Service.EditService;
import com.work.pinkweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class EdiController {
    @Autowired
    private EditService editService;

    @RequestMapping(value="/index/apply_edit/{c_id}",method= RequestMethod.GET)
    public Candidate getCandidateInfo(@PathVariable Integer c_id ) {return editService.getCandidateInfo(c_id);}

    @PostMapping("/index/apply_edit/modify_personal_info")
    public Object modifyUserInfo(@RequestBody User user){
        return editService.modifyUserInfo(user);
    }

    @PostMapping("/index/apply_edit/modify_personal_candidate_info")
    public Object modifyCandidateInfo(@RequestBody Candidate candidate){return editService.modifyCandidateInfo(candidate);}

    //上传用户头像
    @PostMapping("/index/apply_profile/updateAvatar")
    public String avatarUpdate(@RequestParam("file") MultipartFile file, @RequestParam(value = "c_id") Integer c_id) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath("static/avatar/");
        return editService.updateAvatar(c_id,fileName,filePath + "/"+c_id.toString(),new Date(),file);
    }

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}
