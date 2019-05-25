package view;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

class FileHandler {

    static ArrayList<ArrayList<Long>> loadKey(final Stage ownerStage){

        return openIt(chooseFileToLoad(ownerStage));
    }

    static void saveKey(final Stage ownerStage, ArrayList<ArrayList<Long>> key){

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

    private static ArrayList<ArrayList<Long>> openIt(File file){

        try {
            if (file == null) return null;
            ArrayList<ArrayList<Long>> output = new ArrayList<>();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            ArrayList<Integer> bytethread = new ArrayList<>();
            int length = fis.available();
            for (int i = 0; i < length; i++) {

                bytethread.add(fis.read());
            }

            for (int i = 0; i < Math.sqrt(length); i++) {

                output.add(new ArrayList<>());
                for (int j = 0; j < Math.sqrt(length); j++) {

                    output.get(i).add(bytethread.get((int)(i*Math.sqrt(length)) + j).longValue());
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

    private static void saveIt(File file, ArrayList<ArrayList<Long>> key){

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file.getAbsolutePath());
            for (ArrayList<Long> Longs : key) {

                for (Long Long : Longs)
                    fos.write(Long.byteValue());

            }
            fos.close();

        } catch (IOException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Saving Failed");
            alert.showAndWait();
        }

    }
}
