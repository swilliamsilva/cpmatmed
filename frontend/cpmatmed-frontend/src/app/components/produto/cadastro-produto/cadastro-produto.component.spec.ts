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
});
