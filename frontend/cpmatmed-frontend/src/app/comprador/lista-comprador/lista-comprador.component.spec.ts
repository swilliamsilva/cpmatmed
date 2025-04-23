import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaCompradorComponent } from './lista-comprador.component';
import { By } from '@angular/platform-browser';

describe('ListaCompradorComponent', () => {
  let component: ListaCompradorComponent;
  let fixture: ComponentFixture<ListaCompradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaCompradorComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve ter uma lista de compradores com pelo menos dois elementos', () => {
    expect(component.compradores.length).toBeGreaterThanOrEqual(2);
  });

  it('deve renderizar todos os compradores no HTML', () => {
    const elementos = fixture.debugElement.queryAll(By.css('li.list-group-item'));
    expect(elementos.length).toBe(component.compradores.length);
    expect(elementos[0].nativeElement.textContent).toContain('João');
    expect(elementos[1].nativeElement.textContent).toContain('Maria');
  });
});
