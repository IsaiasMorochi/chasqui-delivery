package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.CompanyService;
import com.imorochi.chasqui.domain.document.Company;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void save(@RequestBody final Company company) {
        companyService.save(company);
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable final Integer id) {
        return companyService.findById(id);
    }
}
