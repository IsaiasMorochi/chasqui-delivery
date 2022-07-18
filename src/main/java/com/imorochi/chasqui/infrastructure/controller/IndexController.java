package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.DummyDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class IndexController {

    private final DummyDataService dummyDataService;

    public IndexController(DummyDataService dummyDataService) {
        this.dummyDataService = dummyDataService;
    }

    @PostMapping("/insertdummydata")
    public void insertDummyData() {
        dummyDataService.insertDummyData();
    }
}
