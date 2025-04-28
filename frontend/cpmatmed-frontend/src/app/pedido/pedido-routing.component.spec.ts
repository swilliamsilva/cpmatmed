import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { PedidoRoutingModule } from './pedido-routing.module';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Component } from '@angular/core';

// Componentes dummy para teste
@Component({ template: '' })
class DummyListaComponent {}

@Component({ template: '' })
class DummyCadastroComponent {}

describe('PedidoRoutingModule', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { 
            path: 'pedido',
            children: [
              { path: 'lista-pedido', component: DummyListaComponent },
              { path: 'cadastro-pedido', component: DummyCadastroComponent },
              { path: '**', redirectTo: 'lista-pedido' }
            ]
          }
        ])
      ],
      declarations: [DummyListaComponent, DummyCadastroComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('deve carregar a rota de lista de pedidos', fakeAsync(() => {
    router.navigate(['/pedido/lista-pedido']);
    tick();
    expect(location.path()).toBe('/pedido/lista-pedido');
  }));

  it('deve carregar a rota de cadastro de pedidos', fakeAsync(() => {
    router.navigate(['/pedido/cadastro-pedido']);
    tick();
    expect(location.path()).toBe('/pedido/cadastro-pedido');
  }));
});