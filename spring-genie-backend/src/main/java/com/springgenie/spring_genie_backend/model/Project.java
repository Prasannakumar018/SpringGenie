package com.springgenie.spring_genie_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
        @NotBlank
		private String name;
		@NotBlank
		private String description;
		private LocalDate createdAt;
}