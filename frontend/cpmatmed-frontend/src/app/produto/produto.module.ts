import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule aqui
import { ProdutoRoutingModule } from './produto-routing.module';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    ListaProdutoComponent,
    CadastroProdutoComponent,
    DetalheProdutoComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,  // Adicione o HttpClientModule no array de imports
    ReactiveFormsModule, 
    ProdutoRoutingModule,
  ],
})
export class ProdutoModule {}
