import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'pedido/lista-pedido', pathMatch: 'full' },
  {
    path: 'produto',
    loadChildren: () => import('./produto/produto.module').then(m => m.ProdutoModule),
  },
  {
    path: 'comprador',
    loadChildren: () => import('./comprador/comprador.module').then(m => m.CompradorModule),
  },
  {
    path: 'fornecedor',
    loadChildren: () => import('./fornecedor/fornecedor.module').then(m => m.FornecedorModule),
  },
  {
    path: 'pedido',
    loadChildren: () => import('./pedido/pedido.module').then(m => m.PedidoModule),
  },
  { path: '**', redirectTo: 'pedido/lista-pedido' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}