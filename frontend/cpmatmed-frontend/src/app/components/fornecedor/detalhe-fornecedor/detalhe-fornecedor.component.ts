import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalhe-fornecedor',
  templateUrl: './detalhe-fornecedor.component.html'
})
export class DetalheFornecedorComponent implements OnInit {
  fornecedorId!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.fornecedorId = +this.route.snapshot.paramMap.get('id')!;
    // Aqui você pode buscar os detalhes do fornecedor pelo ID
  }
}
