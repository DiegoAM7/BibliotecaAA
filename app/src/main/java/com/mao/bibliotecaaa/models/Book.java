package com.mao.bibliotecaaa.models;

import java.io.Serializable;

public class Book implements Serializable {
  private int id;
  private String title;
  private String description;
  private String publicationDate;
  private int replicas;
  private int pages;
  private Author author;
  private Bookcase bookcase;
  private Editorial editorial;

  public Book() {}

  public Book(
      String title,
      String description,
      String publicationDate,
      int replicas,
      int pages,
      Author author,
      Bookcase bookcase,
      Editorial editorial) {
    this.title = title;
    this.description = description;
    this.publicationDate = publicationDate;
    this.replicas = replicas;
    this.pages = pages;
    this.author = author;
    this.bookcase = bookcase;
    this.editorial = editorial;
  }

  public Book(
      int id,
      String title,
      String description,
      String publicationDate,
      int replicas,
      int pages,
      Author author,
      Bookcase bookcase,
      Editorial editorial) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.publicationDate = publicationDate;
    this.replicas = replicas;
    this.pages = pages;
    this.author = author;
    this.bookcase = bookcase;
    this.editorial = editorial;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public int getReplicas() {
    return replicas;
  }

  public void setReplicas(int replicas) {
    this.replicas = replicas;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Bookcase getBookcase() {
    return bookcase;
  }

  public void setBookcase(Bookcase bookcase) {
    this.bookcase = bookcase;
  }

  public Editorial getEditorial() {
    return editorial;
  }

  public void setEditorial(Editorial editorial) {
    this.editorial = editorial;
  }
}
