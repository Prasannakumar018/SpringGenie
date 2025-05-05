// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';  // Required for Material components
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { CodeGeneratorComponent } from './components/codegeneratorcomponent/codegeneratorcomponent.component';
import { HttpClientModule } from '@angular/common/http';


// import { AppComponent } from './app.component';

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,  // Include this module
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    FormsModule,
    ProjectListComponent,   // Add this to make the component available
    CodeGeneratorComponent,
    HttpClientModule
  ],
  providers: [],
  // Removed bootstrap array as AppComponent is standalone
})
export class AppModule { }
