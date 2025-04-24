import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FornecedorService } from '../fornecedor.service';

@Component({
  selector: 'app-detalhe-fornecedor',
  templateUrl: './detalhe-fornecedor.component.html'
})
export class DetalheFornecedorComponent implements OnInit {
  fornecedorId!: number;
  fornecedor: any = {};  // Inicializa fornecedor como objeto vazio
  errorMessage: string = '';  // Para mensagens de erro

  constructor(
    private route: ActivatedRoute,
    private fornecedorService: FornecedorService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      this.fornecedorId = id;
      this.fornecedorService.buscarPorId(id).subscribe(
        (data) => {
          this.fornecedor = data;
        },
        (error) => {
          this.errorMessage = 'Erro ao carregar os dados do fornecedor';
        }
      );
    }
  }
}
