import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroPedidoComponent } from './cadastro-pedido.component';
import { PedidoService } from '../pedido.service';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
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
    pedidoServiceSpy.criarPedido.and.returnValue(of({ mensagem: 'Pedido criado com sucesso!' }));

    component.pedidoForm.setValue({
      compradorId: 1,
      fornecedorId: 1,
      produtos: [{ produtoId: 1, quantidade: 2 }]
    });

    component.onSubmit();

    expect(component.mensagemSucesso).toBe('Pedido criado com sucesso!');
  });

  it('deve adicionar produto e validar o formulário', () => {
    // Assume que produtos é um FormArray dentro do pedidoForm
    const produtosFormArray = component.produtos;
    const initialLength = produtosFormArray.length;

    component.adicionarProduto();

    expect(produtosFormArray.length).toBe(initialLength + 1);
  });
});
