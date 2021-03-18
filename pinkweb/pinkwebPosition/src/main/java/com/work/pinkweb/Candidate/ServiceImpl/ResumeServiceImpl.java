package com.work.pinkweb.Candidate.ServiceImpl;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.work.pinkweb.Candidate.Mapper.EditMapper;
import com.work.pinkweb.Candidate.Mapper.LoginMapper;
import com.work.pinkweb.Candidate.Mapper.RegisterMapper;
import com.work.pinkweb.Candidate.Mapper.ResumeMapper;
import com.work.pinkweb.Candidate.Service.ResumeService;
import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.Resume;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.utils.FileUtil;
import com.work.pinkweb.utils.WordTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private EditMapper editMapper;
    @Override
    public String uploadResumePDF(Integer uid, String name, String url, Date created_time, MultipartFile file) {
        Integer count = resumeMapper.countResumePDF(uid);
        if(count>=3){
            return "已经上传了三份简历";
        }else{
            try {
                FileUtil.uploadFile(file.getBytes(), url, name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resumeMapper.uploadResumePDF(uid, name, url, created_time);
            return "uploadResumePDF-success";
        }
    }

    @Override
    public List<Resume> findAllResumePDF(Integer c_id) {return resumeMapper.findAllResumePDF(c_id);}

    @Override
    public String deleteResumePDF(Integer r_id) {
        Resume resume = resumeMapper.findResumePDF(r_id);
        resumeMapper.deleteResumePDF(r_id);
        FileSystemUtils.deleteRecursively(new File(resume.getUrl()+"//"+resume.getName()));
        return "deleteResumePDF-success";
    }

    @Override
    public String resumePreview(Integer m_id, String phone) throws IOException {
        User user = loginMapper.userLogin(phone);
        Candidate candidate = editMapper.getCandidateInfo(user.getId());
        Map<String, Object> wordDataMap = new HashMap<String, Object>();  // 存储报表全部数据
        Map<String, Object> parametersMap = new HashMap<String, Object>();// 存储报表中不循环的数据
        String sex = "";
        if(user.getSex()==1){
            sex = "女";
        }else{
            sex = "男";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(user.getBirthday());
        //替换段落标签
        String[] info_flag=   {"name","position","birthday","sex","phone","email","edu1start","edu1last","edu1school","edu1subject","edu2start","edu2last","edu2school","edu2subject","wor1start","wor1last","wor1company","wor1position","wor1duty","wor2start","wor2last","wor2company","wor2position","wor2duty","selfIntroduction"};
        String[] info_addr= {user.getName(),"java工程师",sdf.format(user.getBirthday()),sex,user.getPhone(),user.getEmail(),sdf.format(candidate.getC_ftime()),sdf.format(candidate.getC_stime()),candidate.getC_fschool(),candidate.getC_fmajor(),sdf.format(candidate.getC_stime()),sdf.format(candidate.getC_stime()),candidate.getC_sschool(),candidate.getC_smajor(),sdf.format(candidate.getC_finternship_stime()),sdf.format(candidate.getC_finternship_etime())
                ,candidate.getC_finternship_enterprise(),candidate.getC_finternship_job(),"下载后填写",sdf.format(candidate.getC_sinternship_stime()),sdf.format(candidate.getC_sinternship_etime()),candidate.getC_sinternship_enterprise(),candidate.getC_sinternship_job(),"下载后填写",candidate.getC_intro()};
        for(int i=0;i<info_flag.length;i++) {
            parametersMap.put(info_flag[i], info_addr[i]);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式
        parametersMap.put("outputTime", df.format(new Date()));

        //替换段落图片
        String[] pic_flag=   {"avator"};
        String[] pic_addr= {"file\\avator.jpg"};
        for(int i=0;i<pic_flag.length;i++) {
            parametersMap.put(pic_flag[i], pic_addr[i]);
        }

        wordDataMap.put("parametersMap", parametersMap);

        File file = new File("file\\resume_" + m_id.toString() + "_new.docx");   //可改成你本地模板文件所在目录

        // 读取word模板
        FileInputStream fileInputStream = new FileInputStream(file);
        WordTemplate template = new WordTemplate(fileInputStream);

        // 替换数据
        template.replaceDocument(wordDataMap);


        //生成文件
        File dir = new File("target\\classes\\static\\out\\"+user.getPhone());
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();
        }
        File outputFile = new File("target\\classes\\static\\out\\"+user.getPhone()+"\\" + m_id.toString() + ".docx");  //可改成你想要模板文件所在目录
        FileOutputStream fos = new FileOutputStream(outputFile);
        template.getDocument().write(fos);
        fos.flush();
        fos.close();

        File outputFilePdf = new File("target\\classes\\static\\out\\" +user.getPhone()+"\\"+ m_id.toString() + ".pdf");
        File outputFileWord = new File("target\\classes\\static\\out\\" +user.getPhone()+"\\"+ m_id.toString() + ".docx");  //可改成你想要模板文件所在目录
        try {
            InputStream docxInputStream = new FileInputStream(outputFileWord);
            OutputStream outputStream = new FileOutputStream(outputFilePdf);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            outputStream.flush();
            outputStream.close();
            docxInputStream.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String resumeDownload(HttpServletResponse response, Integer r_id) {
        Resume resume = resumeMapper.findResumePDF(r_id);
        String path = resume.getUrl()+"/"+resume.getName();
        File file = new File(path);
        // 文件名称
        String filename = file.getName();
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //判断文件父目录是否存在
            if (file.exists()) {
                //设置返回文件信息
                response.setContentType("application/octet-stream;charset=UTF-8");
                // 将响应头中的Content-Disposition暴露出来 ， 不然前端获取不到
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                // 在响应头中的Content-Disposition里设置文件名称
                response.setHeader("Content-Disposition","attachment;filename="+filename);
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "resumeDownload-success";
    }

    @Override
    public String resumeTmpDownload(HttpServletResponse response, Integer m_id, String phone) {
        File file = new File("target\\classes\\static\\out\\" +phone.toString()+"\\"+ m_id.toString() + ".docx");
        // 文件名称
        String filename = file.getName();
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //判断文件父目录是否存在
            if (file.exists()) {
                //设置返回文件信息
                response.setContentType("application/octet-stream;charset=UTF-8");
                // 将响应头中的Content-Disposition暴露出来 ， 不然前端获取不到
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                // 在响应头中的Content-Disposition里设置文件名称
                response.setHeader("Content-Disposition","attachment;filename="+filename);
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
