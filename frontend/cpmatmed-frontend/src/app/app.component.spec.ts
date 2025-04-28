import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { RouterTestingModule } from '@angular/router/testing';

import { ProdutoModule } from '../../src/app/produto/produto.module';
import { PedidoModule } from '../../src/app/pedido/pedido.module';
import { FornecedorModule } from '../../src/app/fornecedor/fornecedor.module';
import { CompradorModule } from '../../src/app/comprador/comprador.module';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ProdutoModule,
        PedidoModule,
        FornecedorModule,
        CompradorModule
      ],
      declarations: [AppComponent]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'cpmatmed'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('cpmatmed-frontend');
  });
});
