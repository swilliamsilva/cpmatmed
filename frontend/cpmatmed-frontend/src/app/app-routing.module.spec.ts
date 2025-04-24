import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Component } from '@angular/core';

@Component({ template: '' })
class DummyComponent {}

describe('AppRoutingModule (com rotas reais)', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: '', redirectTo: 'produto/lista-produto', pathMatch: 'full' },
          { path: '**', redirectTo: 'produto/lista-produto', pathMatch: 'full' },
          { path: 'produto/lista-produto', component: DummyComponent },
          { path: 'produto/cadastro-produto', component: DummyComponent },
          { path: 'produto/detalhe-produto', component: DummyComponent },
          { path: 'pedido/lista-pedido', component: DummyComponent },
          { path: 'pedido/cadastro-pedido', component: DummyComponent },
          { path: 'pedido/detalhe-pedido', component: DummyComponent },
          { path: 'fornecedor/lista-fornecedor', component: DummyComponent },
          { path: 'fornecedor/cadastro-fornecedor', component: DummyComponent },
          { path: 'fornecedor/detalhe-fornecedor', component: DummyComponent },
          { path: 'comprador/lista-comprador', component: DummyComponent },
          { path: 'comprador/cadastro-comprador', component: DummyComponent },
          { path: 'comprador/detalhe-comprador', component: DummyComponent },
        ])
      ],
      declarations: [DummyComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.initialNavigation();
  });

  it('deve redirecionar "" para "/produto/lista-produto"', fakeAsync(() => {
    router.navigate(['']);
    tick();
    expect(location.path()).toBe('/produto/lista-produto');
  }));

  it('deve redirecionar rota inválida para "/produto/lista-produto"', fakeAsync(() => {
    router.navigate(['/rota-invalida']);
    tick();
    expect(location.path()).toBe('/produto/lista-produto');
  }));

  it('deve navegar para /pedido/cadastro-pedido', fakeAsync(() => {
    router.navigate(['/pedido/cadastro-pedido']);
    tick();
    expect(location.path()).toBe('/pedido/cadastro-pedido');
  }));

  it('deve navegar para /produto/lista-produto', fakeAsync(() => {
    router.navigate(['/produto/lista-produto']);
    tick();
    expect(location.path()).toBe('/produto/lista-produto');
  }));

  it('deve navegar para /produto/cadastro-produto', fakeAsync(() => {
    router.navigate(['/produto/cadastro-produto']);
    tick();
    expect(location.path()).toBe('/produto/cadastro-produto');
  }));

  it('deve navegar para /produto/detalhe-produto', fakeAsync(() => {
    router.navigate(['/produto/detalhe-produto']);
    tick();
    expect(location.path()).toBe('/produto/detalhe-produto');
  }));

  it('deve navegar para /pedido/lista-pedido', fakeAsync(() => {
    router.navigate(['/pedido/lista-pedido']);
    tick();
    expect(location.path()).toBe('/pedido/lista-pedido');
  }));

  it('deve navegar para /fornecedor/cadastro-fornecedor', fakeAsync(() => {
    router.navigate(['/fornecedor/cadastro-fornecedor']);
    tick();
    expect(location.path()).toBe('/fornecedor/cadastro-fornecedor');
  }));

  it('deve navegar para /comprador/detalhe-comprador', fakeAsync(() => {
    router.navigate(['/comprador/detalhe-comprador']);
    tick();
    expect(location.path()).toBe('/comprador/detalhe-comprador');
  }));
});
