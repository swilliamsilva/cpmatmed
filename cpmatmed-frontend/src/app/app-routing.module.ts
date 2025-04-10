import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaPedidosComponent } from './lista-pedidos/lista-pedidos.component';
import { DetalhesPedidoComponent } from './detalhes-pedido/detalhes-pedido.component';
import { CadastroPedidoComponent } from './cadastro-pedido/cadastro-pedido.component';

const routes: Routes = [
  { path: '', component: ListaPedidosComponent },
  { path: 'pedidos/:id', component: DetalhesPedidoComponent },
  { path: 'cadastro', component: CadastroPedidoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
