package com.mas.project.Repository;

import com.mas.project.Model.Category;
import com.mas.project.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
