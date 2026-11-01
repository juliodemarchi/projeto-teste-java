import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExemploConcorrencia {
    public static void main(String[] args) {
        // Cria um pool com 2 threads simultâneas
        ExecutorService executor = Executors.newFixedThreadPool(2);

        System.out.println("Iniciando tarefas em background...");

        // Tarefa 1: Simula o envio de um relatório
        executor.submit(() -> {
            try {
                Thread.sleep(1000); // Simulando trabalho pesado
                System.out.println("[Thread 1] Relatório de vendas gerado com sucesso!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Tarefa 2: Simula a limpeza de cache
        executor.submit(() -> {
            try {
                Thread.sleep(500);
                System.out.println("[Thread 2] Cache do sistema limpo!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Encerra o executor após as tarefas terminarem
        executor.shutdown();
        System.out.println("Fluxo principal continua a executar sem bloqueio!");
    }
}
