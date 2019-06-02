package view;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.ByteBuffer;
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

        return null;

        /* Очень непонятно пока мне, как адекватно писать и читать лонги в файле
            Поэтому функция сохранения\загрузки ключа пока недоступна :(
        * */

       /* try {
            if (file == null) return null;
            ArrayList<ArrayList<Long>> output = new ArrayList<>();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            output.add(new ArrayList<>());
            int length = fis.available();
            int j = 0;
            for (int i = 0; i < length / 4; i++) {

                if (output.get(j).size() == (int)Math.sqrt(length / 4)){
                    output.add(new ArrayList<>());
                    j++;
                }

                byte[] array = new byte[Long.BYTES];

                for (int k = 0; k < Long.BYTES; k++) {
                    array[k] = (byte)fis.read();
                }

                output.get(j).add(ByteBuffer.wrap(array).getLong());
            }

            return output;

        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Load Failed");
            alert.show();
            return null;
        }*/
    }

    private static void saveIt(File file, ArrayList<ArrayList<Long>> key){

        /*FileOutputStream fos;
        try {
            fos = new FileOutputStream(file.getAbsolutePath());

            for (ArrayList<Long> Longs : key) {

                for (Long aLong: Longs){

                    for (byte i : (ByteBuffer.allocate(Long.BYTES)
                            .putLong(aLong).array())) {

                        fos.write(i);
                    }
                }
            }
            fos.close();

        } catch (IOException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Saving Failed");
            alert.show();
        }*/

    }
}
