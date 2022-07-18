package com.imorochi.chasqui.domain.repository;

import com.imorochi.chasqui.domain.document.Tag;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TagRepository extends ElasticsearchRepository<Tag, Integer> {
}
