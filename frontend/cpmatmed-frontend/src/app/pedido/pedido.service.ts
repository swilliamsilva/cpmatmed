// Classe: PedidoService - Aplicação: cpmatmed

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PedidoDTO } from './dto/pedido.dto'; // Usando o DTO correto

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private readonly apiUrl = 'http://localhost:8080/api/pedidos'; // ajuste feito para manter a padronização

  constructor(private http: HttpClient) {}

  listar(): Observable<PedidoDTO[]> {
    return this.http.get<PedidoDTO[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<PedidoDTO> {
    return this.http.get<PedidoDTO>(`${this.apiUrl}/${id}`);
  }

  criar(pedido: PedidoDTO): Observable<PedidoDTO> {
    return this.http.post<PedidoDTO>(this.apiUrl, pedido);
  }

  atualizar(id: number, pedido: PedidoDTO): Observable<PedidoDTO> {
    return this.http.put<PedidoDTO>(`${this.apiUrl}/${id}`, pedido);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
