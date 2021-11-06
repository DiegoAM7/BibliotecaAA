package com.mao.bibliotecaaa.models;

import java.io.Serializable;

public class Editorial implements Serializable {
  private int id;
  private String name;
  private String nationality;

  public Editorial() {}

  public Editorial(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  public Editorial(int id, String name, String nationality) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
}
