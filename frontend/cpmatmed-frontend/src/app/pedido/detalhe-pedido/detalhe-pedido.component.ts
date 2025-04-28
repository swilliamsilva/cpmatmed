import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-detalhe-pedido',
  templateUrl: './detalhe-pedido.component.html',
})
export class DetalhePedidoComponent implements OnInit {
  pedidoId!: number;
  pedido: any = {};
  errorMessage: string = ''; // Para capturar erros

  constructor(
    private route: ActivatedRoute,
    private pedidoService: PedidoService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      this.pedidoId = id;
      this.pedidoService.buscarPorId(id).subscribe(
        (data) => {
          this.pedido = data;
        },
        (error) => {
          this.errorMessage = 'Erro ao carregar os dados do pedido';
        }
      );
    }
  }
}
