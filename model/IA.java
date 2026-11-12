package model;

public class IA {
  private Pesos pesos;

  public IA( Pesos pesos) {
    this.pesos = pesos;
  }

  public Retorno identificar(Vetor x) {
    // Passo 2 treinar o próximo x
    double v1 = x.mul(pesos.getW1()).som();
    double v2 = x.mul(pesos.getW2()).som();

    // Passo 3 calcular o y
    int y1 = v1 > 0 ? 1 : -1;
    int y2 = v2 > 0 ? 1 : -1;

    return new Retorno(y1, y2);
  }

  public Pesos treinar(Treino[] treino, double erroLim, int treinoLimite) {
    // Variáveis de controle
    int contador = 0;
    int index;

    Vetor w1 = pesos.getW1();
    Vetor w2 = pesos.getW2();

    double[] erro = new double[treino.length];

    boolean condicaoParada = false;

    do {
      index = contador % treino.length;
      Vetor x = treino[index].getX();
      Retorno y = treino[index].getY();

      // Incrementa o contador de treino
      contador++;

      // Passo 2 treinar o próximo x
      double v1 = x.mul(w1).som();
      double v2 = x.mul(w2).som();

      // Passo 3 calcular o y
      int y1 = v1 > 0 ? 1 : -1;
      int y2 = v2 > 0 ? 1 : -1;

      // Passo 4 calcular o erro
      int e1 = y.getN1() - y1;
      int e2 = y.getN2() - y2;

      erro[index] = (e1 * e1 + e2 * e2) / 2.0;

      // Passo 5 atualizar os pesos
      w1 = w1.som(x.mul(0.5 * e1));
      w2 = w2.som(x.mul(0.5 * e2));

      System.out.println(
        "\nTreino: " + treino[index].getNome() +
        " | Erro: " + erro[index] +
        " | Pesos: " + w1.toString() + " | " + w2.toString()
      );

      // Passo 6 verificar condição de parada
      double erroMedio = 0;
      for (double e : erro) {
          erroMedio += e;
      }
      erroMedio /= erro.length;
      boolean erroAbaixoLimite = erroMedio <= erroLim;

      boolean finalEpoca = index == treino.length - 1; // Verifica se está no final da época
      boolean treinoMaximo = contador >= treinoLimite; // Verifica se atingiu o número máximo de treinos

      if (treinoMaximo) System.out.println("\n|| Treino máximo atingido ||");

      condicaoParada = finalEpoca && (erroAbaixoLimite || treinoMaximo);

    } while (condicaoParada == false);

    return new Pesos(w1, w2);
  }

  public void setPesos(Pesos pesos) {
    this.pesos = pesos;
  }
}
