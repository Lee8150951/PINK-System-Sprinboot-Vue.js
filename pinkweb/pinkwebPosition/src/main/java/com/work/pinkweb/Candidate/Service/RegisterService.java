package com.work.pinkweb.Candidate.Service;

import java.util.Date;

public interface RegisterService {
    Integer candidateRegister(String phone,String password);
    Integer perfectCandidateInfo(String phone, String name, Integer sex, Date birthday);
}
