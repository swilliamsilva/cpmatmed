// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { 
    path: 'produto', 
    loadChildren: () => import('./produto/produto.module').then(m => m.ProdutoModule) 
  },
  { 
    path: 'pedido', 
    loadChildren: () => import('./pedido/pedido.module').then(m => m.PedidoModule) 
  },
  { 
    path: 'comprador', 
    loadChildren: () => import('./comprador/comprador.module').then(m => m.CompradorModule) 
  },
  { 
    path: 'fornecedor', 
    loadChildren: () => import('./fornecedor/fornecedor.module').then(m => m.FornecedorModule) 
  },
  { path: '', redirectTo: '/produto', pathMatch: 'full' },
  { path: '**', redirectTo: '/produto' } // Redireciona para produto em caso de 404
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}