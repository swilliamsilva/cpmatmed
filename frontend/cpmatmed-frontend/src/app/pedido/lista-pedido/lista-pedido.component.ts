import { Component, OnInit } from '@angular/core';
import { PedidoService } from '../pedido.service';

@Component({
  selector: 'app-lista-pedido',
  templateUrl: './lista-pedido.component.html',
  styleUrls: ['./lista-pedido.component.scss']
})
export class ListaPedidoComponent implements OnInit {
  pedidos: any[] = [];
  errorMessage: string = '';

  constructor(private pedidoService: PedidoService) {}

  ngOnInit(): void {
    this.carregarPedidos();
  }

  carregarPedidos(): void {
    this.pedidoService.listar().subscribe({
      next: (data) => {
        this.pedidos = Array.isArray(data) ? data : [];
      },
      error: (error) => {
        this.pedidos = [];
        this.errorMessage = 'Erro ao carregar os pedidos';
        console.error('Erro ao carregar pedidos', error);
      }
    });
  }

  excluirpedido(id: number): void {
    this.pedidoService.excluir(id).subscribe({
      next: () => {
        this.carregarPedidos(); // Recarrega a lista após exclusão
      },
      error: (error) => {
        this.errorMessage = 'Erro ao excluir pedido: ' + error.message;
        console.error('Erro na exclusão', error);
      }
    });
  }
}
