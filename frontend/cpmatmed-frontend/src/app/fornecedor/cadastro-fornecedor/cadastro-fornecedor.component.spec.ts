import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { CadastroFornecedorComponent } from './cadastro-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('CadastroFornecedorComponent - cpmatmed', () => {
  let component: CadastroFornecedorComponent;
  let fixture: ComponentFixture<CadastroFornecedorComponent>;
  let fornecedorService: FornecedorService;
  let router: Router;

  const mockActivatedRoute = {
    snapshot: {
      paramMap: {
        get: (key: string) => key === 'id' ? '1' : null
      }
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastroFornecedorComponent],
      imports: [FormsModule, ReactiveFormsModule],
      providers: [
        {
          provide: FornecedorService,
          useValue: {
            buscarPorId: (id: number) => of({ id: 1, nome: 'Fornecedor Teste', cnpj: '00.000.000/0001-00' }),
            criar: (fornecedor: any) => of({ id: 2, ...fornecedor }),
            atualizar: (id: number, fornecedor: any) => of({ id, ...fornecedor })
          }
        },
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: Router, useValue: { navigate: jasmine.createSpy('navigate') } }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroFornecedorComponent);
    component = fixture.componentInstance;
    fornecedorService = TestBed.inject(FornecedorService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  // Teste corrigido
  it('deve verificar se o método salvar foi chamado', fakeAsync(() => {
    spyOn(fornecedorService, 'criar').and.callThrough();

    component.fornecedor = { nome: 'Novo Fornecedor', cnpj: '11.111.111/1111-11' };
    component.salvar();
    tick();

    expect(fornecedorService.criar).toHaveBeenCalled();
    expect(router.navigate).toHaveBeenCalledWith(['/fornecedor/lista-fornecedor']);
  }));
});
