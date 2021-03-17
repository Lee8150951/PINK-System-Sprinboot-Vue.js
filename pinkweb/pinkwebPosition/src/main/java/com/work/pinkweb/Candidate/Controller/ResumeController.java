package com.work.pinkweb.Candidate.Controller;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.work.pinkweb.Candidate.Service.RegisterService;
import com.work.pinkweb.Candidate.Service.ResumeService;
import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.Resume;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.utils.FileUtil;
import com.work.pinkweb.utils.WordTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    //上传简历pdf
    @PostMapping("/index/apply_edit/uploadResumePDF")
    public String uploadResumePDF(@RequestParam("file") MultipartFile file, @RequestParam(value = "c_id") Integer c_id) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("ss");//设置日期格式

        String fileName = df.format(new Date())+file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath("static/userpdf/");
        return resumeService.uploadResumePDF(c_id,fileName,filePath +"/"+c_id.toString(),new Date(),file);

    }

    //获取简历列表
    @RequestMapping(value = "/index/apply_edit/getallresume/{c_id}", method = RequestMethod.GET)
    public List<Resume> findAllResumePDF(@PathVariable Integer c_id) {return resumeService.findAllResumePDF(c_id);}

    //删除上传的简历
    @RequestMapping(value = "/index/apply_resume/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteResumePDF(@RequestBody Map<String, Map> para) {
        Integer r_id = Integer.valueOf(para.get("data").get("r_id").toString());
        return resumeService.deleteResumePDF(r_id);
    }

    //获取简历模板预览
    @RequestMapping(value = "/index/apply_resume/{m_id}", method = RequestMethod.GET)
    public String resumePreview(@PathVariable Integer m_id,@RequestParam("phone") String phone) throws IOException {
        return resumeService.resumePreview(m_id,phone);
    }

    //下载简历
    @RequestMapping(value = "/index/apply_resume/download", method = RequestMethod.GET)
    public String resumeDownload(HttpServletResponse response, @RequestParam("r_id") Integer r_id){return resumeService.resumeDownload(response,r_id);}

    //下载简历模板
    @RequestMapping(value = "/index/apply_resume/downloadtmp", method = RequestMethod.GET)
    public String resumeTmpDownload(HttpServletResponse response,@RequestParam("m_id") Integer m_id,@RequestParam("phone") String phone){return resumeService.resumeTmpDownload(response,m_id,phone);}

}
