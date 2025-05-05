package com.springgenie.spring_genie_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springgenie.spring_genie_backend.model.Project;
import com.springgenie.spring_genie_backend.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")

public class ProjectController {
	@Autowired
	private ProjectService service;

	@PostMapping
	public Project create(@RequestBody Project p) { return service.create(p); }

	@GetMapping
	public List<Project> all() { return service.findAll(); }

	@GetMapping("/{id}")
	public ResponseEntity<Project> get(@PathVariable Long id) {
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) { service.deleteById(id); }



}
