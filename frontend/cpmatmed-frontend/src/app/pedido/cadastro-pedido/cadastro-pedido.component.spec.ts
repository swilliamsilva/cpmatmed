// cadastro-pedido.component.spec.ts
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroPedidoComponent } from './cadastro-pedido.component';
import { PedidoService } from '../pedido.service';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('CadastroPedidoComponent', () => {
  let component: CadastroPedidoComponent;
  let fixture: ComponentFixture<CadastroPedidoComponent>;
  let pedidoServiceSpy: jasmine.SpyObj<PedidoService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('PedidoService', ['criarPedido']);

    await TestBed.configureTestingModule({
      declarations: [CadastroPedidoComponent],
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers: [
        FormBuilder,
        { provide: PedidoService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CadastroPedidoComponent);
    component = fixture.componentInstance;
    pedidoServiceSpy = TestBed.inject(PedidoService) as jasmine.SpyObj<PedidoService>;

    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve mostrar mensagem de sucesso ao enviar pedido', () => {
    // Ajustando o retorno para o serviço
    pedidoServiceSpy.criarPedido.and.returnValue(of({ mensagem: 'Pedido criado com sucesso!' }));

    component.pedidoForm.setValue({
      descricao: 'Pedido de teste',
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: [{ produtoId: 1, quantidade: 2 }]
    });

    component.onSubmit();

    // Confirmando que a mensagem de sucesso foi exibida
    expect(component.mensagemSucesso).toBe('Pedido cadastrado com sucesso!');
  });

  it('deve exibir mensagem de erro ao falhar ao enviar pedido', () => {
    pedidoServiceSpy.criarPedido.and.returnValue(throwError('Erro ao criar pedido'));

    component.pedidoForm.setValue({
      descricao: 'Pedido de erro',
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: [{ produtoId: 1, quantidade: 2 }]
    });

    component.onSubmit();

    // Confirmando que a mensagem de erro foi exibida
    expect(component.mensagemErro).toBe('Erro ao cadastrar pedido!');
  });

  it('deve adicionar produto ao formulário de produtos e validar o formulário', () => {
    const produtosFormArray = component.produtos;
    const initialLength = produtosFormArray.length;

    component.adicionarProduto();

    expect(produtosFormArray.length).toBe(initialLength + 1);
    // Confirmando a validação do produto dentro do FormArray
    const produtoControl = produtosFormArray.at(0);
    expect(produtoControl.valid).toBeFalse(); // Deveria ser inválido porque não tem valores ainda
    produtoControl.get('produtoId')?.setValue(1);
    produtoControl.get('quantidade')?.setValue(1);
    expect(produtoControl.valid).toBeTrue(); // Agora o controle deve ser válido
  });

  it('deve validar o formulário corretamente', () => {
    // Caso o formulário seja válido
    component.pedidoForm.setValue({
      descricao: 'Pedido válido',
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: [{ produtoId: 1, quantidade: 2 }]
    });

    expect(component.pedidoForm.valid).toBeTrue();

    // Caso o formulário seja inválido
    component.pedidoForm.setValue({
      descricao: '',
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: []
    });

    expect(component.pedidoForm.invalid).toBeTrue();
  });
});
