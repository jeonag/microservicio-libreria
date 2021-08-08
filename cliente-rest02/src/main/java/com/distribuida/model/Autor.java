package com.distribuida.model;

public class Autor {
	private Integer id;
	private int age;
	private String genre;
	private String name;

	public Autor() {
	}

	public Autor(int age, String genre, String name) {
		super();
		this.age = age;
		this.genre = genre;
		this.name = name;
	}

	public Autor(Integer id, int age, String genre, String name) {
		super();
		this.id = id;
		this.age = age;
		this.genre = genre;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	


	@Override
	public String toString() {
		return "Autor [id_author=" + id + ", age=" + age + ", genre=" + genre + ", name=" + name + "]";
	}
}
