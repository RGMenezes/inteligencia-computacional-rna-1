import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import model.IA;
import model.Pesos;
import model.Vetor;

public class Main {
  public static void main(String[] args) {
    System.out.println("Iniciando o programa...");
    String caminhoArquivoPesos = "pesos.dat"; // Caminho do arquivo de pesos
    // String caminhoArquivoPesos = "pesosT2.dat"; // Caminho do arquivo de pesos

    Pesos pesos = null;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminhoArquivoPesos))) {
      // Tem que ler na mesma ordem em que foram salvos
      Vetor w1Carregado = (Vetor) in.readObject();
      Vetor w2Carregado = (Vetor) in.readObject();

      pesos = new Pesos(w1Carregado, w2Carregado);

      System.out.println("\nPesos carregados: " + pesos.getW1().toString() + " e " + pesos.getW2().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Verifica se os pesos foram carregados corretamente
    if (pesos == null) {
      System.err.println("Não foi possível carregar os pesos.");
      return;
    }

    int bias = 1;
    IA ia = new IA(pesos);

    // Menu de opções para o usuário
    int opcao = 0;
    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("\nEscolha uma opção:\n" +
        "1. Teste automático\n" +
        "2. Testa manual\n" +
        "0. Sair"
      );

      try {
        opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
          case 1:
            // Teste automático
            Vetor teste1 = new Vetor(new double[] { bias,
              1.0,  1.0,  1.0,
              1.0, -1.0,  1.0,
             -1.0,  1.0, -1.0
            });
            System.out.println("\nTeste com vetor: \n" + teste1.toStringMatriz(3, 3, true));
            System.out.println("Resultado do teste: " + ia.identificar(teste1).resultado());

            Vetor teste2 = new Vetor(new double[] { bias,
              -1.0, 1.0, -1.0,
              -1.0, 1.0, -1.0,
              -1.0, 1.0, -1.0
            });
            System.out.println("\nTeste com vetor: \n" + teste2.toStringMatriz(3, 3, true));
            System.out.println("Resultado do teste: " + ia.identificar(teste2).resultado());
            break;

          case 2:
            // Teste manual
            System.out.println("Digite os valores do vetor (9 valores separados por espaço):");
            String[] valoresStr = scanner.nextLine().trim().split("\\s+");
            if (valoresStr.length != 9) {
              System.err.println("Você deve digitar exatamente 9 valores.");
              break;
            }

            double[] valores = new double[10];
            valores[0] = bias; // Adiciona o bias no início
            for (int i = 0; i < 9; i++) {
              valores[i + 1] = Double.parseDouble(valoresStr[i]);
            }

            Vetor testeManual = new Vetor(valores);
            System.out.println("\nTeste com vetor: \n" + testeManual.toStringMatriz(3, 3, true));
            System.out.println("Resultado do teste: " + ia.identificar(testeManual).resultado());
            break;

          case 0:
            System.out.println("Saindo...");
            break;

          default:
            System.err.println("Opção inválida. Tente novamente.");
        }

      } catch (NumberFormatException e) {
        System.err.println("Opção inválida. Tente novamente.");
        continue;
      }
    } while (opcao != 0);

    scanner.close();
  }
}
