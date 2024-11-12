package com.example.lab9_2021345.controller;

import com.example.lab9_2021345.dao.MealDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
