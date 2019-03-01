package com.example.demo.service;

import com.example.demo.entity.RiskAssessor;

import org.springframework.stereotype.Service;

@Service
public class DatabaseAccountService {
    private final RiskAssessor riskAssessor;

    /**这里的riskAssessor必须在SpringApplication中有注册过的bean*/
    public DatabaseAccountService(RiskAssessor riskAssessor) {
        this.riskAssessor = riskAssessor;
    }
}
