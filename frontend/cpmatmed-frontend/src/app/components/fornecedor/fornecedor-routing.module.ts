import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';
import { DetalheFornecedorComponent } from './detalhe-fornecedor/detalhe-fornecedor.component';

const routes: Routes = [
  { path: 'cadastro-fornecedor', component: CadastroFornecedorComponent },
  { path: 'lista-fornecedor', component: ListaFornecedorComponent },
  { path: 'detalhes-fornecedor/:id', component: DetalheFornecedorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FornecedorRoutingModule {}
