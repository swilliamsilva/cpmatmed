export class ProdutoDTO {
  constructor(
    public id: number,
    public nome: string,
    public descricao: string,
    public preco: number
  ) {}
}