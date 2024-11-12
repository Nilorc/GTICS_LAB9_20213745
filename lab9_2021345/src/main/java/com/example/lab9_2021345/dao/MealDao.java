package com.example.lab9_2021345.dao;

import com.example.lab9_2021345.entity.Category;
import com.example.lab9_2021345.entity.CategoriesResponse;
import com.example.lab9_2021345.entity.Meal;
import com.example.lab9_2021345.entity.MealResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    public List<Meal> buscarRecetaPorNombre(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + nombre;
        ResponseEntity<MealResponse> response = restTemplate.getForEntity(url, MealResponse.class);
        return response.getBody().getMeals();
    }

    public Meal obtenerRecetaPorId(String idMeal) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + idMeal;
        ResponseEntity<MealResponse> response = restTemplate.getForEntity(url, MealResponse.class);

        Meal meal = response.getBody().getMeals() != null && !response.getBody().getMeals().isEmpty() ? response.getBody().getMeals().get(0) : null;

        if (meal != null) {
            List<String> ingredients = new ArrayList<>();
            // Acceder directamente a los campos de ingredientes
            for (int i = 1; i <= 20; i++) {
                try {
                    Field ingredientField = Meal.class.getDeclaredField("strIngredient" + i);
                    ingredientField.setAccessible(true);
                    String ingredientValue = (String) ingredientField.get(meal);
                    if (ingredientValue != null && !ingredientValue.isEmpty()) {
                        ingredients.add(ingredientValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            meal.setIngredients(ingredients);
        }

        return meal;
    }

}