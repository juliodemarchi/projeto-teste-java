import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ExemploListas {
    public static void main(String[] args) {
        List<String> tecnologias = new ArrayList<>();
        tecnologias.add("Java");
        tecnologias.add("Spring Boot");
        tecnologias.add("JavaScript");
        tecnologias.add("SQL");

        System.out.println("--- Lista Original ---");
        for (String tech : tecnologias) {
            System.out.println("- " + tech);
        }

        Collections.sort(tecnologias);

        System.out.println("\n--- Lista Ordenada ---");
        tecnologias.forEach(tech -> System.out.println("-> " + tech));
    }
}
