import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { Component } from '@angular/core';

@Component({ template: '' }) class DummyComponent {}

@NgModule({
  declarations: [DummyComponent],
  exports: [DummyComponent]
})
class DummyModule {}

describe('AppRoutingModule', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([]),
        AppRoutingModule
      ],
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('deve redirecionar "" para "/produto"', fakeAsync(() => {
    router.navigate(['']);
    tick();
    expect(location.path()).toBe('/produto');
  }));

  it('deve redirecionar rota inválida para "/produto"', fakeAsync(() => {
    router.navigate(['/rota-invalida']);
    tick();
    expect(location.path()).toBe('/produto');
  }));

  it('deve navegar para /produto', fakeAsync(() => {
    router.navigate(['/produto']);
    tick();
    expect(location.path()).toBe('/produto');
  }));

  it('deve navegar para /produto/novo', fakeAsync(() => {
    router.navigate(['/produto/novo']);
    tick();
    expect(location.path()).toBe('/produto/novo');
  }));

  it('deve navegar para /pedido', fakeAsync(() => {
    router.navigate(['/pedido']);
    tick();
    expect(location.path()).toBe('/pedido');
  }));

  it('deve navegar para /fornecedor', fakeAsync(() => {
    router.navigate(['/fornecedor']);
    tick();
    expect(location.path()).toBe('/fornecedor');
  }));

  it('deve navegar para /comprador', fakeAsync(() => {
    router.navigate(['/comprador']);
    tick();
    expect(location.path()).toBe('/comprador');
  }));
});
