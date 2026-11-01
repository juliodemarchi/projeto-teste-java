import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseManager.inicializarBanco();

        List<String> categoriasParaBuscar = Arrays.asList("Eletrônicos", "Móveis");

        System.out.println("Iniciando buscas paralelas...");
        long inicio = System.currentTimeMillis();

        List<Produto> produtosEncontrados = LeituraParalela.buscarCategoriasEmParalelo(categoriasParaBuscar);

        long fim = System.currentTimeMillis();

        System.out.println("\n--- Produtos encontrados em paralelo ---");
        produtosEncontrados.forEach(p -> System.out.println("- " + p.nome() + " (" + p.categoria() + ")"));
        
        System.out.println("\nTempo total de execução paralela: " + (fim - inicio) + " ms");
    }
}
