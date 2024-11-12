package com.example.lab9_2021345.dao;

import com.example.lab9_2021345.entity.Category;
import com.example.lab9_2021345.entity.CategoriesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class MealDao {
    public List<Category> listarCategorias() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        ResponseEntity<CategoriesResponse> response = restTemplate.getForEntity(url, CategoriesResponse.class);
        return response.getBody().getCategories();
    }
}