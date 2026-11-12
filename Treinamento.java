import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.*;

public class Treinamento {
  public static void main(String[] args) {
    // Bias
    int bias = 1;
    double erroLim = 0.001;

    // Cunjunto de treino
    Treino[] treino = new Treino[4];
    treino[0] = new Treino(
      "0",
      new double[] { bias,
        1.0,  1.0, 1.0,
        1.0, -1.0, 1.0,
        1.0,  1.0, 1.0
      },
      new Retorno(-1, 1)
    );
    treino[1] = new Treino(
      "1",
      new double[] { bias,
        -1.0, 1.0, -1.0,
         1.0, 1.0, -1.0,
        -1.0, 1.0, -1.0
      },
      new Retorno(1, -1)
    );
    treino[2] = new Treino(
      "0",
      new double[] { bias,
        -1.0,  1.0, -1.0,
         1.0, -1.0,  1.0,
         1.0,  1.0,  1.0
      },
      new Retorno(-1, 1)
    );
    treino[3] = new Treino(
      "1",
      new double[] { bias,
        -1.0, 1.0, -1.0,
        -1.0, 1.0, -1.0,
         1.0, 1.0,  1.0
      },
      new Retorno(1, -1)
    );

    // Sinapses (Passo 1)
    Vetor w1 = new Vetor(new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0});
    Vetor w2 = new Vetor(new double[] { -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0 });
    Pesos pesos = new Pesos(w1, w2);

    // Treinar a IA
    IA ia = new IA(pesos);
    pesos = ia.treinar(treino, erroLim, 1000);

    // Mostrar resultado final
    System.out.println("\n|| Treinamento finalizado ||");
    System.out.println("Pesos finais:\n" + pesos.getW1().toString() + "\n" + pesos.getW2().toString());

    // Salvar os objetos w1 e w2 em um arquivo
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pesos.dat"))) {
      out.writeObject(pesos.getW1());
      out.writeObject(pesos.getW2());
      System.out.println("Objetos w1 e w2 salvos!");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
