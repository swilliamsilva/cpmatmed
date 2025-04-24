import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';
import { DetalheFornecedorComponent } from './detalhe-fornecedor/detalhe-fornecedor.component';

const routes: Routes = [
  { path: '', redirectTo: 'lista-fornecedor', pathMatch: 'full' },  // Redireciona a rota vazia para a lista de fornecedores
  { path: 'lista-fornecedor', component: ListaFornecedorComponent },
  { path: 'cadastro-fornecedor', component: CadastroFornecedorComponent },
  { path: 'detalhe-fornecedor/:id', component: DetalheFornecedorComponent },
  { path: '**', redirectTo: 'lista-fornecedor' }  // Redireciona rotas inválidas para a lista de fornecedores
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FornecedorRoutingModule {}
