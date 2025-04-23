import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { DetalheProdutoComponent } from './detalhe-produto.component';

describe('DetalheProdutoComponent', () => {
  let component: DetalheProdutoComponent;
  let fixture: ComponentFixture<DetalheProdutoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // importante para inicializar o componente
  });

  it('deve ser criado', () => {
    expect(component).toBeTruthy();
  });
});
