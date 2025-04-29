import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompradorService } from '../comprador.service';

@Component({
  selector: 'app-cadastro-comprador',
  templateUrl: './cadastro-comprador.component.html',
})
export class CadastroCompradorComponent {
  compradorForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private compradorService: CompradorService,
    private router: Router
  ) {
    this.compradorForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }
  // No componente
  errorMessage: string = '';

  onSubmit(): void {
    if (this.compradorForm.valid) {
      this.compradorService.criar(this.compradorForm.value).subscribe({
        next: () => this.router.navigate(['/comprador/lista-comprador']),
        error: (err) => {
          this.errorMessage = `Erro ao criar comprador: ${err.message}`;
          console.error('Erro ao criar comprador:', err);
        }
      });
    } else {
      this.compradorForm.markAllAsTouched();
      this.errorMessage = 'Por favor, corrija os erros no formulário';
    }
  }
}

