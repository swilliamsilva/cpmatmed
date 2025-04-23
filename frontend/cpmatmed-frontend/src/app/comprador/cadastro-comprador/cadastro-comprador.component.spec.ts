import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroCompradorComponent } from './cadastro-comprador.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';

describe('CadastroCompradorComponent', () => {
  let component: CadastroCompradorComponent;
  let fixture: ComponentFixture<CadastroCompradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastroCompradorComponent],
      imports: [ReactiveFormsModule, FormsModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroCompradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve inicializar o formulário com campos vazios e inválidos', () => {
    const form = component.compradorForm;
    expect(form).toBeDefined();
    expect(form.get('nome')?.value).toBe('');
    expect(form.get('email')?.value).toBe('');
    expect(form.invalid).toBeTrue();
  });

  it('deve validar o formulário com dados válidos', () => {
    component.compradorForm.setValue({ nome: 'Maria', email: 'maria@teste.com' });
    expect(component.compradorForm.valid).toBeTrue();
  });

  it('deve manter o botão desabilitado se o formulário for inválido', () => {
    component.compradorForm.setValue({ nome: '', email: '' });
    fixture.detectChanges();
    const button = fixture.debugElement.query(By.css('button[type="submit"]')).nativeElement;
    expect(button.disabled).toBeTrue();
  });

  it('deve chamar onSubmit() ao submeter o formulário', () => {
    const submitSpy = spyOn(component, 'onSubmit').and.callThrough();

    component.compradorForm.setValue({ nome: 'João', email: 'joao@teste.com' });
    fixture.detectChanges();

    const formElement = fixture.debugElement.query(By.css('form'));
    formElement.triggerEventHandler('ngSubmit', null);

    expect(submitSpy).toHaveBeenCalled();
  });

  it('deve logar no console ao submeter o formulário', () => {
    const consoleSpy = spyOn(console, 'log');
    component.compradorForm.setValue({ nome: 'João', email: 'joao@teste.com' });
    component.onSubmit();
    expect(consoleSpy).toHaveBeenCalledWith({ nome: 'João', email: 'joao@teste.com' });
  });
});
