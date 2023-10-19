package com.project.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "country")
public class Country {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "country_id")
	    private Long id;
	    private String name;
		public Country() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Country(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	    
}
