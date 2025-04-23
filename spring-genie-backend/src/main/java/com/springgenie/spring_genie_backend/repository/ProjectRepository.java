package com.springgenie.spring_genie_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springgenie.spring_genie_backend.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}


