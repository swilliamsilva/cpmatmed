import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheFornecedorComponent } from './detalhe-fornecedor.component';

describe('DetalheFornecedorComponent', () => {
  let component: DetalheFornecedorComponent;
  let fixture: ComponentFixture<DetalheFornecedorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetalheFornecedorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
