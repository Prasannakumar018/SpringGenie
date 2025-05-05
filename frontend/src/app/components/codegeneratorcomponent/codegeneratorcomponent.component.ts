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

  // submitForm(): void {
  //   const payload = {
  //     entityName: this.entityName,
  //     fields: this.fields
  //   };

  //   this.http.post<any>('/api/generate', payload).subscribe((res) => {
  //     this.generatedCode = res;
  //   });
  // }


  submitForm(): void {
    // Dynamically create a prompt based on entity name and fields
    let prompt = `Generate Java Spring code for an entity class, model, repository, and service.`;

    prompt += `\nEntity Name: ${this.entityName}\n`;
    prompt += `Fields:\n`;

    this.fields.forEach(field => {
        prompt += `- Name: ${field.name}, Type: ${field.type}\n`;
    });

    // Prepare the payload with the generated prompt
    const payload = {
        prompt: prompt  // Send the generated prompt
    };

    console.log("Generated prompt:", prompt);

    // Make the HTTP POST request to the backend
    this.http.post<any>('http://localhost:8080/api/code/generate', payload)
        .subscribe({
            next: (res) => {
                // Extract the generated code (model, repository, service, controller) from the response
                const generatedCodeText = res.candidates[0]?.content?.parts[0]?.text;

                if (generatedCodeText) {
                    // You can parse the generated text into separate sections if needed
                    // For example, splitting by headings or markers (e.g., // Model, // Service)
                    this.generatedCode = {
                        model: this.extractCodeBlock(generatedCodeText, 'Model'),
                        repository: this.extractCodeBlock(generatedCodeText, 'Repository'),
                        service: this.extractCodeBlock(generatedCodeText, 'Service'),
                        controller: this.extractCodeBlock(generatedCodeText, 'Controller')
                    };
                } else {
                    console.error('No generated code found in response');
                }
            },
            error: (err) => {
                console.error('Error occurred while generating code:', err);
            }
        });
}

// Helper function to extract code based on the label (Model, Repository, etc.)
extractCodeBlock(text: string, section: string): string {
    const regex = new RegExp(`// ${section}(.*?)//`, 's');
    const match = text.match(regex);
    return match ? match[1].trim() : '';
}



  capitalizeFirstLetter(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }
}