import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Fornecedor, FornecedorService } from '../fornecedor.service';

@Component({
  selector: 'app-detalhe-fornecedor',
  templateUrl: './detalhe-fornecedor.component.html'
})
export class DetalheFornecedorComponent implements OnInit {
  fornecedorId!: number;
  fornecedor?: Fornecedor;

  constructor(
    private route: ActivatedRoute,
    private fornecedorService: FornecedorService
  ) {}

  ngOnInit(): void {
    this.fornecedorId = +this.route.snapshot.paramMap.get('id')!;
    this.fornecedorService.buscarPorId(this.fornecedorId).subscribe(fornecedor => {
      this.fornecedor = fornecedor;
    });
  }
}
