import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../services/project.service';
import { Project } from '../../models/project.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CodeGeneratorComponent } from '../codegeneratorcomponent/codegeneratorcomponent.component';  // Import the CodeGeneratorComponent
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CodeGeneratorComponent,MatFormFieldModule,  // Add MatFormFieldModule
    MatInputModule,      // Add MatInputModule
    MatButtonModule,     // Add MatButtonModule
    MatIconModule  ],
  // Include it here in imports
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {
  projects: Project[] = [];
  projectForm: Project = { name: '', description: '' };
  isEditMode = false;

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    this.loadProjects();
  }

  loadProjects(): void {
    this.projectService.getAll().subscribe(data => this.projects = data);
  }

  saveProject(): void {
    if (this.isEditMode && this.projectForm.id) {
      this.projectService.update(this.projectForm).subscribe(() => {
        this.loadProjects();
        this.resetForm();
      });
    } else {
      this.projectService.create(this.projectForm).subscribe(() => {
        this.loadProjects();
        this.resetForm();
      });
    }
  }

  editProject(project: Project): void {
    this.projectForm = { ...project };
    this.isEditMode = true;
  }

  deleteProject(id: number): void {
    this.projectService.delete(id).subscribe(() => this.loadProjects());
  }

  resetForm(): void {
    this.projectForm = { name: '', description: '' };
    this.isEditMode = false;
  }
}
