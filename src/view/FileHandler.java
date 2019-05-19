package view;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<ArrayList<Integer>> loadKey(final Stage ownerStage){

        return openIt(chooseFileToLoad(ownerStage));
    }

    public static void saveKey(final Stage ownerStage, ArrayList<ArrayList<Integer>> key){

        saveIt(chooseFileToSave(ownerStage), key);
    }

    private static File chooseFileToSave(final Stage ownerStage){

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Key File", "*.key"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );
        fileChooser.setTitle("SAVE KEY");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        return fileChooser.showSaveDialog(ownerStage);
    }

    private static File chooseFileToLoad(final Stage ownerStage){

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Key File", "*.key"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );

        fileChooser.setTitle("Loading key from a file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        return fileChooser.showOpenDialog(ownerStage);
    }

    private static ArrayList<ArrayList<Integer>> openIt(File file){

        try {
            if (file == null) return null;
            ArrayList<ArrayList<Integer>> output = new ArrayList<>();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ArrayList<Integer> bytethread = new ArrayList<>();
            int length = fis.available();
            for (int i = 0; i < length; i++) {

                bytethread.add(fis.read());
            }

            for (int i = 0; i < Math.sqrt(length); i++) {

                output.add(new ArrayList<>());
                for (int j = 0; j < Math.sqrt(length); j++) {

                    output.get(i).add(bytethread.get((int)(i*Math.sqrt(length)) + j));
                }

            }
            return output;


        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Load Failed");
            alert.showAndWait();
            return null;
        }
    }

    private static void saveIt(File file, ArrayList<ArrayList<Integer>> key){

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file.getAbsolutePath());
            for (ArrayList<Integer> integers : key) {

                for (Integer integer : integers)
                    fos.write(integer.byteValue());

            }
            fos.close();

        } catch (IOException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Saving Failed");
            alert.showAndWait();
        }

    }
}
