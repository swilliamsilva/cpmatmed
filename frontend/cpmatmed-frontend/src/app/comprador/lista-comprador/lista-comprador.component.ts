import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lista-comprador',
  templateUrl: './lista-comprador.component.html'
})
export class ListaCompradorComponent implements OnInit {
  compradores = [
    { id: 1, nome: 'João', email: 'joao@email.com' },
    { id: 2, nome: 'Maria', email: 'maria@email.com' }
  ];

  constructor() {}

  ngOnInit(): void {}
}
