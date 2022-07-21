package com.imorochi.chasqui.infrastructure.mock;

import com.imorochi.chasqui.domain.document.Category;
import com.imorochi.chasqui.domain.document.Company;
import com.imorochi.chasqui.domain.document.Product;
import com.imorochi.chasqui.domain.document.Tag;

import java.util.List;

public final class DATA {

    public static final Category CATEGORY_1 = new Category(1, "Pizza");
    public static final Category CATEGORY_2 = new Category(2, "Comida Nacional");
    public static final Category CATEGORY_3 = new Category(3, "Comida Intenacional");
    public static final Category CATEGORY_4 = new Category(4, "Abarrote");
    public static final Category CATEGORY_5 = new Category(5, "Bebida");
    public static final Category CATEGORY_6 = new Category(6, "Carne");
    public static final Category CATEGORY_7 = new Category(7, "Embutido");
    public static final Category CATEGORY_8 = new Category(8, "Fruta");
    public static final Category CATEGORY_9 = new Category(9, "Verdura");
    public static final Category CATEGORY_10 = new Category(10, "Lácteo");
    public static final Category CATEGORY_11 = new Category(11, "Huevo");
    public static final Category CATEGORY_12 = new Category(12, "Panaderia");
    public static final Category CATEGORY_13 = new Category(13, "Artículos de higiene personal");
    public static final Category CATEGORY_14 = new Category(14, "Artículos de limpieza");
    public static final Category CATEGORY_15 = new Category(15, "Alimento para mascotas");
    public static final Category CATEGORY_16 = new Category(16, "Electronicos");

    public static final Tag TAG_1 = new Tag(1, "Educación");
    public static final Tag TAG_2 = new Tag(2, "Video Juegos");
    public static final Tag TAG_3 = new Tag(3, "Entretenimiento");
    public static final Tag TAG_4 = new Tag(4, "Res");
    public static final Tag TAG_5 = new Tag(5, "Pollo");
    public static final Tag TAG_6 = new Tag(6, "Cerdo");
    public static final Tag TAG_7 = new Tag(7, "Con Alcohol");
    public static final Tag TAG_8 = new Tag(8, "Sin Alcohol");
    public static final Tag TAG_9 = new Tag(9, "Vegetales Frescos");
    public static final Tag TAG_10 = new Tag(10, "Perro");
    public static final Tag TAG_11 = new Tag(11, "Gato");
    public static final Tag TAG_12 = new Tag(12, "Escoba");
    public static final Tag TAG_13 = new Tag(13, "Libre de aceite");
    public static final Tag TAG_14 = new Tag(14, "Cables");
    public static final Tag TAG_15 = new Tag(15, "Accesorio");
    public static final Tag TAG_16 = new Tag(16, "Nuevo");
    public static final Tag TAG_17 = new Tag(17, "Usado");
    public static final Tag TAG_18 = new Tag(18, "Comida Rapida");


    public static final Company COMPANY_1 = new Company(1, "Pizzerias");
    public static final Company COMPANY_2 = new Company(2, "Restaurantes");
    public static final Company COMPANY_3 = new Company(3, "Heladerias");
    public static final Company COMPANY_4 = new Company(4, "Supermercados");
    public static final Company COMPANY_5 = new Company(5, "Mercados");
    public static final Company COMPANY_6 = new Company(6, "Almacenes");
    public static final Company COMPANY_7 = new Company(7, "PetStores");
    public static final Company COMPANY_8 = new Company(8, "Burger King");
    public static final Company COMPANY_9 = new Company(9, "Pollos Chriss");
    public static final Company COMPANY_10 = new Company(10, "Toby");
    public static final Company COMPANY_11 = new Company(11, "Vikingo Comida Rapida");
    public static final Company COMPANY_12 = new Company(12, "Pollos Kiky");
    public static final Company COMPANY_13 = new Company(13, "Hot Burger");
    public static final Company COMPANY_14 = new Company(14, "Pollos Campeón");
    public static final Company COMPANY_15 = new Company(15, "Pollos Chuy");


    public static final Product PRODUCT_1 = new Product(1, CATEGORY_16, "Acer Aspire Laptop", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_16));
    public static final Product PRODUCT_2 = new Product(2, CATEGORY_16, "HP Laptop", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_17));
    public static final Product PRODUCT_3 = new Product(3, CATEGORY_16, "Asus Laptop", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_16));
    public static final Product PRODUCT_4 = new Product(4, CATEGORY_16, "HP pavilon Laptop 2022", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_5 = new Product(5, CATEGORY_16, "Dell Laptop Windows 11", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_6 = new Product(6, CATEGORY_16, "Lenovo Windows 10", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_7 = new Product(7, CATEGORY_16, "ThinkPad Windows 11", "1000", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_8 = new Product(8, CATEGORY_2, "Pollo 3/4", "15", "path/url/imagen.png", "available", List.of(TAG_5, TAG_18));
    public static final Product PRODUCT_9 = new Product(9, CATEGORY_1, "Pizza Americana", "66", "path/url/imagen.png", "available", List.of(TAG_5, TAG_18));
    public static final Product PRODUCT_10 = new Product(10, CATEGORY_13, "Jabon Ace", "5", "path/url/imagen.png", "available", List.of(TAG_12));
    public static final Product PRODUCT_11 = new Product(11, CATEGORY_2, "Pollo a la Braza", "32", "path/url/imagen.png", "available", List.of(TAG_5, TAG_18));
    public static final Product PRODUCT_12 = new Product(12, CATEGORY_2, "Pollo al Spiedo", "25", "path/url/imagen.png", "available", List.of(TAG_5, TAG_18));
    public static final Product PRODUCT_13 = new Product(13, CATEGORY_13, "Champu", "13", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_14 = new Product(14, CATEGORY_16, "Portatil Lenovo", "2450", "path/url/imagen.png", "available", List.of(TAG_2, TAG_3, TAG_16));
    public static final Product PRODUCT_15 = new Product(15, CATEGORY_1, "Pizza Napolitana", "56", "path/url/imagen.png", "available", List.of(TAG_18));
    public static final Product PRODUCT_16 = new Product(16, CATEGORY_16, "Huevo Criollo", "10", "path/url/imagen.png", "available", List.of(TAG_5, TAG_16));

}
