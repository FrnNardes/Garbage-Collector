import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/simulacao.fxml")); // Carrega o FXML
        Parent root = loader.load(); // Define o root com o FXML e carrega o controller atraves do metodo load
        Scene scene = new Scene(root); // Define a cena com o root

        primaryStage.setScene(scene); // Define a cena no Stage
        primaryStage.setTitle("Garbage Collector"); // Define o titulo do Stage
        // primaryStage.initStyle(StageStyle.UNDECORATED); // Estiliza o Stage, removendo as bordas
        primaryStage.setResizable(false); // Define o Stage como nao redimensionavel
        primaryStage.show(); // Exibe o Stage para o usuario
    } // Fim do metodo start
  public static void main(String[] args) {
    launch(args);
  } // Fim do metodo main
}// Fim da classe Principal
