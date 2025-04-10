import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PedidoService } from '../pedido.service';
import { Pedido } from '../pedido.model';

@Component({
    selector: 'app-detalhes-pedido',
    templateUrl: './detalhes-pedido.component.html',
    styleUrls: ['./detalhes-pedido.component.css'],
    standalone: false
})
export class DetalhesPedidoComponent implements OnInit {

  pedido: Pedido | undefined;

  constructor(
    private route: ActivatedRoute,
    private pedidoService: PedidoService
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    
    this.pedidoService.getPedidos(id).subscribe(data => { 

      this.pedido = data;
    });
  }
}
