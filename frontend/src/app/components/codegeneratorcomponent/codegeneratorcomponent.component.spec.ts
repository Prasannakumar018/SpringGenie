import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CodeGeneratorComponent } from './codegeneratorcomponent.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

describe('CodegeneratorcomponentComponent', () => {
  let component: CodeGeneratorComponent;
  let fixture: ComponentFixture<CodeGeneratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommonModule, FormsModule],
      declarations: [CodeGeneratorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CodeGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
