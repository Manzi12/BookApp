package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.stream.events.Characters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookDetailsController implements Initializable {
	@FXML private TextField searchTextField;



	@FXML private ListView <String> bookTitles;
	@FXML TabPane tabPane = new TabPane();
	@FXML Tab bookDetailsTab = new Tab("Book Details");
	@FXML Tab characterTab = new Tab("Character");
	@FXML private ListView<String> charactersNameList;
	

	
	//books details that i need to be displayed in the grid
	@FXML TextField authorTextField;
	@FXML TextField yearOfPublicationTextField;
	@FXML TextField publisherTextField;
	@FXML TextField numberOfPagesTextField;
	@FXML TextField genreTextField;
	@FXML TextField plotDescriptionTextField;
	@FXML TextField bookCoverLinkTextField;
	
	
	public void selectBookFromList(MouseEvent e) {
		//authorTextField.setText(bookTitles.getSelectionModel().getSelectedItem());
		//charactersNameList.getItems().clear();
		
		if(bookTitles.getSelectionModel().getSelectedIndex()>=0) {
			String title=bookTitles.getSelectionModel().getSelectedItem();
			
			for(Book book : BookApp.bookList)
				if(book.getTitle().equals(title)) {
					authorTextField.setText(book.getAuthor());
					yearOfPublicationTextField.setText(Integer.toString(book.getYearOfPublication()));
					publisherTextField.setText(book.getPublisher());
					numberOfPagesTextField.setText(Integer.toString(book.getNumberOfPages()));
					genreTextField.setText(book.getGenre());
					plotDescriptionTextField.setText(book.getPlotDescription());
					bookCoverLinkTextField.setText(book.getBookCoverImageLink());
				
					BookApp.currentBook= book;
					
					charactersNameList.getItems().clear();
					for(Character characterInBook : BookApp.currentBook.getCharacterList()) {
						//Character.bookList.add(book.characterList.add(characterInBook););
						
						charactersNameList.getItems().add(characterInBook.getName());
					}
				}
			
					
				}
		}
		
	
	
	
	//public void selectedCharacter(MouseEvent e) {
		
	
//	public void addCharacterInTheSpecificBook(ActionEvent event) throws IOException {
//		if(bookTitles.getSelectionModel().getSelectedIndex()>0) {
//			Parent BookAddFacility = FXMLLoader.load(getClass().getResource("CharacterAdd.fxml"));
//			Scene BookAddFacilityview = new Scene(BookAddFacility);
//			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//			window.setScene(BookAddFacilityview);
//			window.show();
//			
//			for(Character characterInBook : Book.characterList) {
//				charactersNameList.getItems().add(characterInBook.getName());
//			}
//		}
//	}
//	
	
	public void changeSceneToAddCharacterScene(ActionEvent event) throws IOException {
		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("CharacterAdd.fxml"));
		Scene BookAddFacilityview = new Scene(BookAddFacility);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookAddFacilityview);
		myLinkedList<Book> book = BookApp.bookList;
		window.show();
	}
	
	public void changeSceneToAddBookScene(ActionEvent event) throws IOException {
		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
		Scene BookAddFacilityview = new Scene(BookAddFacility);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookAddFacilityview);
		window.show();
	}
	
		

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for(Character character : BookApp.characterList) {
			charactersNameList.getItems().add(character.getName());
			charactersNameList.getSelectionModel().getSelectedIndex();
		}
		
		
		for(Book book : BookApp.bookList) {
			bookTitles.getItems().add(book.getTitle());
			bookTitles.getSelectionModel().getSelectedIndex();
			
		}
		
		
	
		
		
	}
	
	

}




