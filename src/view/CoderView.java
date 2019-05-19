package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Alg;
import logic.KeyGen;

import java.util.ArrayList;

public class CoderView {

    private KeyGen gen = null;
    private ArrayList<ArrayList<Integer>> currentKey;
    private int alphabet = 256;
    private Stage initStage;

    private ArrayList<ArrayList<Integer>> newKey(int gram) {

        gen = new KeyGen (gram, alphabet);
        gen.generateKey();
        return gen.getKey();
    }

    public void setStage(Stage stage){
        initStage = stage;
    }

    public KeyGen getGenerator() {

        return gen;
    }

    @FXML
    private TextField gramaField;

    @FXML
    private Text keyArea;

    @FXML
    private TextArea cyphText;

    @FXML
    private TextArea origText;

    @FXML
    private Label deterLabel;


    @FXML
    private void initialize(){

        keyArea.setText("");

    }

    public CoderView(){

    }


    @FXML
    public void genHandle() {

        try {

            if ( gramaField.getText().isEmpty() ){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Type value!");
                alert.showAndWait();
                return;
            }

            int g = Integer.parseInt(gramaField.getText());

            if (g < 1)
                throw new NumberFormatException("Value less than 1");


            currentKey = newKey(g);

            keyArea.setText(gen.getKeyAsString());
            deterLabel.setText(Double.toString(Alg.determinant(gen.getKey(), alphabet)));
            ArrayList<ArrayList<Integer>> antikey = gen.antikey();
        }

        catch (NumberFormatException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Value Exception");
            alert.setContentText("This is wrong values! (Must be numbers > 0)");
            alert.showAndWait();
        }
    }

    @FXML
    public void cipherHandle() {

    }

    @FXML
    public void decipherHandle() {

    }

    @FXML
    public void openKeyHandle() {

        currentKey = FileHandler.loadKey(initStage);
        keyArea.setText(KeyGen.getKeyAsString(currentKey));
    }

    @FXML
    public void saveKeyHandle() {

        FileHandler.saveKey(initStage, gen.getKey());
    }
}
