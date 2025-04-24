import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';  // Se apenas formulários reativos forem usados, FormsModule pode ser removido.

import { CadastroCompradorComponent } from './cadastro-comprador/cadastro-comprador.component';
import { DetalheCompradorComponent } from './detalhe-comprador/detalhe-comprador.component';
import { ListaCompradorComponent } from './lista-comprador/lista-comprador.component';
import { CompradorRoutingModule } from './comprador-routing.module';

@NgModule({
  declarations: [
    CadastroCompradorComponent,
    DetalheCompradorComponent,
    ListaCompradorComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,  // Módulo de formulários reativos, adequado se você está usando Reactive Forms
    CompradorRoutingModule
  ]
})
export class CompradorModule {}
