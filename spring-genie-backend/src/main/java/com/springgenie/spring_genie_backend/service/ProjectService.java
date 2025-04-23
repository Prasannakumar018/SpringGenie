package com.springgenie.spring_genie_backend.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springgenie.spring_genie_backend.model.Project;
import com.springgenie.spring_genie_backend.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository repository;

	public Project create(Project p) { return repository.save(p); }
	public List<Project> findAll() { return repository.findAll(); }
	public Optional<Project> findById(Long id) { return repository.findById(id); }
	public void deleteById(Long id) { repository.deleteById(id); }
}
