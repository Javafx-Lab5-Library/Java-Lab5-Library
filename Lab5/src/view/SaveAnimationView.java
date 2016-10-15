/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Niklas
 */
public class SaveAnimationView {

    private Canvas canvas;
    private Image image;
    private ImageView img;
    private ClockTimer timer;
    private ImageView saveImage;
    private HBox imageBox;
    private double x, y, width, height;
    
    public SaveAnimationView(Canvas canvas, ImageView saveImage, HBox imageBox) {
        timer = new ClockTimer();
        this.canvas = canvas;
        this.saveImage = saveImage;
        this.imageBox = imageBox;
        initAnimation();
        
    }

    private void initAnimation() {
        image = new Image(this.getClass().
                getResource("library-books.png").
                toString());
        /*width = image.getWidth();
        height = image.getHeight();
        img = new ImageView(this.getClass().
                getResource("library-books.png").
                toString());
        Rectangle2D viewportRect = new Rectangle2D(40, 35, 110, 110);
        img.setViewport(viewportRect);
        img.setVisible(true);*/
        //saveImage = new ImageView(this.getClass().
        //        getResource("library-books.png").
        //        toString());
        saveImage.setImage(image);
        saveImage.setFitWidth(100);
        saveImage.setPreserveRatio(true);
        
       // saveImage.setVisible(false);
        
        x = 0.0;
        y = 0.0;
        saveImage.setX(300.0);
        saveImage.setY(300.0);
        saveImage.setVisible(false);
       // canvas.setFocusTraversable(true);
      //  canvas.requestFocus();
        
        //drawImage();
        
        //timer.start();
    }
    
    public void startAnimation() {
        saveImage.setX(300);
        saveImage.setY(300);
        x = 30.0;
        imageBox.setPadding(new Insets(10, 10, 5, x));

        System.out.println("UGH");
        saveImage.setVisible(true);
        timer.start();
    }
    
    private void drawImage() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(image, x, y, width, height);
        
    }


    private class ClockTimer extends AnimationTimer{

        @Override
        public void handle(long now) {
            x += 1;
            imageBox.setPadding(new Insets(10, 10, 5, x));
            saveImage.setY(saveImage.getY() - 1);
            System.out.println(saveImage.getY());
            //saveImage.notify();
            //y -= 1.0;
            //drawImage();
            if (x > 400) {
                System.out.println("TIMERRRRRRRRRRRR");
                saveImage.setVisible(false);
                timer.stop();
            }
            //System.out.println("TIMERRRRRRRRRRRR");
        }
    }
    
}
