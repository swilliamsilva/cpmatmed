import { of } from 'rxjs';

// Mock do FornecedorService
export class MockFornecedorService {
  // Simula o método buscarTodos, retornando uma lista de fornecedores
  buscarTodos() {
    return of([
      { id: 1, nome: 'Fornecedor 1' },
      { id: 2, nome: 'Fornecedor 2' },
      { id: 3, nome: 'Fornecedor 3' },
    ]);
  }

  // Simula o método buscarPorId, retornando um fornecedor específico
  buscarPorId(id: number) {
    return of({ id, nome: `Fornecedor ${id}` });
  }

  // Simula o método criar, que adiciona um novo fornecedor
  criar(fornecedor: { nome: string }) {
    return of({ id: Math.floor(Math.random() * 1000), nome: fornecedor.nome });
  }

  // Simula o método atualizar, que atualiza um fornecedor existente
  atualizar(id: number, fornecedor: { nome: string }) {
    return of({ id, nome: fornecedor.nome });
  }

  // Simula o método excluir, que exclui um fornecedor
  excluir(id: number) {
    return of(null); // Simula a exclusão com sucesso
  }
}
