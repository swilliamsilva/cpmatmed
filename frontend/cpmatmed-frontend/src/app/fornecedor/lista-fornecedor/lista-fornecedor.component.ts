// lista-fornecedor.component.ts - cpmatmed

import { Component, OnInit } from '@angular/core';
import { FornecedorService } from '../fornecedor.service';
import { FornecedorDTO } from '../dto/fornecedor.dto';

@Component({
  selector: 'app-lista-fornecedor',
  templateUrl: './lista-fornecedor.component.html',
})
export class ListaFornecedorComponent implements OnInit {
  fornecedores: FornecedorDTO[] = [];
  errorMessage: string = '';

  constructor(private fornecedorService: FornecedorService) {}

  ngOnInit(): void {
    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedorService.listar().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.fornecedores = data;
        } else {
          this.fornecedores = [];
          console.error('Dados inválidos recebidos para fornecedores');
        }
      },
      (err) => {
        this.fornecedores = [];
        console.error('Erro ao carregar fornecedores', err);
        this.errorMessage = 'Erro ao carregar fornecedores';
      }
    );
  }

  excluir(id: number): void {
    this.fornecedorService.excluir(id).subscribe(
      () => {
        this.carregarFornecedores();
      },
      (err) => {
        console.error('Erro ao excluir fornecedor', err);
        this.errorMessage = 'Erro ao excluir fornecedor';
      }
    );
  }
}
