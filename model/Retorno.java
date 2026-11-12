package model;

public class Retorno {
  private int n1;
  private int n2;

  public Retorno(int n1, int n2) {
    this.n1 = n1;
    this.n2 = n2;
  }

  public int getN1() {
    return n1;
  }

  public int getN2() {
    return n2;
  }

  public String toString() {
    return "Retorno{" +
      "n1=" + n1 +
      ", n2=" + n2 +
      '}';
  }

  public String resultado() {
    if (n1 == 1 && n2 == -1) {
      return "1";
    } else if (n1 == -1 && n2 == 1) {
      return "0";
    } else if (n1 == 1 && n2 == 1) {
      return "Algo de errado não está certo!";
    } else if (n1 == -1 && n2 == -1) {
      return "Não é possível identificar!";
    } else {
      return "?"; // Caso não corresponda a nenhum dos padrões
    }
  }
}
