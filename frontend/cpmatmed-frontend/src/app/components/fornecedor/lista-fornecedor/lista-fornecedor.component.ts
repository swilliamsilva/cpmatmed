import { Component, OnInit } from '@angular/core';
import { FornecedorService, Fornecedor } from '../fornecedor.service';

@Component({
  selector: 'app-lista-fornecedor',
  templateUrl: './lista-fornecedor.component.html',
})
export class ListaFornecedorComponent implements OnInit {
  fornecedores: Fornecedor[] = [];

  constructor(private fornecedorService: FornecedorService) {}

  ngOnInit(): void {
    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedorService.buscarTodos().subscribe((dados) => {
      this.fornecedores = dados;
    });
  }

  excluir(id: number): void {
    if (confirm('Tem certeza que deseja excluir este fornecedor?')) {
      this.fornecedorService.excluir(id).subscribe(() => {
        this.carregarFornecedores();
      });
    }
  }
}
