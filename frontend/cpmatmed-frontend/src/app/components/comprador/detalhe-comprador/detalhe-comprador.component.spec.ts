import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheCompradorComponent } from './detalhe-comprador.component';

describe('DetalheCompradorComponent', () => {
  let component: DetalheCompradorComponent;
  let fixture: ComponentFixture<DetalheCompradorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetalheCompradorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


