import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final String url = "jdbc:sqlite:loja.db";

public ProdutoDAO() {
    String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 "nome TEXT NOT NULL, " +
                 "preco REAL NOT NULL)";
        try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco: " + e.getMessage());
        }
    }
    public void inserir(String nome, double preco) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setDouble(2, preco);
            pstmt.executeUpdate();
            System.out.println("✅ Produto '" + nome + "' inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<String> listarTodos() {
        String sql = "SELECT id, nome, preco FROM produtos";
        List<String> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | R$ " + rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(int id, String novoNome, double novoPreco) {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, novoNome);
            pstmt.setDouble(2, novoPreco);
            pstmt.setInt(3, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("🔄 Produto com ID " + id + " atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("🗑️ Produto com ID " + id + " removido com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
