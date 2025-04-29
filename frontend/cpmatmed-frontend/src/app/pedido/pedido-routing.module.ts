import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroPedidoComponent } from './cadastro-pedido/cadastro-pedido.component';
import { ListaPedidoComponent } from './lista-pedido/lista-pedido.component';
import { DetalhePedidoComponent } from './detalhe-pedido/detalhe-pedido.component';

const routes: Routes = [
  { path: '', redirectTo: 'lista-pedido', pathMatch: 'full' },
  { path: 'cadastro-pedido', component: CadastroPedidoComponent },
  { path: 'lista-pedido', component: ListaPedidoComponent },
  { path: 'detalhe-pedido/:id', component: DetalhePedidoComponent },
  { path: '**', redirectTo: 'lista-pedido' }, // Rota inválida redireciona para a lista de pedidos
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PedidoRoutingModule {}
