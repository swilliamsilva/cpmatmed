import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalheCompradorComponent } from './detalhe-comprador.component';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { MockCompradorService } from '../mock-comprador.service';

describe('DetalheCompradorComponent', () => {
  let component: DetalheCompradorComponent;
  let fixture: ComponentFixture<DetalheCompradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalheCompradorComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { snapshot: { paramMap: { get: () => '1' } } },  // Ajuste para refletir a rota com ID
        },
        { provide: 'CompradorService', useClass: MockCompradorService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar detalhes do comprador no ngOnInit', () => {
    // Garantir que os detalhes do comprador estejam sendo carregados corretamente
    expect(component.comprador).toBeTruthy();
    expect(component.comprador.nome).toBe('Mock Comprador Detalhe'); // Verifica se o nome é o esperado
  });

  it('deve atribuir o ID correto ao compradorId a partir da rota', () => {
    // Verifica se o ID do comprador está sendo atribuído corretamente
    expect(component.compradorId).toBe(1);
  });
});
