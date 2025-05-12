    package com.cpmatmed.backend.mapper;

    import com.cpmatmed.backend.dto.FornecedorRequest; // Importação adicionada
    import java.util.List;
    import java.util.stream.Collectors;
    import com.cpmatmed.backend.dto.FornecedorDTO;
    import com.cpmatmed.backend.model.Fornecedor;
    
    public class FornecedorMapper {
    
        public static Fornecedor fromRequest(FornecedorRequest request) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(request.getNome());
            return fornecedor;
        }
    
    

    public static FornecedorDTO toDTO(Fornecedor fornecedor) {
        if (fornecedor == null) return null;
        return new FornecedorDTO(fornecedor.getId(), fornecedor.getNome());
    }

    public static List<FornecedorDTO> toDTOList(List<Fornecedor> fornecedores) {
        return fornecedores.stream()
                .map(FornecedorMapper::toDTO)
                .collect(Collectors.toList());
    }
}