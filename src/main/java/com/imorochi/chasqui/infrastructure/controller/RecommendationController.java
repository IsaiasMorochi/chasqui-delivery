package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.service.RecommendationService;
import com.imorochi.chasqui.domain.document.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@CrossOrigin
@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping(value = "/recommend")
    public ResponseEntity<Recommendation> recommend() {
        try {
            var recomendation= recommendationService.recommend();
            return new ResponseEntity<>(recomendation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
