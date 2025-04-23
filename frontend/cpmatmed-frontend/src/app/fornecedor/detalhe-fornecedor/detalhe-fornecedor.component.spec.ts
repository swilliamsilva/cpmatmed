import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalheFornecedorComponent } from './detalhe-fornecedor.component';
import { ActivatedRoute } from '@angular/router';
import { FornecedorService } from '../fornecedor.service';
import { of } from 'rxjs';

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
            if (chave === 'id') return '1'; // simula parâmetro de rota
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
    fornecedorServiceSpy.buscarPorId.and.returnValue(of(MOCK_FORNECEDOR));
    fixture.detectChanges(); // executa o ngOnInit
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar o fornecedor ao inicializar', () => {
    expect(component.fornecedorId).toBe(1);
    expect(fornecedorServiceSpy.buscarPorId).toHaveBeenCalledWith(1);
    expect(component.fornecedor).toEqual(MOCK_FORNECEDOR);
  });
});
