import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalhePedidoComponent } from './detalhe-pedido.component';
import { PedidoService } from '../pedido.service';
import { of, throwError } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { PedidoDTO } from '../dto/pedido.dto';

describe('DetalhePedidoComponent', () => {
  let component: DetalhePedidoComponent;
  let fixture: ComponentFixture<DetalhePedidoComponent>;
  let pedidoServiceMock: jasmine.SpyObj<PedidoService>;

  const mockPedido: PedidoDTO = {
    id: 42,
    descricao: 'Pedido de Teste',
    dataCriacao: '2025-04-23',
    compradorId: 1,
    produtos: [
      { produtoId: 10, quantidade: 2 },
      { produtoId: 20, quantidade: 1 },
    ]
  };

  beforeEach(() => {
    pedidoServiceMock = jasmine.createSpyObj('PedidoService', ['buscarPorId']);

    TestBed.configureTestingModule({
      declarations: [DetalhePedidoComponent],
      providers: [
        { provide: PedidoService, useValue: pedidoServiceMock },
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '42' } } } }
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(DetalhePedidoComponent);
    component = fixture.componentInstance;
  });

  it('deve carregar os detalhes do pedido com sucesso', () => {
    pedidoServiceMock.buscarPorId.and.returnValue(of(mockPedido));

    fixture.detectChanges();

    expect(component.pedido).toEqual(mockPedido);
    expect(pedidoServiceMock.buscarPorId).toHaveBeenCalledWith(42);
  });

  it('deve lidar com erro ao buscar os detalhes do pedido', () => {
    // Correção 1: Usar throwError com função factory
    pedidoServiceMock.buscarPorId.and.returnValue(
      throwError(() => ({ message: 'Erro ao buscar pedido' }))
    );

    // Correção 2: Garantir estado inicial
    component.pedido = undefined;
    component.errorMessage = '';

    fixture.detectChanges();

    // Correção 3: Verificar ambos os estados
    expect(component.pedido).toBeUndefined();
    expect(component.errorMessage).toContain('Erro ao carregar os dados do pedido');
  });
});