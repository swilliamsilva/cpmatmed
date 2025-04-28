// Adicione propriedades ausentes se necessário
export class PedidoDTO {
  constructor(
    public id: number,
    public descricao: string,
    public dataCriacao: string,
    public compradorId?: number, // Adicione se existir
    public produtos?: Array<{ produtoId: number; quantidade: number }> // Adicione se existir
  ) {}
}
