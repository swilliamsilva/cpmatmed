import { Component, OnInit } from '@angular/core';
import { FornecedorService } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FornecedorDTO } from '../dto/fornecedor.dto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cadastro-fornecedor',
  templateUrl: './cadastro-fornecedor.component.html',
})
export class CadastroFornecedorComponent implements OnInit {
  fornecedor: FornecedorDTO;
  errorMessage: string = '';

  constructor(
    private fornecedorService: FornecedorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Inicialização correta do objeto
    this.fornecedor = { nome: '', cnpj: '' }; // Adicione todos campos obrigatórios
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.fornecedorService.buscarPorId(+id).subscribe({
        next: (data) => this.fornecedor = data,
        error: (err) => this.errorMessage = 'Erro ao buscar fornecedor'
      });
    }
  }

  salvar(): void {
    this.errorMessage = ''; // Reset mensagem de erro

    if (this.fornecedor.id) {
      this.fornecedorService.atualizar(this.fornecedor.id, this.fornecedor).subscribe({
        next: () => this.router.navigate(['/fornecedor/lista-fornecedor']),
        error: (err) => this.errorMessage = 'Erro ao atualizar fornecedor: ' + err.message
      });
    } else {
      this.fornecedorService.criar(this.fornecedor).subscribe({
        next: () => this.router.navigate(['/fornecedor/lista-fornecedor']),
        error: (err) => this.errorMessage = 'Erro ao criar fornecedor: ' + err.message
      });
    }
  }
}
