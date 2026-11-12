package model;

import java.io.Serializable;

public class Vetor implements Serializable {
  private double[] x;

  public Vetor(double[] x) {
    this.x = x;
  }

  public double[] getX() {
    return x;
  }

  private boolean tamanhoIgual(Vetor vetor) {
    return this.x.length == vetor.x.length;
  }

  public Vetor mul(Vetor vetor) {
    if (!tamanhoIgual(vetor)) {
      throw new IllegalArgumentException("Os vetores têm tamanhos diferentes");
    }

    double[] resultado = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      resultado[i] = this.x[i] * vetor.x[i];
    }
    return new Vetor(resultado);
  }

  public Vetor mul(double escalar) {
    double[] resultado = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      resultado[i] = this.x[i] * escalar;
    }
    return new Vetor(resultado);
  }

  public Vetor som(Vetor vetor) {
    if (!tamanhoIgual(vetor)) {
      throw new IllegalArgumentException("Os vetores têm tamanhos diferentes");
    }

    double[] resultado = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      resultado[i] = this.x[i] + vetor.x[i];
    }
    return new Vetor(resultado);
  }

  public double som() {
    if (x.length == 0) {
      throw new IllegalArgumentException("O vetor está vazio");
    }

    double resultado = 0;
    for (int i = 0; i < x.length; i++) {
      resultado += this.x[i];
    }
    return resultado;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < x.length; i++) {
      sb.append(x[i]);
      if (i < x.length - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  // imprimir matriz
  public String toStringMatriz(int linhas, int colunas, boolean semBias) {
    StringBuilder sb = new StringBuilder();
    int index = semBias ? 1 : 0; // Começa do índice 1 se semBias for true, caso contrário começa do índice 0
    for (int i = 0; i < linhas; i++) {
      for (int j = 0; j < colunas; j++) {
        if (index < x.length) {
          sb.append(String.format("%6.2f", x[index]));
          if (j < colunas - 1) {
            sb.append(" ");
          }
          index++;
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
