// produto/produto-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';

const routes: Routes = [
  { path: '', component: ListaProdutoComponent },
  { path: 'novo', component: CadastroProdutoComponent },
  { path: ':id', component: DetalheProdutoComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProdutoRoutingModule {}