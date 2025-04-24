// Classe: CadastroProdutoComponent - Aplicação: cpmatmed

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-produto',
  templateUrl: './cadastro-produto.component.html',
  styleUrls: ['./cadastro-produto.component.scss']  // Corrigido para usar SCSS
})
export class CadastroProdutoComponent implements OnInit {
  produtoForm: FormGroup;
  erroMensagem: string = '';

  constructor(private fb: FormBuilder) {
    this.produtoForm = this.fb.group({
      nome: ['', Validators.required],
      precoUnitario: [0, [Validators.required, Validators.min(1)]],
      quantidade: [0, [Validators.required, Validators.min(1)]],
      fornecedorId: [null, Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.produtoForm.invalid) {
      this.erroMensagem = 'Todos os campos são obrigatórios!';
      return;
    }

    console.log('Formulário enviado com sucesso');
  }
}
