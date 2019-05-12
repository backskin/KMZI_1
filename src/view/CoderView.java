package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.Alg;
import logic.KeyGen;

import java.util.ArrayList;
import java.util.Objects;

public class CoderView {

    private KeyGen gen = null;

    private void newKey(int gram, int alpha) {

        gen = new KeyGen (gram, alpha);
        gen.generateKey();
    }


    public KeyGen getGenerator() {

        return gen;
    }

    @FXML
    private TextField alphaField;

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

            if ((gramaField.getText().isEmpty() || alphaField.getText().isEmpty())){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Type values!");
                alert.showAndWait();
                return;
            }

            int g = Integer.parseInt(gramaField.getText());
            int a = Integer.parseInt(alphaField.getText());

            if (g < 1 || a < 1)
                throw new NumberFormatException("Values less than 1");
            newKey(g,a);
            keyArea.setText(gen.getKeyAsString());
            deterLabel.setText(Double.toString(Alg.determinant(gen.getKey(), a)));
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
}
