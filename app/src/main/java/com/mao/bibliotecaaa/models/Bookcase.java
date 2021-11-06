package com.mao.bibliotecaaa.models;

import java.io.Serializable;

public class Bookcase implements Serializable {
  private int id;
  private String letter;
  private int number;
  private String color;

  public Bookcase() {}

  public Bookcase(String letter, int number, String color) {
    this.letter = letter;
    this.number = number;
    this.color = color;
  }

  public Bookcase(int id, String letter, int number, String color) {
    this.id = id;
    this.letter = letter;
    this.number = number;
    this.color = color;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLetter() {
    return letter;
  }

  public void setLetter(String letter) {
    this.letter = letter;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
