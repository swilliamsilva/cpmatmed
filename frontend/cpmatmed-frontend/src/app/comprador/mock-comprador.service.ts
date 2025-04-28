import { Observable, of } from 'rxjs';
import { CompradorDTO } from './dto/comprador.dto';

export class MockCompradorService {
  getCompradores(): Observable<CompradorDTO[]> {
    return of([{
      id: 1,
      nome: 'Mock Comprador',
      email: 'mock@teste.com' // ✅ Campo email
    }]);
  }

  getCompradorPorId(id: number): Observable<CompradorDTO> {
    return of({
      id,
      nome: 'Mock Comprador Detalhe',
      email: 'mock.detalhe@teste.com'
    });
  }

  criar(comprador: CompradorDTO): Observable<CompradorDTO> {
    return of({
      id: 1,
      nome: comprador.nome,
      email: comprador.email // ✅ Retornando email
    });
  }
}
