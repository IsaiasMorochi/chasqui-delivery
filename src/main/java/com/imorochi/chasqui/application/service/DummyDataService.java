package com.imorochi.chasqui.application.service;

import org.springframework.stereotype.Service;

import static com.imorochi.chasqui.infrastructure.mock.DATA.*;

@Service
public class DummyDataService {

    private final CategoryService categoryService;
    private final TagService tagService;
    private final CompanyService companyService;
    private final ProductService productService;

    public DummyDataService(CategoryService categoryService, TagService tagService, CompanyService companyService, ProductService productService) {
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.companyService = companyService;
        this.productService = productService;
    }

    public void insertDummyData() {

        insertCategories();

        insertTags();

        insertCompanies();

        insertProducts();
    }

    private void insertProducts() {
        productService.save(PRODUCT_1);
        productService.save(PRODUCT_2);
        productService.save(PRODUCT_3);
        productService.save(PRODUCT_4);
        productService.save(PRODUCT_5);
        productService.save(PRODUCT_6);
        productService.save(PRODUCT_7);
        productService.save(PRODUCT_8);
        productService.save(PRODUCT_9);
        productService.save(PRODUCT_10);
        productService.save(PRODUCT_11);
        productService.save(PRODUCT_12);
        productService.save(PRODUCT_13);
        productService.save(PRODUCT_14);
        productService.save(PRODUCT_15);
        productService.save(PRODUCT_16);
    }

    private void insertCompanies() {
        companyService.save(COMPANY_1);
        companyService.save(COMPANY_2);
        companyService.save(COMPANY_3);
        companyService.save(COMPANY_4);
        companyService.save(COMPANY_5);
        companyService.save(COMPANY_6);
        companyService.save(COMPANY_7);
        companyService.save(COMPANY_8);
        companyService.save(COMPANY_9);
        companyService.save(COMPANY_10);
        companyService.save(COMPANY_11);
        companyService.save(COMPANY_12);
        companyService.save(COMPANY_13);
        companyService.save(COMPANY_14);
        companyService.save(COMPANY_15);
    }

    private void insertTags() {
        tagService.save(TAG_1);
        tagService.save(TAG_2);
        tagService.save(TAG_3);
        tagService.save(TAG_4);
        tagService.save(TAG_5);
        tagService.save(TAG_6);
        tagService.save(TAG_7);
        tagService.save(TAG_8);
        tagService.save(TAG_9);
        tagService.save(TAG_10);
        tagService.save(TAG_11);
        tagService.save(TAG_12);
        tagService.save(TAG_13);
        tagService.save(TAG_14);
        tagService.save(TAG_15);
        tagService.save(TAG_16);
        tagService.save(TAG_17);
        tagService.save(TAG_18);
    }

    private void insertCategories() {
        categoryService.save(CATEGORY_1);
        categoryService.save(CATEGORY_2);
        categoryService.save(CATEGORY_3);
        categoryService.save(CATEGORY_4);
        categoryService.save(CATEGORY_5);
        categoryService.save(CATEGORY_6);
        categoryService.save(CATEGORY_7);
        categoryService.save(CATEGORY_8);
        categoryService.save(CATEGORY_9);
        categoryService.save(CATEGORY_10);
        categoryService.save(CATEGORY_11);
        categoryService.save(CATEGORY_12);
        categoryService.save(CATEGORY_13);
        categoryService.save(CATEGORY_14);
        categoryService.save(CATEGORY_15);
        categoryService.save(CATEGORY_16);
    }

}
