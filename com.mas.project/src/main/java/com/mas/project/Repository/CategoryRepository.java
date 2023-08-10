package com.mas.project.Repository;

import com.mas.project.Model.Book;
import com.mas.project.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

