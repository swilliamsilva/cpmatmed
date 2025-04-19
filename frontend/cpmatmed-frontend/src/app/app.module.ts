import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FornecedorModule } from './components/fornecedor/fornecedor.module';
import { ProdutoModule } from './components/produto/produto.module';

import { CadastroPedidoComponent } from './components/pedido/cadastro-pedido/cadastro-pedido.component';
import { DetalhePedidoComponent } from './components/pedido/detalhe-pedido/detalhe-pedido.component';
import { ListaPedidoComponent } from './components/pedido/lista-pedido/lista-pedido.component';

import { CadastroCompradorComponent } from './components/comprador/cadastro-comprador/cadastro-comprador.component';
import { DetalheCompradorComponent } from './components/comprador/detalhe-comprador/detalhe-comprador.component';
import { ListaCompradorComponent } from './components/comprador/lista-comprador/lista-comprador.component';


@NgModule({
  declarations: [
    AppComponent,

    // Somente os componentes que NÃO estão em módulos específicos
    CadastroPedidoComponent,
    DetalhePedidoComponent,
    ListaPedidoComponent,

    CadastroCompradorComponent,
    DetalheCompradorComponent,
    ListaCompradorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FornecedorModule, // ✅ já contém os componentes de Fornecedor
    ProdutoModule      // ✅ já contém os componentes de Produto
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
