import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroPedidoComponent } from './cadastro-pedido.component'; // ✅ import correto
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
    const spy = jasmine.createSpyObj('PedidoService', ['criar']); // ✅ criando spy certo

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

  it('deve mostrar mensagem de sucesso ao enviar pedido', () => {
    pedidoServiceSpy.criar.and.returnValue(of({
      id: 1,
      descricao: 'Pedido de teste',
      dataCriacao: '2025-04-23'
    }));

    component.produtos.clear();
    component.adicionarProduto();

    component.pedidoForm.patchValue({
      descricao: 'Pedido de teste',
      dataCriacao: '2025-04-23',
      compradorId: 1
    });

    component.produtos.at(0).patchValue({
      produtoId: 1,
      quantidade: 2
    });

    component.onSubmit();

    expect(pedidoServiceSpy.criar).toHaveBeenCalled();
    expect(component.mensagemSucesso).toBe('Pedido cadastrado com sucesso!');
    expect(component.mensagemErro).toBe('');
  });
});
