import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CoderView;

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


    public static void main(String[] args) {
        launch(args);
    }
}
