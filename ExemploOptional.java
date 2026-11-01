import java.util.Optional;

public class ExemploOptional {
    public static void main(String[] args) {
        // Simulando uma busca que retorna null
        ProdutoTemp produtoPesquisado = buscarProdutoNoBanco("Mouse Gamer");

        // 1. Optional.ofNullable(): Embrulha o objeto com segurança contra null
        Optional<ProdutoTemp> optProduto = Optional.ofNullable(produtoPesquisado);

        // 2. ifPresent(): Executa apenas se o produto existir
        optProduto.ifPresent(p -> System.out.println("Sucesso! Encontrado: " + p.nome()));

        // 3. orElseGet(): Se for nulo, define um produto padrão
        ProdutoTemp produtoFinal = optProduto.orElseGet(() -> 
            new ProdutoTemp("Produto Genérico", 0.0, "Indefinida")
        );
        System.out.println("Produto final utilizado: " + produtoFinal.nome());

        // 4. .map(): Transforma e extrai dados com segurança
        String categoriaFormatada = optProduto
            .map(ProdutoTemp::categoria)
            .map(String::toUpperCase)
            .orElse("CATEGORIA NÃO INFORMADA");

        System.out.println("Categoria: " + categoriaFormatada);
    }

    private static ProdutoTemp buscarProdutoNoBanco(String nome) {
        return null; // Simulando que não encontrou no banco
    }
}

record ProdutoTemp(String nome, double preco, String categoria) {}
