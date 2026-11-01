import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.inicializarBanco();

        // Buscando os dados salvos do arquivo SQLite
        List<Produto> produtosDoBanco = DatabaseManager.listarTodos();
        GerenciadorDeProdutos gerenciador = new GerenciadorDeProdutos(produtosDoBanco);

        System.out.println("\n--- Produtos recuperados do SQLite ---");
        produtosDoBanco.forEach(p -> System.out.println("- " + p.nome() + " (R$ " + p.preco() + ")"));

        System.out.println("\nPreço médio geral do catálogo: R$ " + gerenciador.calcularPrecoMedio());

        // Testando a busca por ID com Optional
        Long idPesquisa = 1L;
        DatabaseManager.buscarPorId(idPesquisa).ifPresentOrElse(
            p -> System.out.println("\nProduto encontrado por ID " + idPesquisa + ": " + p.nome() + " (R$ " + p.preco() + ")"),
            () -> System.out.println("\nProduto com ID " + idPesquisa + " não foi encontrado no banco.")
        );
    }
}
