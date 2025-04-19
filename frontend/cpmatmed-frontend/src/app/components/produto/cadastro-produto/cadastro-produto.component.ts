import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-produto',
  templateUrl: './cadastro-produto.component.html'
})
export class CadastroProdutoComponent implements OnInit {
  produtoForm: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.produtoForm = this.fb.group({
      nome: ['', Validators.required],
      precoUnitario: [0, [Validators.required, Validators.min(0.01)]],
      quantidade: [0, [Validators.required, Validators.min(1)]],
      fornecedorId: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.produtoForm.valid) {
      const produto = this.produtoForm.value;
      console.log('Produto a ser salvo:', produto);
      // Chamar o serviço para salvar
    }
  }
}
