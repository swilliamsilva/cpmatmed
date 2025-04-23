import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetalheCompradorComponent } from './detalhe-comprador.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

describe('DetalheCompradorComponent', () => {
  let component: DetalheCompradorComponent;
  let fixture: ComponentFixture<DetalheCompradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalheCompradorComponent],
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => {
                  if (key === 'id') return '1';  // Retorna o ID correto
                  return null;
                }
              }
            }
          }
        }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();  // Garante que o componente seja renderizado
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve atribuir o valor correto ao compradorId', () => {
    expect(component.compradorId).toBe(1);  // Verifica se o ID foi corretamente atribuído
  });
});
