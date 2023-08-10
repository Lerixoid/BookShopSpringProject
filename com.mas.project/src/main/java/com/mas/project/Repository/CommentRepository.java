package com.mas.project.Repository;

import com.mas.project.Model.Book;
import com.mas.project.Model.Category;
import com.mas.project.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

