import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-cadastro-pedido',
  templateUrl: './cadastro-pedido.component.html',
})
export class CadastroPedidoComponent implements OnInit { // ✅ com export
  pedidoForm!: FormGroup;
  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private fb: FormBuilder, private pedidoService: PedidoService) {}

  ngOnInit(): void {
    this.pedidoForm = this.fb.group({
      descricao: ['', Validators.required],
      dataCriacao: ['', Validators.required],
      compradorId: ['', Validators.required],
      produtos: this.fb.array([]),
    });

    this.adicionarProduto();
  }

  get produtos(): FormArray {
    return this.pedidoForm.get('produtos') as FormArray;
  }

  adicionarProduto(): void {
    const produtoGroup = this.fb.group({
      produtoId: ['', Validators.required],
      quantidade: ['', [Validators.required, Validators.min(1)]],
    });
    this.produtos.push(produtoGroup);
  }

  removerProduto(index: number): void {
    this.produtos.removeAt(index);
  }

  onSubmit(): void {
    if (this.pedidoForm.valid) {
      this.pedidoService.criar(this.pedidoForm.value).subscribe({
        next: () => {
          this.mensagemSucesso = 'Pedido cadastrado com sucesso!';
          this.mensagemErro = '';
          this.pedidoForm.reset();
          this.produtos.clear();
          this.adicionarProduto();
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
