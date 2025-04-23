import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ListaFornecedorComponent } from './lista-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

describe('ListaFornecedorComponent', () => {
  let component: ListaFornecedorComponent;
  let fixture: ComponentFixture<ListaFornecedorComponent>;
  let service: FornecedorService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaFornecedorComponent],
      imports: [HttpClientTestingModule], // ✅ Import necessário
      providers: [FornecedorService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaFornecedorComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(FornecedorService);

    // Mock inicial da resposta para buscarTodos
    spyOn(service, 'buscarTodos').and.returnValue(of([]));

    fixture.detectChanges(); // dispara ngOnInit
  });

  it('deve excluir fornecedor e recarregar lista', fakeAsync(() => {
    spyOn(window, 'confirm').and.returnValue(true);
    const buscarTodosSpy = service.buscarTodos as jasmine.Spy;
    const excluirSpy = spyOn(service, 'excluir').and.returnValue(of(null));

    component.excluir(1);
    tick(); // simula tempo para conclusão dos observables

    expect(excluirSpy).toHaveBeenCalledWith(1);
    expect(buscarTodosSpy).toHaveBeenCalledTimes(2); // ngOnInit + pós-exclusão
  }));
});
