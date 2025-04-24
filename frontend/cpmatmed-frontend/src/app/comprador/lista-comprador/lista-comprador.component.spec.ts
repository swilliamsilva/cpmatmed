import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaCompradorComponent } from './lista-comprador.component';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { MockCompradorService } from '../mock-comprador.service'; // Supondo que exista um serviço para buscar compradores

describe('ListaCompradorComponent', () => {
  let component: ListaCompradorComponent;
  let fixture: ComponentFixture<ListaCompradorComponent>;
  let mockCompradorService: MockCompradorService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaCompradorComponent],
      providers: [
        { provide: MockCompradorService, useValue: { getCompradores: () => of([{ nome: 'João' }, { nome: 'Maria' }]) } }
      ]
    }).compileComponents();

    mockCompradorService = TestBed.inject(MockCompradorService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();  // Garante que o componente e os dados sejam inicializados
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve ter uma lista de compradores com pelo menos dois elementos', () => {
    // Verifica se o componente possui pelo menos dois compradores
    expect(component.compradores.length).toBeGreaterThanOrEqual(2);
  });

  it('deve renderizar todos os compradores no HTML', () => {
    // Aguarda o componente ser completamente renderizado
    fixture.detectChanges();

    // Verifica se o número de elementos no HTML é igual ao número de compradores
    const elementos = fixture.debugElement.queryAll(By.css('li.list-group-item'));
    expect(elementos.length).toBe(component.compradores.length);

    // Verifica se o conteúdo está correto para cada comprador
    expect(elementos[0].nativeElement.textContent).toContain('João');
    expect(elementos[1].nativeElement.textContent).toContain('Maria');
  });

  it('deve chamar o serviço getCompradores e carregar os dados corretamente', () => {
    // Simula a chamada ao serviço para obter os compradores
    spyOn(mockCompradorService, 'getCompradores').and.callThrough();

    // Verifica se a função do serviço foi chamada
    component.ngOnInit();
    expect(mockCompradorService.getCompradores).toHaveBeenCalled();

    // Verifica se a lista de compradores foi carregada corretamente
    expect(component.compradores.length).toBeGreaterThanOrEqual(2);
    expect(component.compradores[0].nome).toBe('João');
    expect(component.compradores[1].nome).toBe('Maria');
  });
});
