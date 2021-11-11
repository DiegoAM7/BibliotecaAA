package com.mao.bibliotecaaa.models;

import java.io.Serializable;

public class Author implements Serializable {
  private int id;
  private String names;
  private String lastnames;
  private String nationality;

  public Author() {}

  public Author(String names, String lastnames, String nationality) {
    this.names = names;
    this.lastnames = lastnames;
    this.nationality = nationality;
  }

  public Author(int id, String names, String lastnames, String nationality) {
    this.id = id;
    this.names = names;
    this.lastnames = lastnames;
    this.nationality = nationality;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getLastnames() {
    return lastnames;
  }

  public void setLastnames(String lastnames) {
    this.lastnames = lastnames;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public String toString() {
    return names + " " + lastnames;
  }
}
