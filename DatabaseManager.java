import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:loja.db";

    public static void inicializarBanco() {
        String sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                preco REAL NOT NULL,
                categoria TEXT NOT NULL,
                estoque INTEGER NOT NULL
            );
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco: " + e.getMessage());
        }
    }

    public static void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, categoria, estoque) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.nome());
            pstmt.setDouble(2, produto.preco());
            pstmt.setString(3, produto.categoria());
            pstmt.setInt(4, produto.estoque());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public static List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco, categoria, estoque FROM produtos";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getInt("estoque")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public static Optional<Produto> buscarPorId(Long idBuscado) {
        String sql = "SELECT id, nome, preco, categoria, estoque FROM produtos WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, idBuscado);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("categoria"),
                        rs.getInt("estoque")
                    );
                    return Optional.of(produto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        }

        return Optional.empty();
    }
}
