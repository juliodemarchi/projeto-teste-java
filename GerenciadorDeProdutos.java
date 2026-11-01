import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeProdutos {
    private final List<Produto> catalogo;

    public GerenciadorDeProdutos(List<Produto> catalogo) {
        this.catalogo = catalogo;
    }

    // Busca segura utilizando Optional
    public Optional<Produto> buscarPorNome(String nome) {
        return catalogo.stream()
            .filter(p -> p.nome().equalsIgnoreCase(nome))
            .findFirst();
    }

    // Agrupamento por categoria usando Streams
    public Map<String, List<Produto>> agruparPorCategoria() {
        return catalogo.stream()
            .collect(Collectors.groupingBy(Produto::categoria));
    }

    // Cálculo do preço médio com Streams
    public double calcularPrecoMedio() {
        return catalogo.stream()
            .mapToDouble(Produto::preco)
            .average()
            .orElse(0.0);
    }
}
