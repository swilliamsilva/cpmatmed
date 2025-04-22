import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-cadastro-pedido',
  templateUrl: './cadastro-pedido.component.html'
})
export class CadastroPedidoComponent implements OnInit {
  pedidoForm!: FormGroup;

  constructor(private fb: FormBuilder, private pedidoService: PedidoService) {}

  ngOnInit(): void {
    this.pedidoForm = this.fb.group({
      descricao: ['', Validators.required],
      dataCriacao: ['', Validators.required],
      compradorId: ['', Validators.required],
      produtos: this.fb.array([])
    });
  }

  get produtos(): FormArray {
    return this.pedidoForm.get('produtos') as FormArray;
  }

  adicionarProduto(): void {
    const produtoGroup = this.fb.group({
      produtoId: ['', Validators.required],
      quantidade: ['', [Validators.required, Validators.min(1)]]
    });
    this.produtos.push(produtoGroup);
  }

  removerProduto(index: number): void {
    this.produtos.removeAt(index);
  }

  onSubmit(): void {
    if (this.pedidoForm.valid) {
      this.pedidoService.criarPedido(this.pedidoForm.value).subscribe({
        next: () => {
          alert('Pedido cadastrado com sucesso!');
          this.pedidoForm.reset();
          this.produtos.clear();
        },
        error: () => {
          alert('Erro ao cadastrar pedido!');
        }
      });
    }
  }
}
