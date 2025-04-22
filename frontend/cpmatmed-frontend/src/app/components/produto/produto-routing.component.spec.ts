import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ProdutoRoutingModule } from './produto-routing.module';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';
import { DetalheProdutoComponent } from './detalhe-produto/detalhe-produto.component';
import { NgModule } from '@angular/core';

@NgModule({
  imports: [
    RouterTestingModule.withRoutes([
      { path: '', component: ListaProdutoComponent },
      { path: 'novo', component: CadastroProdutoComponent },
      { path: ':id', component: DetalheProdutoComponent }
    ])
  ]
})
class TestModule {}

describe('ProdutoRouting', () => {
  let location: Location;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestModule],
      declarations: [CadastroProdutoComponent, ListaProdutoComponent, DetalheProdutoComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    router.initialNavigation();
  });

  it('deve navegar para /novo', async () => {
    await router.navigate(['/novo']);
    expect(location.path()).toBe('/novo');
  });
});
