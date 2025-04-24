// Classe: CadastroPedidoComponent.ts - Aplicação: cpmatmed

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-cadastro-pedido',
  templateUrl: './cadastro-pedido.component.html',
 
})
export class CadastroPedidoComponent implements OnInit {
  pedidoForm!: FormGroup;
  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private fb: FormBuilder, private pedidoService: PedidoService) {}

  ngOnInit(): void {
    // Definindo o formulário com um FormArray para gerenciar a lista de produtos
    this.pedidoForm = this.fb.group({
      descricao: ['', Validators.required],
      dataCriacao: ['', Validators.required],
      compradorId: ['', Validators.required],
      produtos: this.fb.array([]) // FormArray para adicionar/remover produtos
    });
  }

  // Método para acessar o FormArray de produtos
  get produtos(): FormArray {
    return this.pedidoForm.get('produtos') as FormArray;
  }

  // Método para adicionar um produto ao FormArray
  adicionarProduto(): void {
    const produtoGroup = this.fb.group({
      produtoId: ['', Validators.required], // ID do produto
      quantidade: ['', [Validators.required, Validators.min(1)]] // Validação da quantidade
    });
    this.produtos.push(produtoGroup);
  }

  // Método para remover um produto pelo índice do FormArray
  removerProduto(index: number): void {
    this.produtos.removeAt(index);
  }

  // Método de submit do formulário
  onSubmit(): void {
    if (this.pedidoForm.valid) {
      // Envia os dados para o serviço para salvar o pedido
      this.pedidoService.criarPedido(this.pedidoForm.value).subscribe({
        next: () => {
          this.mensagemSucesso = 'Pedido cadastrado com sucesso!';
          this.mensagemErro = '';
          this.pedidoForm.reset(); // Reseta o formulário
          this.produtos.clear(); // Limpa a lista de produtos
        },
        error: () => {
          this.mensagemErro = 'Erro ao cadastrar pedido!';
          this.mensagemSucesso = '';
        }
      });
    } else {
      this.mensagemErro = 'Por favor, preencha todos os campos corretamente!';
    }
  }
}
