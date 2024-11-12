package com.example.lab9_2021345.controller;

import com.example.lab9_2021345.dao.MealDao;
import com.example.lab9_2021345.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MealController {

    final MealDao mealDao;

    @Autowired
    public MealController(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    @GetMapping("/categories")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", mealDao.listarCategorias());
        return "meal/categories"; // Vista para ver las categorías
    }
    @GetMapping("/search")
    public String buscarRecetas(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            List<Meal> recetas = mealDao.buscarRecetaPorNombre(nombre);
            model.addAttribute("recetas", recetas);
        }
        return "meal/search";
    }

    @GetMapping("/meal/details/{idMeal}")
    public String verDetallesReceta(@PathVariable("idMeal") String idMeal, Model model) {
        Meal meal = mealDao.obtenerRecetaPorId(idMeal);
        model.addAttribute("meal", meal);
        return "meal/details";
    }


    @PostMapping("/favorites/add")
    public String addFavorite(@RequestParam String mealId, Model model) {
        // Obtener el Meal por el ID
        Meal meal = mealDao.obtenerRecetaPorId(mealId);

        if (meal != null) {
            mealDao.addMealToFavorites(meal);
            model.addAttribute("favoriteAdded", true);
        } else {
            model.addAttribute("favoriteAdded", false);
        }
        return "redirect:/meal/details?mealId=" + mealId + "&favoriteAdded=true";
    }

}
