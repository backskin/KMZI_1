import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Alg;
import logic.KeyGen;
import org.junit.Test;
import view.CoderView;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/CoderView.fxml"));
        Parent root = loader.load();
        CoderView CVCon = loader.getController();
        CVCon.setStage(primaryStage);
        primaryStage.setTitle("КМЗИ Лаб. работа 1-18");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Test
    private static void test(){

        int modInt = 12;
        
        ArrayList<ArrayList<Integer>> key = new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < 3; i++) {
                add(new ArrayList<>());
                for (int j = 0; j < 5; j++) {
                    get(i).add( i+1);
                }
            }

        }};

        ArrayList<ArrayList<Integer>> key2 = new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < 5; i++) {
                add(new ArrayList<>());
                for (int j = 0; j < 4; j++) {
                    get(i).add(j+1);
                }
            }
        }};

        System.out.println(KeyGen.getKeyAsString(key));
        System.out.println(KeyGen.getKeyAsString(key2));
        System.out.println(KeyGen.getKeyAsString(Alg.mul(key,key2,modInt)));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
