import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaCompradorComponent } from './lista-comprador.component';

describe('ListaCompradorComponent', () => {
  let component: ListaCompradorComponent;
  let fixture: ComponentFixture<ListaCompradorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaCompradorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
