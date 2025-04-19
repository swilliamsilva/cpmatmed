import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';
import { FornecedorRoutingModule } from './fornecedor-routing.module';

@NgModule({
  declarations: [
    CadastroFornecedorComponent,
    ListaFornecedorComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FornecedorRoutingModule
  ]
})
export class FornecedorModule { }
