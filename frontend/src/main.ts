import { bootstrapApplication } from '@angular/platform-browser';
import { ProjectListComponent } from './app/components/project-list/project-list.component';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

bootstrapApplication(ProjectListComponent, {
  providers: [
    importProvidersFrom(HttpClientModule) // ✅ this makes HttpClient injectable
  ]
});
