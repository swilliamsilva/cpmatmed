import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalhe-produto',
  templateUrl: './detalhe-produto.component.html'
})
export class DetalheProdutoComponent implements OnInit {
  produtoId!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.produtoId = +this.route.snapshot.paramMap.get('id')!;
    // Aqui você pode buscar os detalhes do produto usando o ID
  }
}
