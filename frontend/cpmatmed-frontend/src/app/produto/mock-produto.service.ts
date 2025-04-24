import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Produto } from './produto.model'; // Supondo que Produto seja uma interface ou classe que define os campos

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  listar(): Observable<Produto[]> {
    return of([
      { id: 1, nome: 'Produto A', precoUnitario: 10, quantidade: 5, fornecedorId: 1 },
      { id: 2, nome: 'Produto B', precoUnitario: 20, quantidade: 10, fornecedorId: 2 }
    ]);
  }
}
