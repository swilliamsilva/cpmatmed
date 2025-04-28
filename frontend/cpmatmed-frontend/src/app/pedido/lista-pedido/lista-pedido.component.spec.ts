import {
  ComponentFixture,
  TestBed,
  fakeAsync,
  tick,
} from '@angular/core/testing';
import { ListaPedidoComponent } from './lista-pedido.component';
import { PedidoService } from '../pedido.service';
import { of, throwError } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { delay } from 'rxjs/operators';

describe('ListaPedidoComponent', () => {
  let component: ListaPedidoComponent;
  let fixture: ComponentFixture<ListaPedidoComponent>;
  let pedidoService: jasmine.SpyObj<PedidoService>;

  const mockPedidos = [
    { id: 1, nome: 'Pedido A' },
    { id: 2, nome: 'Pedido B' },
  ];

  beforeEach(async () => {
    const pedidoServiceMock = jasmine.createSpyObj('PedidoService', [
      'listar',
      'excluir',
    ]);
    pedidoServiceMock.listar.and.returnValue(of(mockPedidos));

    // Correção: Garantir que o Observable complete após a emissão
    pedidoServiceMock.excluir.and.returnValue(of(null).pipe(delay(0)));

    await TestBed.configureTestingModule({
      declarations: [ListaPedidoComponent],
      imports: [RouterTestingModule],
      providers: [{ provide: PedidoService, useValue: pedidoServiceMock }],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaPedidoComponent);
    component = fixture.componentInstance;
    pedidoService = TestBed.inject(
      PedidoService
    ) as jasmine.SpyObj<PedidoService>;
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar pedidos na inicialização', () => {
    fixture.detectChanges();
    expect(pedidoService.listar).toHaveBeenCalled();
    expect(component.pedidos.length).toBe(2);
    expect(component.pedidos[0].nome).toBe('Pedido A');
  });

  it('deve lidar com falha na exclusão de pedido', () => {
    const errorMessage = 'Erro ao excluir pedido';
    pedidoService.excluir.and.returnValue(
      throwError(() => new Error(errorMessage))
    );

    component.excluirpedido(1);
    expect(component.errorMessage).toContain(errorMessage);
  });

  it('deve excluir pedido e recarregar lista', fakeAsync(() => {
    // Força detecção inicial
    fixture.detectChanges();

    // Reseta o contador após o carregamento inicial
    pedidoService.listar.calls.reset();

    // Dispara a exclusão
    component.excluirpedido(1);

    // Força conclusão de todas as operações assíncronas
    tick();
    fixture.detectChanges();

    // Verifica chamadas
    expect(pedidoService.excluir).toHaveBeenCalledWith(1);
    expect(pedidoService.listar).toHaveBeenCalledTimes(1);
  }));
});
