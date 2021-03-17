package com.work.pinkweb.Candidate.Service;

import com.work.pinkweb.Entity.Resume;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ResumeService {
    String uploadResumePDF(Integer uid, String name, String url, Date created_time, MultipartFile file);
    List<Resume> findAllResumePDF(Integer c_id);
    String deleteResumePDF(Integer r_id);
    String resumePreview(Integer m_id,String phone) throws IOException;
    String resumeDownload(HttpServletResponse response, Integer r_id);
    String resumeTmpDownload(HttpServletResponse response, Integer m_id,String phone);
}
