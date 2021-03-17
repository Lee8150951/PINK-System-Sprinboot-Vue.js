package com.work.pinkweb.Company.Controller;

import com.work.pinkweb.Company.Service.CoCompanyService;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class CoCompanyController {
    @Autowired
    private CoCompanyService coCompanyService;
    //更新公司logo
    @PostMapping("/company/profile/avatar")
    public String uploadComanyLogo(HttpServletRequest request, @RequestParam("file") MultipartFile file , @RequestParam("e_id")Integer e_id ){
        System.out.println(e_id);

        //1.保存到硬盘上
        String fileName=file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath("static/companyAvatar/");
        fileName=System.currentTimeMillis()+fileName;

        try{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date created_time = new Date();
        coCompanyService.updateCompanyAvatar(e_id,fileName);
        return "上传成功";
    }
    //上传公司的许可证
    @PostMapping("/company/profile/licenseImg")
    public String uploadLicenseImg( @RequestParam("file") MultipartFile file , @RequestParam("e_id")Integer e_id ){

        System.out.println(e_id);

        //1.保存到硬盘上
        String fileName=file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath("static/LicenseImg/");
        fileName=e_id.toString()+System.currentTimeMillis()+e_id.toString()+fileName;

        try{
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date created_time = new Date();

        coCompanyService.uploadLicenseImg(e_id,fileName);

        return "上传成功";
    }

    @RequestMapping("/company/profile/update")
    public String updateCompany(@RequestBody Company company){
        coCompanyService.updateCompanyInfo(company);
        return "更新成功";
    }
}
