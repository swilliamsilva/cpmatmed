import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-detalhe-pedido',
  templateUrl: './detalhe-pedido.component.html',
  styleUrls: ['./detalhe-pedido.component.scss']
})
export class DetalhePedidoComponent implements OnInit {
  pedidoId!: number;
  pedido: any;

  constructor(
    private route: ActivatedRoute,
    private pedidoService: PedidoService
  ) {}

  ngOnInit(): void {
    this.pedidoId = Number(this.route.snapshot.paramMap.get('id'));
    this.pedidoService.buscarPedidoPorId(this.pedidoId).subscribe(data => {
      this.pedido = data;
    });
  }
}
