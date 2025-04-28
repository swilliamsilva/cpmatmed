// lista-produto.component.ts
import { Component, OnInit } from '@angular/core';
import { ProdutoService } from '../produto.service';
import { Router } from '@angular/router';
import { ProdutoDTO } from '../dto/produto.dto'; // Importação adicionada

@Component({
  selector: 'app-lista-produto',
  templateUrl: './lista-produto.component.html',
})
export class ListaProdutoComponent implements OnInit {
  produtos: ProdutoDTO[] = []; // Tipo corrigido

  constructor(private produtoService: ProdutoService, private router: Router) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listar().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.produtos = data;
        } else {
          this.produtos = [];
          console.error('Dados inválidos recebidos para produtos');
        }
      },
      (error) => {
        this.produtos = [];
        console.error('Erro ao carregar produtos', error);
      }
    );
  }

  editarProduto(id: number): void {
    this.router.navigate(['/produto/cadastro-produto', id]);
  }

  excluirProduto(id: number): void {
    if (confirm('Deseja realmente excluir este produto?')) {
      this.produtoService.excluir(id).subscribe(() => this.carregarProdutos());
    }
  }
}
