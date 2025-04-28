import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { ProdutoModule } from './produto/produto.module';

describe('AppRoutingModule', () => {
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        AppRoutingModule,
        RouterTestingModule.withRoutes([])
      ],
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.initialNavigation();
  });

  it('deve redirecionar rota raiz para lista de produtos', fakeAsync(() => {
    router.navigate(['']);
    tick();
    expect(location.path()).toBe('/produto/lista-produto');
  }));

  it('deve redirecionar rotas inválidas para lista de produtos', fakeAsync(() => {
    router.navigate(['/rota-inexistente']);
    tick();
    expect(location.path()).toBe('/produto/lista-produto');
  }));
});