// Classe: FornecedorService - Aplicação: cpmatmed

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FornecedorDTO } from './dto/fornecedor.dto';

@Injectable({
  providedIn: 'root'
})
export class FornecedorService {
  private readonly apiUrl = 'http://localhost:8080/api/fornecedores';

  constructor(private http: HttpClient) {}

  listar(): Observable<FornecedorDTO[]> {
    return this.http.get<FornecedorDTO[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<FornecedorDTO> {
    return this.http.get<FornecedorDTO>(`${this.apiUrl}/${id}`);
  }

  criar(fornecedor: FornecedorDTO): Observable<FornecedorDTO> {
    return this.http.post<FornecedorDTO>(this.apiUrl, fornecedor);
  }

  atualizar(id: number, fornecedor: FornecedorDTO): Observable<FornecedorDTO> {
    return this.http.put<FornecedorDTO>(`${this.apiUrl}/${id}`, fornecedor);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
