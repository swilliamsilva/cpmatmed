import { Component, OnInit } from '@angular/core';
import { PedidoService } from '../pedido.service';
import { Pedido } from '../pedido.model';

@Component({
    selector: 'app-lista-pedidos',
    templateUrl: './lista-pedidos.component.html',
    styleUrls: ['./lista-pedidos.component.css'],
    standalone: false
})
export class ListaPedidosComponent implements OnInit {

  pedidos: Pedido[] = [];

  constructor(private pedidoService: PedidoService) { }

  ngOnInit(): void {
     this.pedidoService.getPedidos().subscribe(data => { data => {

      this.pedidos = data;
    });
  }
}
