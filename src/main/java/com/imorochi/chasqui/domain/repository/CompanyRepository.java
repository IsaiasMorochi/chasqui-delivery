package com.imorochi.chasqui.domain.repository;

import com.imorochi.chasqui.domain.document.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CompanyRepository extends ElasticsearchRepository<Company, Integer> {
}
