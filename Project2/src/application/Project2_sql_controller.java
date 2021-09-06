package application;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Project2_sql_controller implements Initializable {
	Student connection = new Student();

    @FXML
    private Label movieid;

    @FXML
    private Label title;

    @FXML
    private Label language;

    @FXML
    private Label productionCompany;

    @FXML
    private Label productionCountry;

    @FXML
    private Label releaseDate;

    @FXML
    private Label runtime;

    @FXML
    private TextField movieid_text;

    @FXML
    private TextField title_text;

    @FXML
    private TextField language_text;

    @FXML
    private TextField productionCompany_text;

    @FXML
    private TextField productionCountry_text;

    @FXML
    private TextField releaseDate_text;

    @FXML
    private TextField runtime_text;
    
    @FXML
    private Button search;
    
    @FXML
    private TableView<Movie> tableview;
    
    @FXML TableColumn<Movie, String> movieid_column;
    @FXML TableColumn<Movie, String> title_column;
    @FXML TableColumn<Movie, String> language_column;
    @FXML TableColumn<Movie, String> productionCompany_column;
    @FXML TableColumn<Movie, String> productionCountry_column;
    @FXML TableColumn<Movie, String> releaseDate_column;
    @FXML TableColumn<Movie, String> runtime_column;
    @FXML TableColumn<Movie, String> avgrating_column;
    
    ObservableList<Movie> dataList;
    String query; 
 
    @FXML
    void searchMovie(MouseEvent event) {  //When the search button is clicked
    	try {
    		
    		if (nothingEntered() == true) { //if user does not enter anything in the text fields
    			query = "SELECT m.MovieID, m.Title, m.Language, m.Production_company, m.Production_country, m.Release_date, m.Runtime, AVG(Rating)"
    					+ " FROM Movies m, Ratings r"
    					+ " WHERE m.MovieID = r.MovieID"
    					+ " GROUP BY m.MovieID, m.Title, m.Language, m.Production_company, m.Production_country, m.Release_date, m.Runtime";
    			dataList = Student.getAll(query);
    			tableview.setItems(dataList);
    		}
    		else {
    			query = getQuery();
    			//System.out.println(query);
    			dataList = Student.getAll(query);
    			tableview.setItems(dataList);
    		}
		} catch (Exception e) {
			System.out.println("searchMovie() FAILED");
		}
    }
    
    String getQuery() { // function will build a query statement to be passed
    	String finalQuery = "SELECT m.MovieID, m.Title, m.Language, m.Production_company, m.Production_country, m.Release_date, m.Runtime, AVG(Rating)"
				+ " FROM Movies m, Ratings r"
				+ " WHERE m.MovieID = r.MovieID AND ";
    	ArrayList<String> enteredFields = new ArrayList<String>(); //will add pices of the WHERE query in here
    	int andAmount = 0; //the number of ANDS that need to be in the query
    	
    	if (!(movieid_text.getText().isEmpty())) { // MovieID = '123'
    		enteredFields.add("m.MovieID = '" + movieid_text.getText() + "'");
    		andAmount += 1;
    	}
    	if (!(title_text.getText().isEmpty())) { // LOWER(Title) LIKE LOWER('%kong%')
    		enteredFields.add("LOWER(m.Title) LIKE LOWER('%" + title_text.getText() + "%')");
    		andAmount += 1;
    	}
    	if (!(language_text.getText().isEmpty())) { // Language = 'en'
    		enteredFields.add("m.Language = '" + language_text.getText() + "'");
    		andAmount += 1;
    	}
    	if (!(productionCompany_text.getText().isEmpty())) {// LOWER(Production_company) LIKE LOWER('%warner%')
    		enteredFields.add("LOWER(m.Production_company) LIKE LOWER('%" + productionCompany_text.getText() + "%')");
    		andAmount += 1;
    	}
    	if (!(productionCountry_text.getText().isEmpty())) {// LOWER(Production_country) LOWER('LIKE %united states%
    		enteredFields.add("LOWER(m.Production_country) LIKE LOWER('%" + productionCountry_text.getText() + "%')");
    		andAmount += 1;
    	}
    	if (!(releaseDate_text.getText().isEmpty())) { // Release_date = TO_DATE('07/31/1968', 'MM/DD/YYYY')
    		enteredFields.add("m.Release_date = TO_DATE('" + releaseDate_text.getText() + "', 'MM/DD/YYYY')");
    		andAmount += 1;
    	}
    	if (!(runtime_text.getText().isEmpty())) { //Runtime = '170'
    		enteredFields.add("m.Runtime = '" + runtime_text.getText() + "'");
    		andAmount += 1;
    	}
    	
    	for (int i = 0; i < andAmount; i++) {
    		if (i == andAmount - 1) { //NO AND 
    			finalQuery += enteredFields.get(i);
    			finalQuery += " GROUP BY m.MovieID, m.Title, m.Language, m.Production_company, m.Production_country, m.Release_date, m.Runtime";
    		}
    		else { //ADD AND 
    			finalQuery += enteredFields.get(i) + " AND ";
    		}
    		
    	}
    	return finalQuery;
    }
    
    boolean nothingEntered() {
    	if (movieid_text.getText().isEmpty() && title_text.getText().isEmpty() && language_text.getText().isEmpty() && 
    			productionCompany_text.getText().isEmpty() && productionCountry_text.getText().isEmpty() && 
    			releaseDate_text.getText().isEmpty() && runtime_text.getText().isEmpty()) {
    		return true;
    	}
		return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	movieid_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieid"));
    	title_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
    	language_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("language"));
    	productionCompany_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("productioncompany"));
    	productionCountry_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("productioncountry"));
    	releaseDate_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("releasedate"));
    	runtime_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("runtime"));
    	avgrating_column.setCellValueFactory(new PropertyValueFactory<Movie, String>("avgrating"));
    }


}
