package com.imorochi.chasqui.infrastructure.controller;

import com.imorochi.chasqui.application.service.ProductService;
import com.imorochi.chasqui.application.search.SearchRequestDTO;
import com.imorochi.chasqui.domain.document.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void save(@RequestBody final Product product) {
        productService.save(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable final Integer id) {
        return productService.findById(id);
    }

    @PostMapping("/search")
    public List<Product> search(@RequestBody final SearchRequestDTO dto) {
        return productService.search(dto);
    }

}
