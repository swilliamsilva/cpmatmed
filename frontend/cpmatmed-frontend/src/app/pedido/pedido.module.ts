import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule aqui
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
    HttpClientModule,  // Adicione o HttpClientModule no array de imports
    ReactiveFormsModule,  // Adicionado ReactiveFormsModule
    PedidoRoutingModule
  ]
})
export class PedidoModule {}
