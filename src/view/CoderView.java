package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Alg;
import logic.Cipherator;
import logic.KeyGen;

import java.util.ArrayList;

public class CoderView {

    private KeyGen gen = null;
    private ArrayList<ArrayList<Integer>> currentKey;
    private Stage initStage;

    private ArrayList<ArrayList<Integer>> newKey(int gram) {

        gen = new KeyGen (gram);
        gen.generateKey();
        return gen.getKey();
    }

    public void setStage(Stage stage){
        initStage = stage;
    }

    @FXML
    private TextField gramaField;

    @FXML
    private Text keyArea;

    @FXML
    private TextArea origText;

    @FXML
    private TextArea cyphText;

    @FXML
    private Label deterLabel;

    @FXML
    private Button cButton;

    @FXML
    private Button dButton;

    @FXML
    private void initialize(){

        keyArea.setText("");
        cButton.setDisable(true);
        dButton.setDisable(true);

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
            deterLabel.setText(Double.toString(Alg.determinant(gen.getKey(), KeyGen.alphabetPower)));
            cButton.setDisable(false);
            dButton.setDisable(false);
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

        if (!origText.getText().isEmpty())
            cyphText.setText(Cipherator.cip(origText.getText(), currentKey));
    }

    @FXML
    public void decipherHandle() {

        if (!cyphText.getText().isEmpty())
            origText.setText(Cipherator.desip(cyphText.getText(), currentKey));
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
