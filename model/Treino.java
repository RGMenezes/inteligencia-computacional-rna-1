package model;

public class Treino {
  private static int contadorId = 0;
  private String nome;
  private int id;
  private Vetor x;
  private Retorno y;

  public Treino(String nome, double[] x, Retorno y) {
    this.id = ++contadorId;
    this.nome = nome;
    this.x = new Vetor(x);
    this.y = y;
  }

  public Vetor getX() {
    return x;
  }

  public Retorno getY() {
    return y;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
}
