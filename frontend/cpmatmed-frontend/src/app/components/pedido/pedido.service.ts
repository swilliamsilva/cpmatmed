import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiUrl = 'http://localhost:8080/pedidos'; // ajuste conforme seu backend

  constructor(private http: HttpClient) {}

  listarPedidos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  buscarPedidoPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  criarPedido(pedido: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, pedido);
  }
}
