package application.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import application.Main;
import application.model.Book;
//CoverViewerController.java
//Controller for Cover Viewer application
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoverViewerController {
	
	//instance variable to track the current selected book
	private Book currentBook;

    @FXML
    private ListView<Book> booksListView;

    @FXML
    private ImageView coverImageView;
    
    
 // stores the list of Book Objects
    //A list that allows listeners to track changes when they occur
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public void initialize() throws IOException {
       // populate the ObservableList<Book>
    	/*
       books.add(new Book("Android How to Program", 
          "/images/small/androidhtp.jpg", "/images/large/androidhtp.jpg"));
       books.add(new Book("C How to Program", 
          "/images/small/chtp.jpg", "/images/large/chtp.jpg"));
       books.add(new Book("C++ How to Program",
          "/images/small/cpphtp.jpg", "/images/large/cpphtp.jpg"));
       books.add(new Book("Internet and World Wide Web How to Program",  
          "/images/small/iw3htp.jpg", "/images/large/iw3htp.jpg"));
       books.add(new Book("Java How to Program", 
          "/images/small/jhtp.jpg", "/images/large/jhtp.jpg"));
       books.add(new Book("Visual Basic How to Program", 
          "/images/small/vbhtp.jpg", "/images/large/vbhtp.jpg"));
       books.add(new Book("Visual C# How to Program", 
          "/images/small/vcshtp.jpg", "/images/large/vcshtp.jpg"));
          */
    	
    	/*The relative path works in Java using the . specifier.

			. means same folder as the currently running context.
			.. means the parent folder of the currently running context.
			So the question is how do you know the path where the Java is currently looking?

			Do a small experiment

   			File directory = new File("./");
   			System.out.println(directory.getAbsolutePath());
    	*/
    	
    	
    	File directory = new File("./");
    	System.out.println(directory.getAbsolutePath());
    	String path = "./src/application";
    	String dataPath = path + "/data/bookInfo.txt";
    	File file = new File(dataPath);
    	
    	Scanner input = new Scanner(file);
    	while(input.hasNext()) {
    		String[] record = input.nextLine().split(",");
    		System.out.println(record[0]);
    		System.out.println(path + record[1]);
    		System.out.println(path + record[2]);
    		books.add(new Book(record[0].trim(), path +  record[1].trim(), path + record[2].trim()));
    		
    	}
    	input.close();
        booksListView.setItems(books); //bind booksListView to books
        
        
        // when ListView selection changes, show large cover in ImageView
        booksListView.getSelectionModel().selectedItemProperty().
           addListener(
              new ChangeListener<Book>() {                                   
                 @Override                                                     
                 public void changed(ObservableValue<? extends Book> ov,
                    Book oldValue, Book newValue) {                        
                    coverImageView.setImage(
                       new Image(new File(newValue.getLargeImage()).toURI().toString()));
                    currentBook = newValue;
                    System.out.println(currentBook.getTitle());
              
                 }
              }
           );
         
    }
    //https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8#:~:text=Create%20a%20new%20instance%20of,is%20the%20same%20as%20before
    @FXML
    void showDetailsButtonClicked(ActionEvent event) {
    	try {
			//Parent root = FXMLLoader.load(getClass().getResource("../view/Library.fxml"));
    		System.out.println(getClass().getClassLoader());
    		FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("view/bookDetail.fxml") );
    	    // Step 3
    	    BookDetailController controller = new BookDetailController();
    	    controller.setBook(currentBook);
    	    // Step 4
    	    loader.setController(controller);
    	    // Step 5
    	    Parent root = loader.load();
			Main.stage.setScene( new Scene(root, 600, 600) );
			Main.stage.setTitle("Book Detail");
			Main.stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}

    }
    
    public Book getCurrentBook() {
    	return currentBook;
    }
    


}
