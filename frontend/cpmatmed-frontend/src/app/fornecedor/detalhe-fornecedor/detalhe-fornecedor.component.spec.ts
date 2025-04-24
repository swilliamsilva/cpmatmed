import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalheFornecedorComponent } from './detalhe-fornecedor.component';
import { ActivatedRoute } from '@angular/router';
import { FornecedorService } from '../fornecedor.service';
import { of, throwError } from 'rxjs';

describe('DetalheFornecedorComponent', () => {
  let component: DetalheFornecedorComponent;
  let fixture: ComponentFixture<DetalheFornecedorComponent>;
  let fornecedorServiceSpy: jasmine.SpyObj<FornecedorService>;

  const MOCK_FORNECEDOR = { id: 1, nome: 'Fornecedor Teste' };

  beforeEach(async () => {
    const fornecedorServiceMock = jasmine.createSpyObj('FornecedorService', ['buscarPorId']);
    const activatedRouteMock = {
      snapshot: {
        paramMap: {
          get: (chave: string) => {
            if (chave === 'id') return '1'; // Simula o parâmetro de rota
            return null;
          }
        }
      }
    };

    await TestBed.configureTestingModule({
      declarations: [DetalheFornecedorComponent],
      providers: [
        { provide: FornecedorService, useValue: fornecedorServiceMock },
        { provide: ActivatedRoute, useValue: activatedRouteMock }
      ]
    }).compileComponents();

    fornecedorServiceSpy = TestBed.inject(FornecedorService) as jasmine.SpyObj<FornecedorService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheFornecedorComponent);
    component = fixture.componentInstance;
    fornecedorServiceSpy.buscarPorId.and.returnValue(of(MOCK_FORNECEDOR)); // Mock da resposta do serviço
    fixture.detectChanges(); // Executa o ngOnInit
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar o fornecedor ao inicializar', () => {
    expect(component.fornecedorId).toBe(1); // Verifica se o ID foi corretamente atribuído
    expect(fornecedorServiceSpy.buscarPorId).toHaveBeenCalledWith(1); // Verifica a chamada ao serviço
    expect(component.fornecedor).toEqual(MOCK_FORNECEDOR); // Verifica se o fornecedor foi atribuído corretamente
  });

  it('deve lidar com erro ao carregar o fornecedor', () => {
    // Mock de erro
    fornecedorServiceSpy.buscarPorId.and.returnValue(throwError('Erro ao buscar fornecedor'));

    fixture.detectChanges(); // Atualiza a visualização

    // Verifica se a mensagem de erro foi atribuída corretamente
    expect(component.errorMessage).toBe('Erro ao carregar os dados do fornecedor');
  });

  it('deve exibir fornecedor com nome correto no template', () => {
    // Simula a renderização do template
    fixture.detectChanges();

    const fornecedorElement = fixture.debugElement.nativeElement.querySelector('.fornecedor-nome');
    expect(fornecedorElement.textContent).toContain(MOCK_FORNECEDOR.nome); // Verifica se o nome do fornecedor é exibido no template
  });

  it('deve exibir mensagem de erro no template quando falhar ao carregar fornecedor', () => {
    fornecedorServiceSpy.buscarPorId.and.returnValue(throwError('Erro ao buscar fornecedor'));
    
    fixture.detectChanges(); // Atualiza a visualização após erro

    // Verifica se a mensagem de erro é exibida no template
    const errorMessageElement = fixture.debugElement.nativeElement.querySelector('.error-message');
    expect(errorMessageElement.textContent).toContain('Erro ao carregar os dados do fornecedor');
  });
});
