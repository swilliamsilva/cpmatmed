import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroCompradorComponent } from './cadastro-comprador/cadastro-comprador.component';
import { ListaCompradorComponent } from './lista-comprador/lista-comprador.component';
import { DetalheCompradorComponent } from './detalhe-comprador/detalhe-comprador.component';

const routes: Routes = [
  // Rota para cadastrar um comprador
  { path: 'cadastro-comprador', component: CadastroCompradorComponent },
  
  // Rota para listar todos os compradores
  { path: 'lista-comprador', component: ListaCompradorComponent },

  // Rota para ver os detalhes de um comprador, com um parâmetro de id na URL
  { path: 'detalhe-comprador/:id', component: DetalheCompradorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],  // Importando o módulo de roteamento para este módulo
  exports: [RouterModule]  // Exportando o RouterModule para ser acessado por outros módulos
})
export class CompradorRoutingModule {}
