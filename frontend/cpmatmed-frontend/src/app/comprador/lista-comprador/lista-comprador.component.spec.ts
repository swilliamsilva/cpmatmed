import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaCompradorComponent } from './lista-comprador.component';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { CompradorService } from '../comprador.service';

describe('ListaCompradorComponent', () => {
  let component: ListaCompradorComponent;
  let fixture: ComponentFixture<ListaCompradorComponent>;

  // Corrigido: mock com método getAll()
  const mockCompradorService = {
    getAll: () => of([
      { id: 1, nome: 'João', email: 'joao@email.com' },
      { id: 2, nome: 'Maria', email: 'maria@email.com' }
    ])
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaCompradorComponent],
      providers: [{ provide: CompradorService, useValue: mockCompradorService }]
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

  it('deve chamar o serviço getAll e carregar os dados corretamente', () => {
    spyOn(mockCompradorService, 'getAll').and.callThrough();
    component.ngOnInit();
    expect(mockCompradorService.getAll).toHaveBeenCalled();
  });
});
