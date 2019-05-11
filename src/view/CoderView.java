package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private Text keyArea1;

    @FXML
    private Text keyArea2;

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

            if (g < 2 || a < 2)
                throw new NumberFormatException("Values less than 2");
            newKey(g,a);
            keyArea.setText(gen.getKeyAsString());
            deterLabel.setText(Double.toString(Alg.determinant(gen.getKey(), a)));
            ArrayList<ArrayList<Integer>> antikey = gen.antikey();
            keyArea1.setText(KeyGen.getKeyAsString(antikey));
            keyArea2.setText(KeyGen.getKeyAsString(KeyGen.antikey(gen.antikey(), a)));
        }

        catch (NumberFormatException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Value Exception");
            alert.setContentText("This is wrong values! (Must be numbers > 1)");
            alert.showAndWait();
        }
    }
}
