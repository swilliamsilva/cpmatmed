import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaProdutoComponent } from './lista-produto.component';
import { ProdutoService } from '../produto.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ListaProdutoComponent', () => {
  let component: ListaProdutoComponent;
  let fixture: ComponentFixture<ListaProdutoComponent>;
  let mockService: any;

  beforeEach(() => {
    mockService = jasmine.createSpyObj(['listar']);
    mockService.listar.and.returnValue(of([]));

    TestBed.configureTestingModule({
      declarations: [ListaProdutoComponent],
      imports: [RouterTestingModule],
      providers: [{ provide: ProdutoService, useValue: mockService }]
    });

    fixture = TestBed.createComponent(ListaProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deve carregar produtos ao inicializar', () => {
    expect(mockService.listar).toHaveBeenCalled();
  });

  it('deve ter lista de produtos vazia inicialmente', () => {
    expect(component.produtos.length).toBe(0);
  });
});
