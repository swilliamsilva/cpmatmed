package com.cpmatmed.backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoPostgres {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/dbpmatmed"; // Substitua conforme necessário
        String usuario = "postgres"; // Substitua conforme necessário
        String senha = "SAMUEL"; // Substitua conforme necessário

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("✅ Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Falha na conexão:");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Código de erro: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
