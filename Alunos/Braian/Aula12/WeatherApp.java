package com.example.weather;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        System.out.println("=== Consulta do Clima ===");

        try (Scanner scanner = new Scanner(System.in)) {
            WeatherClient client = new WeatherClient();

            boolean executando = true;

            while (executando) {
                System.out.println("\nMenu de opções:");
                System.out.println("1 - Consultar clima completo");
                System.out.println("2 - Consultar apenas temperatura");
                System.out.println("3 - Consultar apenas umidade");
                System.out.println("4 - Consultar condição do tempo");
                System.out.println("5 - Sair");

                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                        System.out.print("Digite o nome da cidade: ");
                        String cidade = scanner.nextLine().trim();

                        if (cidade.isEmpty() || cidade.isBlank()) {
                            System.out.println("Nome da cidade não pode ser vazio.");
                            break;
                        }

                        try {
                            WeatherData data = client.getWeather(cidade);

                            switch (opcao) {
                                case "1":
                                    System.out.println(data);
                                    break;
                                case "2":
                                    System.out.printf("Temperatura atual em %s: %.1f°C\n", cidade, data.getTemp());
                                    System.out.printf("Máxima: %.1f°C | 🔻 Mínima: %.1f°C\n", data.getTempMax(), data.getTempMin());
                                    break;
                                case "3":
                                    System.out.printf("Umidade do ar em %s: %.1f%%\n", cidade, data.getHumidity());
                                    break;
                                case "4":
                                    System.out.printf("Condição do tempo em %s: %s\n", cidade, data.getConditions());
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada inválida: " + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Erro de conexão ou requisição: " + e.getMessage());
                        }
                        break;

                    case "5":
                        System.out.println("Encerrando o programa.");
                        executando = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Escolha um número de 1 a 5.");
                        break;
                }
            }
        }
    }
}
