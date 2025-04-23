import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'app-code-generator',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule
  ],
  templateUrl: './codegeneratorcomponent.component.html',
  styleUrls: ['./codegeneratorcomponent.component.scss']
})
export class CodeGeneratorComponent {
  entityName: string = '';
  fields: { name: string; type: string }[] = [{ name: '', type: 'String' }];

  generatedCode = {
    model: '',
    repository: '',
    service: '',
    controller: ''
  };

  constructor(private http: HttpClient) {}

  addField(): void {
    this.fields.push({ name: '', type: 'String' });
  }

  removeField(index: number): void {
    this.fields.splice(index, 1);
  }

  submitForm(): void {
    const payload = {
      entityName: this.entityName,
      fields: this.fields
    };

    this.http.post<any>('/api/generate', payload).subscribe((res) => {
      this.generatedCode = res;
    });
  }

  capitalizeFirstLetter(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }
}