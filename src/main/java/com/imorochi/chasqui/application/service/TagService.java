package com.imorochi.chasqui.application.service;

import com.imorochi.chasqui.domain.document.Tag;
import com.imorochi.chasqui.domain.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void save(final Tag tag) {
        tagRepository.save(tag);
    }

    public Tag findById(final Integer id) {
        return tagRepository.findById(id).orElse(null);
    }

}
