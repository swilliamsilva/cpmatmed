import { Component, OnInit } from '@angular/core';
import { FornecedorService, Fornecedor } from '../fornecedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro-fornecedor',
  templateUrl: './cadastro-fornecedor.component.html'
})
export class CadastroFornecedorComponent implements OnInit {
  fornecedor: Fornecedor = {
    nome: ''  // Inicialização com um valor vazio para nome
  };
  errorMessage: string = ''; // Mensagem de erro
  formulario: FormGroup; // Formulário reativo

  // Injectando o FormBuilder no construtor
  constructor(
    private fornecedorService: FornecedorService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder
  ) {
    // Inicializando o formulário com as validações
    this.formulario = this.fb.group({
      nome: ['', Validators.required],  // Campo 'nome' é obrigatório
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.fornecedorService.buscarPorId(id).subscribe(
        (fornecedor) => {
          this.fornecedor = fornecedor;
          this.formulario.patchValue(fornecedor); // Preenche o formulário com os dados do fornecedor
        },
        (error) => {
          this.errorMessage = 'Erro ao carregar dados do fornecedor'; // Caso ocorra algum erro ao buscar os dados
        }
      );
    }
  }

  salvar(): void {
    if (this.formulario.invalid) {
      this.errorMessage = 'Por favor, preencha todos os campos obrigatórios.';
      return; // Impede a execução do método se o formulário estiver inválido
    }

    if (this.fornecedor.id) {
      this.fornecedorService.atualizar(this.fornecedor.id, this.fornecedor).subscribe(
        () => {
          this.router.navigate(['/fornecedor/lista-fornecedor']);
        },
        (error) => {
          this.errorMessage = 'Erro ao atualizar fornecedor'; // Mensagem de erro se a atualização falhar
        }
      );
    } else {
      this.fornecedorService.criar(this.fornecedor).subscribe(
        () => {
          this.router.navigate(['/fornecedor/lista-fornecedor']);
        },
        (error) => {
          this.errorMessage = 'Erro ao criar fornecedor'; // Mensagem de erro se a criação falhar
        }
      );
    }
  }
}
