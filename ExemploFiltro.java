import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExemploFiltro {
    public static void main(String[] args) {
        List<String> tecnologias = Arrays.asList("Java", "Spring Boot", "JavaScript", "SQL", "JUnit");

        // Filtrar apenas as tecnologias que começam com a letra "J"
        List<String> filtradas = tecnologias.stream()
            .filter(tech -> tech.startsWith("J"))
            .collect(Collectors.toList());

        System.out.println("--- Tecnologias que começam com J ---");
        filtradas.forEach(System.out::println);
    }
}
