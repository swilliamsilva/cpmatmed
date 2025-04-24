import { Observable, of } from 'rxjs';

// Tipagem do comprador
interface Comprador {
  id: number;
  nome: string;
}

export class MockCompradorService {

  // Método para buscar todos os compradores
  getCompradores(): Observable<Comprador[]> {
    // Retorna um Observable com uma lista de compradores mockados
    return of([{ id: 1, nome: 'Mock Comprador' }]);
  }

  // Método para buscar um comprador por ID
  getCompradorPorId(id: number): Observable<Comprador> {
    // Retorna um Observable com um comprador específico
    return of({ id, nome: 'Mock Comprador Detalhe' });
  }
}
