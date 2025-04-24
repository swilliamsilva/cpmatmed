// Classe: CompradorService - Aplicação: cpmatmed

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Comprador {
  id?: number;
  nome: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class CompradorService {
  private readonly apiUrl = 'http://localhost:8080/api/comprador';

  constructor(private http: HttpClient) {}

  listar(): Observable<Comprador[]> {
    return this.http.get<Comprador[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<Comprador> {
    return this.http.get<Comprador>(`${this.apiUrl}/${id}`);
  }

  criar(comprador: Comprador): Observable<Comprador> {
    return this.http.post<Comprador>(this.apiUrl, comprador);
  }

  atualizar(id: number, comprador: Comprador): Observable<Comprador> {
    return this.http.put<Comprador>(`${this.apiUrl}/${id}`, comprador);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
