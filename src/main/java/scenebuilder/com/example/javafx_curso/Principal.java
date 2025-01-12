package scenebuilder.com.example.javafx_curso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenebuilder.com.example.javafx_curso.Dao.EstudanteDao;
import scenebuilder.com.example.javafx_curso.Model.Estudante;

public class Principal extends Application {

    Stage janela;

    @Override
    public void start(Stage stage) throws Exception{

        janela = stage;
        Parent tela = FXMLLoader.load(getClass().getResource("telas/estudante.fxml"));

        Scene scene = new Scene(tela);
        janela.setScene(scene);
        janela.show();






    }
}
