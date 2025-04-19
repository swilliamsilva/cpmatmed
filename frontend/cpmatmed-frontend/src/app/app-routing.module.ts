import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Pedido
import { CadastroPedidoComponent } from './components/pedido/cadastro-pedido/cadastro-pedido.component';
import { ListaPedidoComponent } from './components/pedido/lista-pedido/lista-pedido.component';
import { DetalhePedidoComponent } from './components/pedido/detalhe-pedido/detalhe-pedido.component';

// Comprador
import { CadastroCompradorComponent } from './components/comprador/cadastro-comprador/cadastro-comprador.component';
import { ListaCompradorComponent } from './components/comprador/lista-comprador/lista-comprador.component';
import { DetalheCompradorComponent } from './components/comprador/detalhe-comprador/detalhe-comprador.component';

// Fornecedor
import { CadastroFornecedorComponent } from './components/fornecedor/cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './components/fornecedor/lista-fornecedor/lista-fornecedor.component';
import { DetalheFornecedorComponent } from './components/fornecedor/detalhe-fornecedor/detalhe-fornecedor.component';

// Produto
import { CadastroProdutoComponent } from './components/produto/cadastro-produto/cadastro-produto.component';
import { ListaProdutoComponent } from './components/produto/lista-produto/lista-produto.component';
import { DetalheProdutoComponent } from './components/produto/detalhe-produto/detalhe-produto.component';

const routes: Routes = [
  // Pedido
  { path: 'pedido/cadastro-pedido', component: CadastroPedidoComponent },
  { path: 'pedido/lista-pedido', component: ListaPedidoComponent },
  { path: 'pedido/detalhes-pedido/:id', component: DetalhePedidoComponent },

  // Comprador
  { path: 'comprador/cadastro-comprador',component: CadastroCompradorComponent,},
  { path: 'comprador/lista-comprador', component: ListaCompradorComponent },
  { path: 'comprador/comprador-pedido/:id',component: DetalheCompradorComponent,},

  // Fornecedor
  {path: 'fornecedor/cadastro-fornecedor',component: CadastroFornecedorComponent,},
  {path: 'fornecedor/lista-fornecedor', component: ListaFornecedorComponent },
  {path: 'fornecedor/detalhes-fornecedor/:id',component: DetalheFornecedorComponent,},

  // Produto
  {path: 'produto/cadastro-produto', component: CadastroProdutoComponent },
  {path: 'produto/lista-produto', component: ListaProdutoComponent },
  {path: 'produto/detalhes-produto/:id', component: DetalheProdutoComponent },

  // Redirecionamento padrão
  { path: '', redirectTo: 'pedido/lista-pedido', pathMatch: 'full' },
  { path: '**', redirectTo: 'pedido/lista-pedido' }, // rota coringa
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
