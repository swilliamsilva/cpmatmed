// Classe: ListaProdutoComponent.spec.ts
// Aplicação: cpmatmed

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaProdutoComponent } from './lista-produto.component';
import { ProdutoService } from '../produto.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ListaProdutoComponent', () => {
  let component: ListaProdutoComponent;
  let fixture: ComponentFixture<ListaProdutoComponent>;
  let produtoServiceMock: any;

  beforeEach(() => {
    produtoServiceMock = jasmine.createSpyObj('ProdutoService', ['listar']);
    produtoServiceMock.listar.and.returnValue(of([
      { id: 1, nome: 'Produto A', precoUnitario: 10, quantidade: 5, fornecedorId: 1 },
      { id: 2, nome: 'Produto B', precoUnitario: 20, quantidade: 10, fornecedorId: 2 }
    ]));

    TestBed.configureTestingModule({
      declarations: [ListaProdutoComponent],
      imports: [RouterTestingModule],
      providers: [{ provide: ProdutoService, useValue: produtoServiceMock }]
    });

    fixture = TestBed.createComponent(ListaProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Executa ngOnInit
  });

  it('deve carregar e exibir a lista de produtos', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const linhas = compiled.querySelectorAll('tbody tr');

    expect(component.produtos.length).toBe(2);
    expect(linhas.length).toBe(2);
    expect(linhas[0].textContent).toContain('Produto A');
    expect(linhas[1].textContent).toContain('Produto B');
  });
});
