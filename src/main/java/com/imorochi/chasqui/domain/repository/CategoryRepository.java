package com.imorochi.chasqui.domain.repository;

import com.imorochi.chasqui.domain.document.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryRepository extends ElasticsearchRepository<Category, Integer> {
}
