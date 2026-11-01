import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExemploAgrupamentoAvancado {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook Gamer", 4500.00, "Eletrônicos"),
            new Produto("Mouse Sem Fio", 120.50, "Eletrônicos"),
            new Produto("Cadeira Ergonômica", 1200.00, "Móveis"),
            new Produto("Teclado Mecânico", 350.00, "Eletrônicos"),
            new Produto("Mesa de Escritório", 800.00, "Móveis")
        );

        // 1. Somar o preço total dos produtos por categoria
        Map<String, Double> totalPorCategoria = produtos.stream()
            .collect(Collectors.groupingBy(
                Produto::getCategoria, 
                Collectors.summingDouble(Produto::getPreco)
            ));

        System.out.println("=== 1. Valor Total por Categoria ===");
        totalPorCategoria.forEach((categoria, total) -> 
            System.out.println(categoria + ": R$ " + String.format("%.2f", total))
        );

        // 2. Calcular a média de preço por categoria
        Map<String, Double> mediaPorCategoria = produtos.stream()
            .collect(Collectors.groupingBy(
                Produto::getCategoria, 
                Collectors.averagingDouble(Produto::getPreco)
            ));

        System.out.println("\n=== 2. Média de Preço por Categoria ===");
        mediaPorCategoria.forEach((categoria, media) -> 
            System.out.println(categoria + ": R$ " + String.format("%.2f", media))
        );
    }
}
