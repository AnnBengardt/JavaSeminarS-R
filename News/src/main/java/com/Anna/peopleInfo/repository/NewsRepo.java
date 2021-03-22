package com.Anna.peopleInfo.repository;

import com.Anna.peopleInfo.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepo extends JpaRepository<News, Long> {
}
