// Classe: DetalheCompradorComponent.ts - Aplicação: cpmatmed
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompradorService } from '../comprador.service';

export interface Comprador {
  id?: number;
  nome: string;
  email: string;
}

@Component({
  selector: 'app-detalhe-comprador',
  templateUrl: './detalhe-comprador.component.html',
  styleUrls: ['./detalhe-comprador.component.scss']
})
export class DetalheCompradorComponent implements OnInit {
  compradorId!: number;
  comprador: Comprador | null = null;  // Usando a interface Comprador
  errorMessage: string = '';  // Para capturar possíveis erros

  constructor(
    private route: ActivatedRoute,
    private compradorService: CompradorService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      this.compradorId = id;
      this.compradorService.buscarPorId(id).subscribe(
        (data) => {
          this.comprador = data;
        },
        (error) => {
          this.errorMessage = 'Erro ao carregar os dados do comprador';
        }
      );
    }
  }
}
