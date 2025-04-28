import { FornecedorDTO } from '../dto/fornecedor.dto';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FornecedorService } from '../fornecedor.service';

@Component({
  selector: 'app-detalhe-fornecedor',
  templateUrl: './detalhe-fornecedor.component.html',
})
export class DetalheFornecedorComponent implements OnInit {
  fornecedorId: number | null = null;
  fornecedor: FornecedorDTO | null = null;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private fornecedorService: FornecedorService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? parseInt(idParam, 10) : null;

    if (id === null || isNaN(id)) {
      this.errorMessage = 'ID do fornecedor inválido';
      return;
    }

    this.fornecedorId = id;
    this.carregarFornecedor();
  }

  private carregarFornecedor(): void {
    this.fornecedorService.buscarPorId(this.fornecedorId!).subscribe(
      (data: FornecedorDTO) => {
        this.fornecedor = data;
      },
      (error: any) => {
        this.errorMessage = 'Erro ao carregar os dados do fornecedor';
      }
    );
  }
}
