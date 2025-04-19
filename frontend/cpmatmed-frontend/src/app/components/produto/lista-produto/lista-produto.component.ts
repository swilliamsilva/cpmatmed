import { Component, OnInit } from '@angular/core';
import { ProdutoService, Produto } from '../produto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-produto',
  templateUrl: './lista-produto.component.html'
})
export class ListaProdutoComponent implements OnInit {
  produtos: Produto[] = [];

  constructor(private produtoService: ProdutoService, private router: Router) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listar().subscribe(data => this.produtos = data);
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
