import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaPedidoComponent } from './lista-pedido.component';
import { PedidoService } from '../pedido.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';

describe('ListaPedidoComponent', () => {
  let component: ListaPedidoComponent;
  let fixture: ComponentFixture<ListaPedidoComponent>;
  let pedidoServiceMock: any;

  beforeEach(async () => {
    pedidoServiceMock = {
      listarPedidos: jasmine.createSpy('listarPedidos')
    };

    await TestBed.configureTestingModule({
      declarations: [ListaPedidoComponent],
      imports: [RouterTestingModule],
      providers: [
        { provide: PedidoService, useValue: pedidoServiceMock }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPedidoComponent);
    component = fixture.componentInstance;
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar e exibir a lista de pedidos', () => {
    const mockPedidos = [
      { id: 1, descricao: 'Pedido A', dataCriacao: '2025-04-21' },
      { id: 2, descricao: 'Pedido B', dataCriacao: '2025-04-22' }
    ];

    pedidoServiceMock.listarPedidos.and.returnValue(of(mockPedidos));
    fixture.detectChanges(); // ngOnInit

    const compiled = fixture.nativeElement as HTMLElement;
    const itens = compiled.querySelectorAll('ul li');

    expect(component.pedidos.length).toBe(2);
    expect(itens.length).toBe(2);
    expect(itens[0].textContent).toContain('Pedido A');
    expect(itens[0].textContent).toContain('2025-04-21');
    expect(itens[1].textContent).toContain('Pedido B');
    expect(itens[1].textContent).toContain('2025-04-22');
  });

  it('deve ter links para os detalhes de cada pedido', () => {
    const mockPedidos = [
      { id: 1, descricao: 'Pedido A', dataCriacao: '2025-04-21' },
      { id: 2, descricao: 'Pedido B', dataCriacao: '2025-04-22' }
    ];

    pedidoServiceMock.listarPedidos.and.returnValue(of(mockPedidos));
    fixture.detectChanges(); // ngOnInit

    const links = fixture.debugElement.queryAll(By.css('a'));
    expect(links.length).toBe(2);
    expect(links[0].attributes['ng-reflect-router-link']).toBe('/pedido/detalhe-pedido,1');
    expect(links[1].attributes['ng-reflect-router-link']).toBe('/pedido/detalhe-pedido,2');
  });
});
