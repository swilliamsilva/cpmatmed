import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';

const routes: Routes = [
  { path: 'fornecedor/lista-fornecedor', component: ListaFornecedorComponent },
  { path: 'fornecedor/cadastro-fornecedor', component: CadastroFornecedorComponent },
  { path: 'fornecedor/cadastro-fornecedor/:id', component: CadastroFornecedorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FornecedorRoutingModule {}
