import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PedidoService } from './pedido.service';
import { Pedido } from './pedido.model';

describe('PedidoService', () => {
  let service: PedidoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PedidoService],
    });
    service = TestBed.inject(PedidoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch pedidos from the API', () => {
    const mockPedidos: Pedido[] = [
      { id: 1, nomeComprador: 'John Doe', nomeFornecedor: 'Fornecedor A', totalProdutos: 3, valorTotal: 100 },
    ];

    service.getPedidos().subscribe((pedidos) => {
      expect(pedidos.length).toBe(1);
      expect(pedidos[0].nomeComprador).toBe('John Doe');
      expect(pedidos[0].nomeFornecedor).toBe('Fornecedor A');
    });

    const req = httpMock.expectOne(`${service.apiUrl}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockPedidos);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
