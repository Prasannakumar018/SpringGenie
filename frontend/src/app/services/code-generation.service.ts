import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodeGenerationService {

  private baseUrl = 'http://localhost:8080/api/code/generate';

  constructor(private http: HttpClient) {}

  generateCode(entityName: string): Observable<string> {
    const payload = {
      prompt: `Generate full backend code for entity: ${entityName}`
    };
    return this.http.post(this.baseUrl, payload, { responseType: 'text' });
  }
}
