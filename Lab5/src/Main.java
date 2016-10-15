/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.CollectionOfBooks;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.MainView;

/**
 *
 * @author Niklas
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        CollectionOfBooks books = new CollectionOfBooks();
        Canvas canvas = new Canvas(200, 400);
        ImageView bookImage = new ImageView();
        bookImage.setX(300);
        bookImage.setY(300);
        StackPane st = new StackPane();
        HBox imageBox = new HBox();
        MainView view = new MainView(books, primaryStage, canvas, bookImage, imageBox);
        
        //imageBox.setAlignment(Pos.BASELINE_LEFT);
        imageBox.setPadding(new Insets(10, 10, 5, 10));
        imageBox.getChildren().setAll(bookImage);
        imageBox.setMouseTransparent(true);
        //st.setMouseTransparent(false);
        st.getChildren().setAll(view, imageBox); 
        /*Group root = new Group();
        root.getChildren().add(view);
        root.getChildren().add(canvas);
        canvas.blendModeProperty();*/

        
        
        Scene scene = new Scene(st);
        
        primaryStage.setTitle("Library");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



        /*Button btn = new Button();
        btn.setText("read from file");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    readFile(books, stage);
                } catch (IOException ex) {
                    System.out.println("shit happned");
                }
                System.out.println("Hello World!");
            }
        });*/