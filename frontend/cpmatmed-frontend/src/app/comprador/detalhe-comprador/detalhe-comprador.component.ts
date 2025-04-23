import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalhe-comprador',
  templateUrl: './detalhe-comprador.component.html'
})
export class DetalheCompradorComponent implements OnInit {
  compradorId!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.compradorId = +this.route.snapshot.paramMap.get('id')!;
    // buscar os dados do comprador pelo ID
  }
}
