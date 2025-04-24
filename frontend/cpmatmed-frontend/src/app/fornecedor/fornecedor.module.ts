import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { FornecedorRoutingModule } from './fornecedor-routing.module';
import { CadastroFornecedorComponent } from './cadastro-fornecedor/cadastro-fornecedor.component';
import { ListaFornecedorComponent } from './lista-fornecedor/lista-fornecedor.component';
import { DetalheFornecedorComponent } from './detalhe-fornecedor/detalhe-fornecedor.component';

@NgModule({
  declarations: [
    // Declarando os componentes que fazem parte deste módulo
    CadastroFornecedorComponent,
    ListaFornecedorComponent,
    DetalheFornecedorComponent
  ],
  imports: [
    // Módulos necessários para os componentes de fornecedor
    CommonModule,  // Módulo comum do Angular, necessário para muitos componentes padrões (ngIf, ngFor, etc)
    ReactiveFormsModule,  // Necessário para formulários reativos
    FornecedorRoutingModule  // Roteamento específico para este módulo
  ]
})
export class FornecedorModule {}
