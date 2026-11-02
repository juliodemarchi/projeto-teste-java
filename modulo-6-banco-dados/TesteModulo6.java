package modulo6;

import java.util.List;

public class TesteModulo6 {
    public static void main(String[] args) {
        System.out.println("=== TESTE PRÁTICO: TEMA 6 (JDBC & DAO) ===");
        
        ProdutoDAO dao = new ProdutoDAO();

        // Inserindo dados de teste
        dao.inserir("Teclado Mecânico", 250.00);
        dao.inserir("Mouse Gamer", 120.50);

        // Consultando dados salvos
        System.out.println("\n--- Lista de Produtos no Banco ---");
        List<String> produtos = dao.listarTodos();
        for (String p : produtos) {
            System.out.println(p);
        }
    }
}

