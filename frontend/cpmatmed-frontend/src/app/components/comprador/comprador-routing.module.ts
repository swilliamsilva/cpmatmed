import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroCompradorComponent } from './cadastro-comprador/cadastro-comprador.component';
import { ListaCompradorComponent } from './lista-comprador/lista-comprador.component';
import { DetalheCompradorComponent } from './detalhe-comprador/detalhe-comprador.component';

const routes: Routes = [
  { path: 'cadastro-comprador', component: CadastroCompradorComponent },
  { path: 'lista-comprador', component: ListaCompradorComponent },
  { path: 'detalhe-comprador/:id', component: DetalheCompradorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompradorRoutingModule {}
