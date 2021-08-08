package com.programacion.model;

import javax.persistence.*;



@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private int age;
	private String genre;
	private String name;

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

//	@Override
//	public String toString() {
//		return "Author{" +
//	            "id=" + id +
//	            ", age='" + age + '\'' +
//	            ", genre='" + genre + '\'' +
//	            ", name=" + name +
//	            '}';
//	}
}
