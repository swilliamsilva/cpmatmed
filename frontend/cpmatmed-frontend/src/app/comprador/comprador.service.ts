// Classe: CompradorService - Aplicação: cpmatmed

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompradorDTO } from './dto/comprador.dto';

@Injectable({
  providedIn: 'root'
})
export class CompradorService {
  private readonly apiUrl = 'http://localhost:8080/api/compradores';

  constructor(private http: HttpClient) {}

  getAll(): Observable<CompradorDTO[]> {
    return this.http.get<CompradorDTO[]>(this.apiUrl);
  }

  getById(id: number): Observable<CompradorDTO> {
    return this.http.get<CompradorDTO>(`${this.apiUrl}/${id}`);
  }

  criar(comprador: CompradorDTO): Observable<CompradorDTO> {
    return this.http.post<CompradorDTO>(this.apiUrl, comprador);
  }

  atualizar(id: number, comprador: CompradorDTO): Observable<CompradorDTO> {
    return this.http.put<CompradorDTO>(`${this.apiUrl}/${id}`, comprador);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
