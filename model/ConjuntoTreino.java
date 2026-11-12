package model;

public class ConjuntoTreino {
  static Treino[] conjuntoTreino = new Treino[4];


}

class Treino {
  private String nome;
  private int bias;
  private int[] x;
  private int[] y;

  public Treino(String nome, int bias, int[] x, int[] y) {
    this.nome = nome;
    this.bias = bias;
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x[0];
  }
  public int getY() {
    return y[0];
  }
}
