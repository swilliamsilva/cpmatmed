import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProdutoService } from '../produto.service';

@Component({
  selector: 'app-cadastro-produto',
  templateUrl: './cadastro-produto.component.html'
})
export class CadastroProdutoComponent {
  produtoForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private produtoService: ProdutoService,
    private router: Router
  ) {
    this.produtoForm = this.fb.group({
      nome: ['', Validators.required],
      precoUnitario: ['', [Validators.required, Validators.min(0.01)]],
      quantidade: ['', [Validators.required, Validators.min(1)]],
      fornecedorId: ['', Validators.required]
    });
  }
   
    onSubmit(): void {
      if (this.produtoForm.valid) {
        this.produtoService.criar(this.produtoForm.value).subscribe({
          next: () => this.router.navigate(['/produto/lista-produto']),
          error: (err) => {
            this.errorMessage = 'Erro ao criar produto: ' + err.message;
            console.error(err);
          }
        });
      } else {
        this.errorMessage = 'Por favor, preencha todos os campos obrigatórios';
      }
    }
  }


  