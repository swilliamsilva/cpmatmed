import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FornecedorRoutingModule } from './fornecedor-routing.module';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Component } from '@angular/core';

// Componentes dummy para teste
@Component({ template: '' })
class DummyListaComponent {}

@Component({ template: '' })
class DummyCadastroComponent {}

describe('FornecedorRoutingModule', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          {
            path: 'fornecedor',
            children: [
              { path: 'lista-fornecedor', component: DummyListaComponent },
              {
                path: 'cadastro-fornecedor',
                component: DummyCadastroComponent,
              },
              { path: '', redirectTo: 'lista-fornecedor', pathMatch: 'full' },
              { path: '**', redirectTo: 'lista-fornecedor' },
            ],
          },
        ]),
      ],
      declarations: [DummyListaComponent, DummyCadastroComponent],
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('deve carregar a rota de lista de fornecedores', fakeAsync(() => {
    router.navigate(['/fornecedor/lista-fornecedor']);
    tick();
    expect(location.path()).toBe('/fornecedor/lista-fornecedor');
  }));

  it('deve redirecionar rotas inválidas para lista de fornecedores', fakeAsync(() => {
    router.navigate(['/fornecedor/rota-invalida']);
    tick();
    expect(location.path()).toBe('/fornecedor/lista-fornecedor');
  }));
});
