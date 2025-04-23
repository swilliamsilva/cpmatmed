import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalhePedidoComponent } from './detalhe-pedido.component';
import { ActivatedRoute } from '@angular/router';
import { PedidoService } from '../pedido.service';
import { of } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('DetalhePedidoComponent', () => {
  let component: DetalhePedidoComponent;
  let fixture: ComponentFixture<DetalhePedidoComponent>;
  let pedidoServiceMock: any;

  beforeEach(async () => {
    pedidoServiceMock = {
      buscarPedidoPorId: jasmine.createSpy('buscarPedidoPorId')
    };

    await TestBed.configureTestingModule({
      declarations: [DetalhePedidoComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => '42'
              }
            }
          }
        },
        { provide: PedidoService, useValue: pedidoServiceMock }
      ],
      schemas: [NO_ERRORS_SCHEMA] // ignora erros de tags externas no HTML
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalhePedidoComponent);
    component = fixture.componentInstance;
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve buscar e exibir os dados do pedido', () => {
    const mockPedido = {
      id: 42,
      descricao: 'Pedido de Teste',
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: [
        { produtoId: 1, quantidade: 2 },
        { produtoId: 2, quantidade: 5 }
      ]
    };

    // Configura o serviço mock para retornar o pedido
    pedidoServiceMock.buscarPedidoPorId.and.returnValue(of(mockPedido));

    // Executa o ciclo de vida e dispara o ngOnInit
    fixture.detectChanges();

    // Valida o conteúdo renderizado no HTML
    const compiled = fixture.nativeElement as HTMLElement;

    expect(component.pedido).toEqual(mockPedido);
    expect(compiled.querySelector('h2')?.textContent).toContain('Detalhes do Pedido #42');
    expect(compiled.querySelector('p:nth-of-type(1)')?.textContent).toContain('Pedido de Teste');
    expect(compiled.querySelector('p:nth-of-type(2)')?.textContent).toContain('2025-04-23');
    expect(compiled.querySelector('p:nth-of-type(3)')?.textContent).toContain('1');

    const produtos = compiled.querySelectorAll('ul li');
    expect(produtos.length).toBe(2);
    expect(produtos[0].textContent).toContain('Produto ID: 1');
    expect(produtos[0].textContent).toContain('Quantidade: 2');
    expect(produtos[1].textContent).toContain('Produto ID: 2');
    expect(produtos[1].textContent).toContain('Quantidade: 5');
  });
});
