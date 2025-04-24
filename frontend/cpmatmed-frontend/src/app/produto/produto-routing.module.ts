import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';

const routes: Routes = [
  { path: '', redirectTo: 'lista-produto', pathMatch: 'full' },
  { path: 'lista-produto', component: ListaProdutoComponent },
  { path: 'cadastro-produto', component: CadastroProdutoComponent },
  { path: 'detalhe-produto/:id', component: DetalheProdutoComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProdutoRoutingModule {}
