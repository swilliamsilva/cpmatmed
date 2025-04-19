import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroCompradorComponent } from './cadastro-comprador.component';

describe('CadastroCompradorComponent', () => {
  let component: CadastroCompradorComponent;
  let fixture: ComponentFixture<CadastroCompradorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroCompradorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
