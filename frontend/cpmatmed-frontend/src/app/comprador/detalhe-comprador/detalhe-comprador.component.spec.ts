import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { DetalheCompradorComponent } from './detalhe-comprador.component';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { CompradorService } from '../comprador.service';
import { CompradorDTO } from '../dto/comprador.dto';

describe('DetalheCompradorComponent', () => {
  let component: DetalheCompradorComponent;
  let fixture: ComponentFixture<DetalheCompradorComponent>;
  let compradorService: CompradorService;

  const mockActivatedRoute = {
    snapshot: {
      paramMap: {
        get: (key: string) => '1'
      }
    }
  };

  const mockCompradorService = {
    getById: (id: number) =>
      of({
        id,
        nome: 'Mock Comprador Detalhe',
        email: 'mock@email.com'
      } as CompradorDTO)
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalheCompradorComponent],
      providers: [
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: CompradorService, useValue: mockCompradorService }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheCompradorComponent);
    component = fixture.componentInstance;
    compradorService = TestBed.inject(CompradorService);
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve carregar detalhes do comprador no ngOnInit', fakeAsync(() => {
    component.ngOnInit();
    tick();

    expect(component.comprador).toBeTruthy();
    expect(component.comprador.nome).toBe('Mock Comprador Detalhe');
    expect(component.comprador.email).toBe('mock@email.com');
  }));

  it('deve atribuir o ID correto ao comprador a partir da rota', () => {
    component.ngOnInit();
    expect(component.comprador.id).toBe(1);
  });
});
