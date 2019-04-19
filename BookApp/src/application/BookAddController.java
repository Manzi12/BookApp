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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class BookAddController implements Initializable {	
	

	@FXML public static TableView <Book> tableView;
	
	@FXML private TableColumn <Book,String> titleColumn;
	@FXML private TableColumn <Book,String> authorColumn;
	@FXML private TableColumn <Book,Integer> yearOfPublicationColumn;
	@FXML private TableColumn <Book,String>publisherColumn;
	@FXML private TableColumn <Book,Integer>numberOfPagesColumn;
	@FXML private TableColumn <Book,String>genreColumn;
	@FXML private TableColumn <Book,String>plotDescriptionColumn;
	@FXML private TableColumn <Book,String>bookCoverColumn;
	@FXML private ChoiceBox<String> choicebox = new ChoiceBox<String>();
	
	//Initialise the variable in the 
	@FXML private TextField titleTextField;
	@FXML private TextField authorTextField;//author
	@FXML private TextField yearOfPublicationTextField;
	@FXML private TextField publisherTextField;//publisher
	@FXML private TextField numberOfPagesTextField;
	@FXML private ChoiceBox<String> choiceboxx;
	//@FXML private TextField genreTextField;//genre
	@FXML private TextArea plotDecriptionTextArea;
	@FXML private TextField imageCoverlinkTextField;
	
	//save button puts the data in the table
	public void saveBookButtonClicked() throws Exception {
		Book books = new Book(titleTextField.getText(), authorTextField.getText(),
				Integer.parseInt(yearOfPublicationTextField.getText()),publisherTextField.getText(),Integer.parseInt(numberOfPagesTextField.getText()),choicebox.getValue(),
				plotDecriptionTextArea.getText(),imageCoverlinkTextField.getText());
		tableView.getItems().add(books);
		BookApp.bookList.add(books);
		System.out.println(BookApp.bookList.show());
		save();
	}
	
	public void titleEditCellEvent(CellEditEvent edittedCell) {
		Book bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setTitle(edittedCell.getNewValue().toString());
	}
	
	public void authorEditCellEvent(CellEditEvent edittedCell) {
		Book bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setTitle(edittedCell.getNewValue().toString());
	}
	
	public void yearOfPublicationEditCellEvent(CellEditEvent edittedCell) {
		Book bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setTitle(edittedCell.getNewValue().toString());
	}

	public void publisherEditCellEvent(CellEditEvent edittedCell) {
		Book bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setTitle(edittedCell.getNewValue().toString());
	}
	
//	titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//	titleColumn.setOnEditCommit(
//	    new EventHandler<CellEditEvent<Person, String>>() {
//	        @Override
//	        public void handle(CellEditEvent<Person, String> t) {
//	            ((Person) t.getTableView().getItems().get(
//	                t.getTablePosition().getRow())
//	                ).setFirstName(t.getNewValue());
//	        }
//	    }
//}
	
	
	//this changes the scene form the table add and list to the main list
	public void changeSceneToMainScene(ActionEvent event) throws IOException {
		Parent MainView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene Mainscene = new Scene(MainView);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(Mainscene);
		window.show();
	}
	
	//this changes the scene form the add book to the book details scene
	
	public void changeSceneToBookDetailsScene(ActionEvent event) throws IOException {
		Parent BookDetails = FXMLLoader.load(getClass().getResource("BookDetails.fxml"));
		Scene BookDetailsview = new Scene(BookDetails);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookDetailsview);
		window.show();
	}
	
	
	//this method initializes the table 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setup columns in the table
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
		yearOfPublicationColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("yearOfPublication"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
		numberOfPagesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numberOfPages"));
		genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
		plotDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("plotDescription"));
		bookCoverColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("bookCoverImageLink"));
		//tableView.setItems(getBooks());
		
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		choicebox.getItems().addAll("Horror","Fantasy","Comedy","Science", "Religion", "History","Mystery", "Fiction", "Biography", "Autobiography", "Self-Help", "Journal", "Uncatagorized");
		tableView.setEditable(true);
		titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		//yearOfPublicationColumn.setCellFactory(Integer.toString(TextFieldTableCell.forTableColumn()));
		publisherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		genreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		plotDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		bookCoverColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//implemetntin the edit with the row not the columns
		//tableView.getSelectionModel().selectedItemProperty().addListener(obs,);
	}
	
	//this method changes the data to the arraylist collections so that it can be displayed in the table view
	public ObservableList<Book> getBooks(){
		ObservableList<Book>books = FXCollections.observableArrayList();
		books.add(new Book("Manxi", "Manxi jose", 250, "Manxi", 1200, "Manxi", "Manxi", "Manxi"));
	
	return books;
	}
	
	//deleting selected row from the table
	public void deleteButtonClicked() {
		ObservableList<Book> selectedRows, allBooks;
		allBooks = tableView.getItems();
		selectedRows = tableView.getSelectionModel().getSelectedItems();
		
		for(Book books:selectedRows) {
			allBooks.remove(books);
		}
	}
	
	
	//this is the load method that loads saved data to form the xml
	@SuppressWarnings("unchecked")
    public  void load()
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("books.xml"));
      BookApp.bookList=((myLinkedList<Book>) is.readObject());
        is.close();
    }
    
	/**
	 * this function saves the program data to xml file 
	 * @throws Exception
	 */
    public void save() 
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("books.xml"));
        out.writeObject(BookApp.bookList);
        out.close();    

    }
}
    
    
	


