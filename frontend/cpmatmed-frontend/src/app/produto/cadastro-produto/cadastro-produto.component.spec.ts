import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroProdutoComponent } from './cadastro-produto.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProdutoService } from '../produto.service';
import { of, throwError } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

class MockProdutoService {
  criar() {
    return of({});
  }
}

describe('CadastroProdutoComponent', () => {
  let component: CadastroProdutoComponent;
  let fixture: ComponentFixture<CadastroProdutoComponent>;
  let produtoService: ProdutoService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastroProdutoComponent],
      imports: [
        ReactiveFormsModule,
        RouterTestingModule
      ],
      providers: [
        { provide: ProdutoService, useClass: MockProdutoService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroProdutoComponent);
    component = fixture.componentInstance;
    produtoService = TestBed.inject(ProdutoService);
    fixture.detectChanges();
  });

  it('deve exibir mensagem de erro quando formulário for inválido', () => {
    component.onSubmit();
    
    // Verifica a mensagem de erro correta
    expect(component.errorMessage).toBe('Por favor, preencha todos os campos obrigatórios');
  });

  it('deve exibir erro ao falhar criação de produto', () => {
    const errorMessage = 'Erro ao criar produto';
    spyOn(produtoService, 'criar').and.returnValue(
      throwError(() => new Error(errorMessage))
    );

    component.produtoForm.setValue({
      nome: 'Produto Teste',
      precoUnitario: 10.50,
      quantidade: 5,
      fornecedorId: 1
    });
    
    component.onSubmit();
    
    expect(component.errorMessage).toContain(errorMessage);
  });
});