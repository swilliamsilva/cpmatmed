import {
  ComponentFixture,
  TestBed,
  fakeAsync,
  tick,
} from '@angular/core/testing';
import { CadastroCompradorComponent } from './cadastro-comprador.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CompradorService } from '../comprador.service';
import { of, throwError } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { CompradorDTO } from '../dto/comprador.dto';
import { Router } from '@angular/router';

class MockCompradorService {
  criar(comprador: CompradorDTO) {
    return of({
      id: 1,
      nome: comprador.nome,
      email: comprador.email,
    });
  }
}

describe('CadastroCompradorComponent', () => {
  let component: CadastroCompradorComponent;
  let fixture: ComponentFixture<CadastroCompradorComponent>;
  let compradorService: CompradorService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastroCompradorComponent],
      imports: [
        ReactiveFormsModule,
        FormsModule,
        RouterTestingModule.withRoutes([]),
      ],
      providers: [
        { provide: CompradorService, useClass: MockCompradorService },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroCompradorComponent);
    component = fixture.componentInstance;
    compradorService = TestBed.inject(CompradorService);
    router = TestBed.inject(Router);

    // Configuração do spy genérico para o serviço
    spyOn(compradorService, 'criar').and.callThrough();
    spyOn(router, 'navigate').and.stub();

    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve exibir erro se falhar ao criar comprador', fakeAsync(() => {
    const erroEsperado = 'Erro ao criar comprador';
    const errorResponse = new Error(erroEsperado);

    // Sobrescreve o spy para retornar erro
    (compradorService.criar as jasmine.Spy).and.returnValue(
      throwError(errorResponse)
    );
    const consoleSpy = spyOn(console, 'error');

    component.compradorForm.setValue({
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    });

    component.onSubmit();
    tick();

    expect(consoleSpy).toHaveBeenCalledWith(
      'Erro ao criar comprador:',
      errorResponse
    );

    expect(component.errorMessage).toBe(
      `Erro ao criar comprador: ${erroEsperado}`
    );
  }));

  it('deve chamar o serviço quando o formulário for válido', fakeAsync(() => {
    const compradorMock: CompradorDTO = {
      id: 1,
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    };

    (compradorService.criar as jasmine.Spy).and.returnValue(of(compradorMock));

    component.compradorForm.setValue({
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    });

    component.onSubmit();
    tick();

    expect(compradorService.criar).toHaveBeenCalledWith({
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    });

    expect(router.navigate).toHaveBeenCalledWith(['/comprador/lista-comprador']);
  }));

  it('deve exibir mensagem de erro quando formulário for inválido', () => {
    component.compradorForm.setValue({
      nome: '',
      email: 'email-invalido',
    });

    component.onSubmit();

    expect(component.errorMessage).toBe(
      'Por favor, corrija os erros no formulário'
    );
    expect(compradorService.criar).not.toHaveBeenCalled();
  });
});
