package com.imorochi.chasqui.application.service;

import com.imorochi.chasqui.domain.document.Company;
import com.imorochi.chasqui.domain.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void save(final Company company) {
        companyRepository.save(company);
    }

    public Company findById(final Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

}
