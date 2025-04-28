// lista-comprador.component.ts - cpmatmed

import { Component, OnInit } from '@angular/core';
import { CompradorService } from '../comprador.service';
import { CompradorDTO } from '../dto/comprador.dto';

@Component({
  selector: 'app-lista-comprador',
  templateUrl: './lista-comprador.component.html'
})
export class ListaCompradorComponent implements OnInit {
  compradores: CompradorDTO[] = [];
  errorMessage: string = '';

  constructor(private compradorService: CompradorService) {}

  ngOnInit(): void {
    this.carregarCompradores();
  }

  carregarCompradores(): void {
    this.compradorService.getAll().subscribe(
      (data) => {
        if (Array.isArray(data)) {
          this.compradores = data;
        } else {
          this.compradores = [];
          console.error('Dados inválidos recebidos para compradores');
        }
      },
      (err) => {
        this.compradores = [];
        console.error('Erro ao carregar compradores', err);
        this.errorMessage = 'Erro ao carregar compradores';
      }
    );
  }
}
