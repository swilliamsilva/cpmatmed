import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { FornecedorRoutingModule } from './fornecedor-routing.module';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';
import { DetalheFornecedorComponent } from './detalhe-fornecedor/detalhe-fornecedor.component';

@NgModule({
  declarations: [
    CadastroFornecedorComponent,
    ListaFornecedorComponent,
    DetalheFornecedorComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FornecedorRoutingModule
  ]
})
export class FornecedorModule {}
