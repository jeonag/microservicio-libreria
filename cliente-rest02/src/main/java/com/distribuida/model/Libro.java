package com.distribuida.model;

public class Libro {
	private Integer id;
	private String isbn;
	private String title;
	private Integer author_id;
	private Autor author;



	public Autor getAuthor() {
		return author;
	}

	public void setAuthor(Autor author) {
		this.author = author;
	}

	public Libro() {
	}

		public Libro(String isbn, String title, Integer author_id) {
			super();
			this.isbn = isbn;
			this.title = title;
			this.author_id = author_id;
		}

	 

	public Libro(Integer id, Integer author_id, String isbn, String title) {
			super();
			this.id = id;
			this.author_id = author_id;
			this.isbn = isbn;
			this.title = title;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	 
	 
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	
	

}
