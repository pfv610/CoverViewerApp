package application.controller;

import application.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class BookDetailController {
	
	private Book currentBook;

    @FXML
    private Label bookLabel;
    
    @FXML
    private TextArea bookTextArea;

    @FXML
    void nextButtonClicked(ActionEvent event) {

    }

    @FXML
    void prevButtonClicked(ActionEvent event) {

    }
    
    public void setBook(Book currentBook) {
    	this.currentBook = currentBook;
    }
    
    public void initialize() {
    	bookLabel.setText(currentBook.getTitle());
    	bookTextArea.setText("jdfkjdfkdjfkjd  fjkdjfdfjdlf d jfkdjfkdjfkdjf djkdjfkdjfd");
    }

}
