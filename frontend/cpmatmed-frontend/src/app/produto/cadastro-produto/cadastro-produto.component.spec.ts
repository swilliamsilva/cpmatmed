import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { CadastroProdutoComponent } from './cadastro-produto.component';

describe('CadastroProdutoComponent', () => {
  let component: CadastroProdutoComponent;
  let fixture: ComponentFixture<CadastroProdutoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CadastroProdutoComponent],
      imports: [ReactiveFormsModule]
    });
    fixture = TestBed.createComponent(CadastroProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deve criar o formulário com campos obrigatórios', () => {
    expect(component.produtoForm).toBeTruthy();
    const form = component.produtoForm;

    // Verifica que os campos estão inicialmente inválidos
    expect(form.get('nome')?.valid).toBeFalse();
    expect(form.get('precoUnitario')?.valid).toBeFalse();
    expect(form.get('quantidade')?.valid).toBeFalse();
    expect(form.get('fornecedorId')?.valid).toBeFalse();
  });

  it('deve ser inválido se os campos estiverem vazios', () => {
    component.produtoForm.setValue({
      nome: '',
      precoUnitario: 0,
      quantidade: 0,
      fornecedorId: null
    });
    expect(component.produtoForm.valid).toBeFalse();
  });

  it('deve ser válido se todos os campos forem preenchidos corretamente', () => {
    component.produtoForm.setValue({
      nome: 'Produto Teste',
      precoUnitario: 10.0,
      quantidade: 5,
      fornecedorId: 1
    });
    expect(component.produtoForm.valid).toBeTrue();
  });

  it('deve exibir erro ao tentar salvar com o formulário inválido', () => {
    // Definindo valores inválidos no formulário
    component.produtoForm.setValue({
      nome: '',
      precoUnitario: 0,
      quantidade: 0,
      fornecedorId: null
    });

    // Chamando a função de envio (onSubmit) que depende da validação
    component.onSubmit();
    
    // Verificando se a lógica de erro foi chamada, se necessário
    expect(component.produtoForm.valid).toBeFalse();
    expect(component.erroMensagem).toBe('Todos os campos são obrigatórios!');
  });
});
