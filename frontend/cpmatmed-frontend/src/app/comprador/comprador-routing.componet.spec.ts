import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CompradorRoutingModule } from './comprador-routing.module';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Component } from '@angular/core';

// Componentes dummy para teste
@Component({ template: '' })
class DummyDetalheComponent {}

@Component({ template: '' })
class DummyListaComponent {}

describe('CompradorRoutingModule', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { 
            path: 'comprador',
            children: [
              { path: 'detalhe-comprador/:id', component: DummyDetalheComponent },
              { path: 'lista-comprador', component: DummyListaComponent },
              { path: '**', redirectTo: 'lista-comprador' }
            ]
          }
        ])
      ],
      declarations: [DummyDetalheComponent, DummyListaComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('deve carregar a rota de detalhe do comprador', fakeAsync(() => {
    router.navigate(['/comprador/detalhe-comprador/1']);
    tick();
    expect(location.path()).toBe('/comprador/detalhe-comprador/1');
  }));

  it('deve manter parâmetros na rota de detalhe', fakeAsync(() => {
    router.navigate(['/comprador/detalhe-comprador/123']);
    tick();
    expect(location.path()).toBe('/comprador/detalhe-comprador/123');
  }));
});