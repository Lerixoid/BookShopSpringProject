package com.mas.project.Repository;

import com.mas.project.Model.Book;
import com.mas.project.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
