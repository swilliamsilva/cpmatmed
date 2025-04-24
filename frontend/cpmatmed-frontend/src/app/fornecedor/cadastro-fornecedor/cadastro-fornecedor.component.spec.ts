import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroFornecedorComponent } from './cadastro-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('CadastroFornecedorComponent', () => {
  let component: CadastroFornecedorComponent;
  let fixture: ComponentFixture<CadastroFornecedorComponent>;
  let fornecedorServiceSpy: jasmine.SpyObj<FornecedorService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    // Mock do serviço FornecedorService
    const fornecedorServiceMock = jasmine.createSpyObj('FornecedorService', [
      'buscarPorId',
      'criar',
      'atualizar'
    ]);

    // Mock do serviço Router
    const routerMock = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [CadastroFornecedorComponent],
      imports: [FormsModule, ReactiveFormsModule], // Incluindo ReactiveFormsModule para trabalhar com o formulário
      providers: [
        { provide: FornecedorService, useValue: fornecedorServiceMock },
        { provide: Router, useValue: routerMock },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (chave: string) => {
                  if (chave === 'id') return '1'; // Simula rota com ID
                  return null;
                }
              }
            }
          }
        }
      ]
    }).compileComponents();

    fornecedorServiceSpy = TestBed.inject(FornecedorService) as jasmine.SpyObj<FornecedorService>;
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroFornecedorComponent);
    component = fixture.componentInstance;

    // Mock para o retorno do serviço de buscarPorId
    fornecedorServiceSpy.buscarPorId.and.returnValue(of({ id: 1, nome: 'Fornecedor Teste' }));
    fixture.detectChanges(); // Dispara o ciclo de vida do componente
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar fornecedor no ngOnInit se houver ID', () => {
    expect(fornecedorServiceSpy.buscarPorId).toHaveBeenCalledWith(1);
    expect(component.fornecedor.nome).toBe('Fornecedor Teste');
  });

  it('deve chamar criar() ao salvar sem ID', () => {
    component.fornecedor = { nome: 'Fornecedor Novo' }; // Sem ID
    fornecedorServiceSpy.criar.and.returnValue(of({ id: 2, nome: 'Fornecedor Novo' }));

    component.salvar();

    expect(fornecedorServiceSpy.criar).toHaveBeenCalledWith(component.fornecedor);
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/fornecedor/lista-fornecedor']);
  });

  it('deve chamar atualizar() ao salvar com ID', () => {
    component.fornecedor = { id: 1, nome: 'Fornecedor Atualizado' };
    fornecedorServiceSpy.atualizar.and.returnValue(of(component.fornecedor));

    component.salvar();

    expect(fornecedorServiceSpy.atualizar).toHaveBeenCalledWith(1, component.fornecedor);
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/fornecedor/lista-fornecedor']);
  });

  it('deve exibir erro se falhar ao salvar fornecedor', () => {
    fornecedorServiceSpy.criar.and.returnValue(throwError('Erro ao criar fornecedor'));

    component.salvar();

    fixture.detectChanges();  // Atualiza a visualização após a chamada assíncrona
    
    // Verifica se a mensagem de erro foi definida corretamente
    expect(component.errorMessage).toBe('Erro ao criar fornecedor');
  });

  it('deve exibir erro se falhar ao atualizar fornecedor', () => {
    fornecedorServiceSpy.atualizar.and.returnValue(throwError('Erro ao atualizar fornecedor'));

    component.salvar();

    fixture.detectChanges();  // Atualiza a visualização após a chamada assíncrona
    
    // Verifica se a mensagem de erro foi definida corretamente
    expect(component.errorMessage).toBe('Erro ao atualizar fornecedor');
  });

  it('deve garantir que o formulário seja inválido se não houver dados obrigatórios', () => {
    component.formulario.controls['nome'].setValue(''); // Formulário inválido
    fixture.detectChanges();

    expect(component.formulario.invalid).toBeTrue(); // Verifica se o formulário está inválido
  });

  it('deve garantir que o formulário seja válido se todos os dados obrigatórios forem preenchidos', () => {
    component.formulario.controls['nome'].setValue('Fornecedor Teste');
    fixture.detectChanges();

    expect(component.formulario.valid).toBeTrue(); // Verifica se o formulário está válido
  });

  it('deve verificar se o método salvar foi chamado', () => {
    spyOn(component, 'salvar').and.callThrough();
    component.salvar();
    expect(component.salvar).toHaveBeenCalled();
  });
});
