import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CadastroPedidoComponent } from './cadastro-pedido/cadastro-pedido.component';
import { DetalhePedidoComponent } from './detalhe-pedido/detalhe-pedido.component';
import { ListaPedidoComponent } from './lista-pedido/lista-pedido.component';
import { PedidoRoutingModule } from './pedido-routing.module';

@NgModule({
  declarations: [
    CadastroPedidoComponent,
    DetalhePedidoComponent,
    ListaPedidoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,  // Adicionado ReactiveFormsModule
    PedidoRoutingModule
  ]
})
export class PedidoModule {}
