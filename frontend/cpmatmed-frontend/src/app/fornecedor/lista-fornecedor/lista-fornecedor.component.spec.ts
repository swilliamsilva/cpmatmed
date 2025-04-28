import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaFornecedorComponent } from './lista-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { of, throwError } from 'rxjs';
import { FornecedorDTO } from '../dto/fornecedor.dto';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ListaFornecedorComponent', () => {
  let component: ListaFornecedorComponent;
  let fixture: ComponentFixture<ListaFornecedorComponent>;
  let fornecedorService: FornecedorService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaFornecedorComponent],
      imports: [HttpClientTestingModule],
      providers: [FornecedorService],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaFornecedorComponent);
    component = fixture.componentInstance;
    fornecedorService = TestBed.inject(FornecedorService);
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar fornecedores com sucesso', () => {
    const fornecedoresMock: FornecedorDTO[] = [
      {
        id: 1,
        nome: 'Fornecedor A',
        cnpj: '11.111.111/0001-11'
      },
      {
        id: 2,
        nome: 'Fornecedor B',
        cnpj: '22.222.222/0001-22'
      },
    ];

    spyOn(fornecedorService, 'listar').and.returnValue(of(fornecedoresMock));
    component.carregarFornecedores();

    expect(component.fornecedores.length).toBe(2);
    expect(component.fornecedores[0].cnpj).toBe('11.111.111/0001-11'); // Teste adicional
  });

  // Restante do arquivo permanece igual...
});
