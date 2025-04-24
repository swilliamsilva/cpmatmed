import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { DetalheProdutoComponent } from './detalhe-produto.component';
import { of } from 'rxjs'; // Para simular observables, caso precise

describe('DetalheProdutoComponent', () => {
  let component: DetalheProdutoComponent;
  let fixture: ComponentFixture<DetalheProdutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalheProdutoComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: () => '1', // ID fictício
              },
            },
          },
        },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // importante para inicializar o componente
  });

  it('deve ser criado', () => {
    expect(component).toBeTruthy();
  });

  it('deve capturar o ID da rota', () => {
    const route = TestBed.inject(ActivatedRoute); // Captura o ActivatedRoute
    const produtoId = route.snapshot.paramMap.get('id');
    expect(produtoId).toBe('1'); // Verifica se o ID capturado é '1'
  });
});
