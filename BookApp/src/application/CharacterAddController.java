package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CharacterAddController implements Initializable {
	
	
	//these are columns of the table view 
	@FXML TableView <Character> tableView;
	@FXML private TableColumn <Character,String> nameColumn;
	@FXML private TableColumn <Character,String> genderColumn;
	@FXML private TableColumn <Character,String>descriptionColumn;
	@FXML private ChoiceBox<String> choicebox = new ChoiceBox<String>();
	
	
	
	@FXML private TextField nameTextField;
	@FXML private ChoiceBox<String> gender;
	//@FXML private TextField genderTextField;//publisher
	@FXML private TextField descriptionTextField;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setup columns in the table
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Name"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Gender"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Description"));
		choicebox.getItems().addAll("Male","Female");
	}
	
	public void saveBookButtonClicked() throws Exception {
		Character characters = new Character(nameTextField.getText(), choicebox.getValue()
				,descriptionTextField.getText());
		tableView.getItems().add(characters);
		BookApp.currentBook.getCharacterList().add(characters);
		System.out.println(BookApp.currentBook.getCharacterList().show());
		save();
	}
	
	
	public void changeSceneToBookDetailsScene(ActionEvent event) throws IOException {
		Parent BookDetails = FXMLLoader.load(getClass().getResource("BookDetails.fxml"));
		Scene BookDetailsview = new Scene(BookDetails);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookDetailsview);
		window.show();
	}

	
	
	public ObservableList<Character> getCharacters(){
		ObservableList<Character>characters = FXCollections.observableArrayList();
		characters.add(new Character(null, null, null));
	
	return characters;
	}
	
	//this is the load method that loads saved data to form the xml
	@SuppressWarnings("unchecked")
    public  void load()
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("characters.xml"));
      BookApp.characterList=((myLinkedList<Character>) is.readObject());
        is.close();
    }
    
	/**
	 * this function saves the program data to xml file 
	 * @throws Exception
	 */
    public void save() 
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("characters.xml"));
        out.writeObject(BookApp.characterList);
        out.close();    
    }

}
