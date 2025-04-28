import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalheFornecedorComponent } from './detalhe-fornecedor.component';
import { ActivatedRoute } from '@angular/router';
import { FornecedorService } from '../fornecedor.service';
import { of, throwError } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FornecedorDTO } from '../dto/fornecedor.dto';

describe('DetalheFornecedorComponent', () => {
  let component: DetalheFornecedorComponent;
  let fixture: ComponentFixture<DetalheFornecedorComponent>;
  let fornecedorServiceSpy: jasmine.SpyObj<FornecedorService>;

  const MOCK_FORNECEDOR: FornecedorDTO = {
    id: 1,
    nome: 'Fornecedor Teste',
    cnpj: '00.000.000/0001-00'
  };

  beforeEach(async () => {
    const fornecedorServiceMock = jasmine.createSpyObj('FornecedorService', ['buscarPorId']);
    const activatedRouteMock = {
      snapshot: {
        paramMap: {
          get: (chave: string) => (chave === 'id' ? '1' : null)
        }
      }
    };

    await TestBed.configureTestingModule({
      declarations: [DetalheFornecedorComponent],
      providers: [
        { provide: FornecedorService, useValue: fornecedorServiceMock },
        { provide: ActivatedRoute, useValue: activatedRouteMock }
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fornecedorServiceSpy = TestBed.inject(FornecedorService) as jasmine.SpyObj<FornecedorService>;
  });

  describe('sucesso ao carregar fornecedor', () => {
    beforeEach(() => {
      fornecedorServiceSpy.buscarPorId.and.returnValue(of(MOCK_FORNECEDOR));
      fixture = TestBed.createComponent(DetalheFornecedorComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('deve exibir fornecedor com nome correto no template', () => {
      const fornecedorElement = fixture.nativeElement.querySelector('.fornecedor-nome');
      expect(fornecedorElement.textContent).toContain(MOCK_FORNECEDOR.nome);
    });
  });

  describe('falha ao carregar fornecedor', () => {
    beforeEach(() => {
      fornecedorServiceSpy.buscarPorId.and.returnValue(throwError(() => new Error('Erro ao buscar fornecedor')));
      fixture = TestBed.createComponent(DetalheFornecedorComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('deve exibir mensagem de erro no template', () => {
      const errorMessageElement = fixture.nativeElement.querySelector('.error-message');
      expect(errorMessageElement.textContent).toContain('Erro ao carregar os dados do fornecedor');
    });
  });
});
