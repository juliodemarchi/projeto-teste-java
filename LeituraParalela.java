import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LeituraParalela {
    private static final String URL = "jdbc:sqlite:loja.db";

    public static List<Produto> buscarCategoriasEmParalelo(List<String> categorias) 
            throws InterruptedException, ExecutionException {
        
        ExecutorService executor = Executors.newFixedThreadPool(categorias.size());
        List<CompletableFuture<List<Produto>>> futures = new ArrayList<>();

        for (String categoria : categorias) {
            CompletableFuture<List<Produto>> future = CompletableFuture.supplyAsync(() -> {
                List<Produto> produtosDaCategoria = new ArrayList<>();
                String sql = "SELECT id, nome, preco, categoria, estoque FROM produtos WHERE categoria = ?";

                try (Connection conn = DriverManager.getConnection(URL);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    
                    pstmt.setString(1, categoria);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            produtosDaCategoria.add(new Produto(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getDouble("preco"),
                                rs.getString("categoria"),
                                rs.getInt("estoque")
                            ));
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Erro na thread da categoria " + categoria + ": " + e.getMessage());
                }

                return produtosDaCategoria;
            }, executor);

            futures.add(future);
        }

        List<Produto> resultadoFinal = new ArrayList<>();
        for (CompletableFuture<List<Produto>> f : futures) {
            resultadoFinal.addAll(f.join());
        }

        executor.shutdown();
        return resultadoFinal;
    }
}
