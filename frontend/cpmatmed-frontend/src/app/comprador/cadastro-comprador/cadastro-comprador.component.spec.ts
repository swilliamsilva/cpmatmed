import { ComponentFixture, TestBed } from '@angular/core/testing';
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
    spyOn(router, 'navigate').and.stub();
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve exibir erro se falhar ao criar comprador', () => {
    const erroEsperado = 'Erro ao criar comprador';
    const errorResponse = new Error(erroEsperado);

    spyOn(compradorService, 'criar').and.returnValue(
      throwError(errorResponse) // ← Versão corrigida
    );
    const consoleSpy = spyOn(console, 'error');

    component.compradorForm.setValue({
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    });
    component.salvar();

    expect(consoleSpy).toHaveBeenCalledWith(
      jasmine.stringMatching(new RegExp(erroEsperado)),
      jasmine.any(Error) // Agora corresponde ao objeto Error
    );
  });

  it('deve chamar o serviço quando o formulário for válido', () => {
    const compradorMock: CompradorDTO = {
      id: 1,
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    };
    spyOn(compradorService, 'criar').and.returnValue(of(compradorMock));

    component.compradorForm.setValue({
      nome: 'Comprador A',
      email: 'comprador@teste.com',
    });
    component.salvar();

    expect(compradorService.criar).toHaveBeenCalledWith(
      jasmine.objectContaining({
        nome: 'Comprador A',
        email: 'comprador@teste.com',
      })
    );
  });
});
