// Classe: ProdutoService - Aplicação: cpmatmed

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProdutoDTO } from './dto/produto.dto'; // Usando o DTO correto

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  // private readonly apiUrl = 'http://localhost:8080/api/produtos';
  private readonly apiUrl = '/api/produtos';

  constructor(private http: HttpClient) {}

  listar(): Observable<ProdutoDTO[]> {
    return this.http.get<ProdutoDTO[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<ProdutoDTO> {
    return this.http.get<ProdutoDTO>(`${this.apiUrl}/${id}`);
  }

  criar(produto: ProdutoDTO): Observable<ProdutoDTO> {
    return this.http.post<ProdutoDTO>(this.apiUrl, produto);
  }

  atualizar(id: number, produto: ProdutoDTO): Observable<ProdutoDTO> {
    return this.http.put<ProdutoDTO>(`${this.apiUrl}/${id}`, produto);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
