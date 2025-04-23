import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
    FormsModule,
    ReactiveFormsModule,
    CompradorRoutingModule
  ]
})
export class CompradorModule {}
