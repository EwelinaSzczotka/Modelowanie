package automaty;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static java.awt.Color.black;


public class layoutController {

    @FXML
    TextField ruleTxt;
    @FXML
    TextField sizeTxt;
    @FXML
    ChoiceBox bcBox;
    @FXML
    Button startBtn;
    @FXML
    Canvas canvas;

    private GraphicsContext gc;
    private int[] b;
    private int g;
    private int width;
    private int w;

    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        width = 600;
        bcBox.getItems().add("periodyczny");
        bcBox.getItems().add("stały");
        bcBox.getItems().add("odbijający");
        bcBox.getSelectionModel().selectFirst();
        Platform.runLater(new Runnable() {
            public void run() {
                startBtn.requestFocus();
            }
        });
    }

    @FXML
    public void start(){

        int size = Integer.parseInt(sizeTxt.getText());
        int rule = Integer.parseInt(ruleTxt.getText());
        String bc = bcBox.getValue().toString();
        w = width/size;
        Automat aut = new Automat(size, rule, bc);
        g = 0;
        draw(aut.firstStep());
        g++;
        b = new int[size];
        for(int i = 0; i<size; i++){
            b = aut.nextStep();
            draw(b);
            g++;
        }
    }

    public void draw(int[] b){
        for(int i = 0; i<b.length;i++){
            if(b[i] == 1) gc.setFill(Color.BLACK);
            else gc.setFill(Color.WHITE);
            gc.fillRect(i*w,g*w,w,w);
        }
    }

}
