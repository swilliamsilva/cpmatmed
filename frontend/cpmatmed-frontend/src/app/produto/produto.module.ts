// produto/produto.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ProdutoRoutingModule } from './produto-routing.module';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';

@NgModule({
  declarations: [
    CadastroProdutoComponent,
    ListaProdutoComponent,
    DetalheProdutoComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ProdutoRoutingModule // Deve ser o último
  ]
})
export class ProdutoModule {}