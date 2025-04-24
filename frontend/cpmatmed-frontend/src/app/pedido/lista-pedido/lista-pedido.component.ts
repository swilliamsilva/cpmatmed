import { Component, OnInit } from '@angular/core';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-lista-pedido',
  templateUrl: './lista-pedido.component.html',
  styleUrls: ['./lista-pedido.component.scss']
})
export class ListaPedidoComponent implements OnInit {
  pedidos: any[] = [];
  errorMessage: string = ''; // Definindo errorMessage

  constructor(private pedidoService: PedidoService) {}

  ngOnInit(): void {
    this.pedidoService.listarPedidos().subscribe(
      (data) => {
        this.pedidos = data;
      },
      (error) => {
        this.errorMessage = 'Erro ao carregar os pedidos'; // Atribui a mensagem de erro
      }
    );
  }
}
