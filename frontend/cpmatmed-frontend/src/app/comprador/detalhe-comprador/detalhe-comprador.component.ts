import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompradorService } from '../comprador.service';
import { CompradorDTO } from '../dto/comprador.dto';

@Component({
  selector: 'app-detalhe-comprador',
  templateUrl: './detalhe-comprador.component.html',
})
export class DetalheCompradorComponent implements OnInit {
  comprador: CompradorDTO = {
    id: 0,
    nome: '',
    email: '' 
  };

  constructor(
    private route: ActivatedRoute,
    private compradorService: CompradorService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.compradorService.getById(id).subscribe((data) => {
        this.comprador = data;
      });
    }
  }
}
