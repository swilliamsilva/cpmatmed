import { Component, OnInit } from '@angular/core';
import { FornecedorService, Fornecedor } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-fornecedor',
  templateUrl: './cadastro-fornecedor.component.html'
})
export class CadastroFornecedorComponent implements OnInit {
  fornecedor: Fornecedor = {
    nome: ''  // Inicialização com um valor vazio para nome
  };

  constructor(
    private fornecedorService: FornecedorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.fornecedorService.buscarPorId(id).subscribe(fornecedor => {
        this.fornecedor = fornecedor;
      });
    }
  }

  salvar(): void {
    if (this.fornecedor.id) {
      this.fornecedorService.atualizar(this.fornecedor.id, this.fornecedor).subscribe(() => {
        this.router.navigate(['/fornecedor/lista-fornecedor']);
      });
    } else {
      this.fornecedorService.criar(this.fornecedor).subscribe(() => {
        this.router.navigate(['/fornecedor/lista-fornecedor']);
      });
    }
  }
}
