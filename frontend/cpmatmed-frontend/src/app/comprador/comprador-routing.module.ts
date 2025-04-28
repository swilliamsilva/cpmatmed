import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaCompradorComponent } from './lista-comprador/lista-comprador.component';
import { CadastroCompradorComponent } from './cadastro-comprador/cadastro-comprador.component';
import { DetalheCompradorComponent } from './detalhe-comprador/detalhe-comprador.component';

const routes: Routes = [
  { path: '', redirectTo: 'lista-comprador', pathMatch: 'full' }, // Rota padrão
  { path: 'lista-comprador', component: ListaCompradorComponent },
  { path: 'cadastro-comprador', component: CadastroCompradorComponent },
  { path: 'detalhe-comprador/:id', component: DetalheCompradorComponent },
  { path: '**', redirectTo: 'lista-comprador' } // Rota curinga
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompradorRoutingModule {}