package com.francis.byteworkstest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "food_category")
public class FoodCategory implements Serializable{
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "category_id", nullable = false, unique = true)
	private long id;
	
	
	@Column(name = "category_name")
	private String categoryName;
	
	
	    //@JsonIgnore
		@OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private List<Food> food;


		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public List<Food> getFood() {
			return food;
		}

		public void setFood(List<Food> food) {
			this.food = food;
		}
		
		
}
