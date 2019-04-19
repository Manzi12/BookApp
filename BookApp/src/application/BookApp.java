package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class BookApp extends Application {
	public static myLinkedList<Book> bookList = new myLinkedList<Book>();
	public static myLinkedList<Character> characterList = new myLinkedList<Character>();
	public static Book currentBook = new Book();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BookApp.class.getResource("MainView.fxml"));
			AnchorPane mainLayout = new AnchorPane();
			mainLayout = loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Interesting Books");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
