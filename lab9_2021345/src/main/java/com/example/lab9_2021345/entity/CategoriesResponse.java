package com.example.lab9_2021345.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriesResponse {
    private List<Category> categories;
}
