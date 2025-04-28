import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompradorService } from '../comprador.service';
import { CompradorDTO } from '../dto/comprador.dto';

@Component({
  selector: 'app-cadastro-comprador',
  templateUrl: './cadastro-comprador.component.html'
})
export class CadastroCompradorComponent implements OnInit {
  compradorForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private compradorService: CompradorService,
    private router: Router
  ) {
    this.compradorForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]] // ✅ Campo email adicionado
    });
  }

  ngOnInit(): void {}

  salvar(): void {
    if (this.compradorForm.valid) {
      const comprador: CompradorDTO = this.compradorForm.value;
      this.compradorService.criar(comprador).subscribe({
        next: () => {
          this.router.navigate(['/comprador/lista-comprador']);
        },
        error: (err) => {
          console.error('Erro ao criar comprador:', err);
        }
      });
    }
  }
}
