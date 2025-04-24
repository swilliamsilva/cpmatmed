import { of } from 'rxjs';

export class MockPedidoService {
  // Mock da lista de pedidos
  listarPedidos() {
    return of([
      { id: 1, descricao: 'Pedido A', dataCriacao: '2025-04-21', compradorId: 1 },
      { id: 2, descricao: 'Pedido B', dataCriacao: '2025-04-22', compradorId: 2 }
    ]);
  }

  // Mock do método para buscar um pedido por ID
  buscarPedidoPorId(id: number) {
    return of({
      id: id,
      descricao: `Pedido ${id}`,
      dataCriacao: '2025-04-23',
      compradorId: 1,
      produtos: [
        { produtoId: 1, quantidade: 2 },
        { produtoId: 2, quantidade: 3 }
      ]
    });
  }

  // Mock do método para criar um pedido
  criarPedido(pedido: any) {
    return of({
      ...pedido,
      id: Math.floor(Math.random() * 1000) // Gera um ID aleatório para o pedido criado
    });
  }

  // Mock do método para atualizar um pedido
  atualizarPedido(id: number, pedido: any) {
    return of({
      id: id,
      ...pedido
    });
  }

  // Mock do método para excluir um pedido
  excluirPedido(id: number) {
    return of(null); // Retorna null como resposta de sucesso na exclusão
  }
}
