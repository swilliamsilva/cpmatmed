import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProdutoRoutingModule } from './produto-routing.module';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';

@NgModule({
  declarations: [
    ListaProdutoComponent,
    CadastroProdutoComponent,
    DetalheProdutoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule {}
