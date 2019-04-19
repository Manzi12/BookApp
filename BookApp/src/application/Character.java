package application;

import javafx.beans.property.SimpleStringProperty;

public class Character {
		
		private SimpleStringProperty name;
		private SimpleStringProperty gender;
		private SimpleStringProperty description;

		private myLinkedList<Book> bookList=new myLinkedList<>();
		
		
		
		public myLinkedList<Book> getBookList() {
			return bookList;
		}




		public void setBookList(myLinkedList<Book> bookList) {
			this.bookList = bookList;
		}




		public Character(String name,String gender,String description) {
			this.name = new SimpleStringProperty(name);
			this.gender = new SimpleStringProperty(gender);
			this.description =new SimpleStringProperty(description);
		}
		
		
		
		
		/**
		 * getters and setters for the class variables
		 * @return
		 */
		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name = new SimpleStringProperty(name);
		}

		public String getGender() {
			return gender.get();
		}

		public void setGender(String gender) {
			this.gender = new SimpleStringProperty(gender);
		}

		public String getDescription() {
			return description.get();
		}

		public void setDescription(String description) {
			this.description = new SimpleStringProperty(description);
		}
		
		
		public String toString() {
			return "The name of the character is: " + name + "\n" +
					"The gneder of the character is: " + gender + "\n" +
					"summary description of the charaster: "  + description;
		}
		
		
	}



