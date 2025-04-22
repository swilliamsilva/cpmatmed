import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-comprador',
  templateUrl: './cadastro-comprador.component.html'
})
export class CadastroCompradorComponent implements OnInit {
  compradorForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.compradorForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.compradorForm.valid) {
      console.log(this.compradorForm.value);
      // lógica para enviar os dados ao backend
    }
  }
}
