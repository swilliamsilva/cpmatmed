// comprador.module.ts - cpmatmed

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule aqui
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CompradorRoutingModule } from './comprador-routing.module';
import { ListaCompradorComponent } from './lista-comprador/lista-comprador.component';
import { CadastroCompradorComponent } from './cadastro-comprador/cadastro-comprador.component';
import { DetalheCompradorComponent } from './detalhe-comprador/detalhe-comprador.component';

@NgModule({
  declarations: [
    ListaCompradorComponent,
    CadastroCompradorComponent, //<<
    DetalheCompradorComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule, // Adicione o HttpClientModule no array de imports
    ReactiveFormsModule,
    CompradorRoutingModule,
  ],
})
export class CompradorModule {}
