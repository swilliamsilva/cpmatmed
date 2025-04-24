import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ListaFornecedorComponent } from './lista-fornecedor.component';
import { FornecedorService } from '../fornecedor.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';

describe('ListaFornecedorComponent', () => {
  let component: ListaFornecedorComponent;
  let fixture: ComponentFixture<ListaFornecedorComponent>;
  let service: FornecedorService;
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaFornecedorComponent],
      imports: [HttpClientTestingModule],
      providers: [FornecedorService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaFornecedorComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(FornecedorService);

    spyOn(service, 'buscarTodos').and.returnValue(of([])); // Mock inicial da resposta para buscarTodos
    fixture.detectChanges(); // dispara ngOnInit
  });

  it('deve excluir fornecedor e recarregar lista', fakeAsync(() => {
    const confirmSpy = spyOn(window, 'confirm').and.returnValue(true); // Simula confirmação
    const excluirSpy = spyOn(service, 'excluir').and.returnValue(of(null)); // Simula a exclusão bem-sucedida
    const buscarTodosSpy = spyOn(service, 'buscarTodos'); // Spy no método buscarTodos para ver quantas vezes é chamado

    component.excluir(1); // Chama o método de exclusão
    tick(); // Simula o tempo de execução dos observáveis

    expect(excluirSpy).toHaveBeenCalledWith(1); // Verifica se o método excluir foi chamado com o ID correto
    expect(buscarTodosSpy).toHaveBeenCalledTimes(2); // Verifica se buscarTodos foi chamado duas vezes (inicialmente e após a exclusão)
  }));

  it('deve não excluir fornecedor se confirmação for cancelada', fakeAsync(() => {
    const confirmSpy = spyOn(window, 'confirm').and.returnValue(false); // Simula cancelamento da confirmação
    const excluirSpy = spyOn(service, 'excluir'); // Spy para o método de exclusão

    component.excluir(1); // Chama o método de exclusão
    tick(); // Simula o tempo de execução

    expect(excluirSpy).not.toHaveBeenCalled(); // Verifica se o método excluir NÃO foi chamado
  }));

  it('deve lidar com falha na exclusão de fornecedor', fakeAsync(() => {
    const confirmSpy = spyOn(window, 'confirm').and.returnValue(true); // Simula confirmação
    const excluirSpy = spyOn(service, 'excluir').and.returnValue(throwError('Erro na exclusão')); // Simula erro na exclusão
    const buscarTodosSpy = spyOn(service, 'buscarTodos'); // Spy no método buscarTodos

    component.excluir(1); // Chama o método de exclusão
    tick(); // Simula o tempo de execução

    expect(excluirSpy).toHaveBeenCalledWith(1); // Verifica se o método excluir foi chamado
    expect(buscarTodosSpy).toHaveBeenCalledTimes(1); // Verifica se buscarTodos foi chamado apenas uma vez, já que a exclusão falhou
  }));
});
