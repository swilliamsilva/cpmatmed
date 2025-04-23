import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroFornecedorComponent } from './cadastro-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';

describe('CadastroFornecedorComponent', () => {
  let component: CadastroFornecedorComponent;
  let fixture: ComponentFixture<CadastroFornecedorComponent>;
  let fornecedorServiceSpy: jasmine.SpyObj<FornecedorService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    const fornecedorServiceMock = jasmine.createSpyObj('FornecedorService', [
      'buscarPorId',
      'criar',
      'atualizar'
    ]);

    const routerMock = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [CadastroFornecedorComponent],
      imports: [FormsModule],
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
    fornecedorServiceSpy.buscarPorId.and.returnValue(of({ id: 1, nome: 'Fornecedor Teste' }));
    fixture.detectChanges();
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
});
