package scenebuilder.com.example.javafx_curso.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import scenebuilder.com.example.javafx_curso.Dao.EstudanteDao;
import scenebuilder.com.example.javafx_curso.Model.Estudante;
import scenebuilder.com.example.javafx_curso.Repository.EstudanteRepository;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EstudanteController implements Initializable {



    @FXML
    private Button br_deletar;

    @FXML
    private Button bt_editar;

    @FXML
    private Button bt_salvar;

    @FXML
    private TableView tabelaEstudantes;

    @FXML
    private TableColumn<Estudante, Integer> columId_Estudante;

    @FXML
    private TableColumn<Estudante, Integer> columIdade_Estudante;

    @FXML
    private TableColumn<Estudante, String> columNome_Estudante;

    @FXML
    private TableColumn<Estudante, String> columSexo_Estudante;

    @FXML
    private TextField idade_estudante;

    @FXML
    private TextField nome_estudante;

    @FXML
    private ToggleGroup sexo;

    @FXML
    private RadioButton sexo_femenino;

    @FXML
    private RadioButton sexo_masculino;

    private boolean modeEdit = false;// Indica se estamos editando um estudante

    Estudante estudante = new Estudante();
    EstudanteDao estudanteDao = new EstudanteDao();
    EstudanteRepository estudanteRepository = new EstudanteRepository(new EstudanteDao());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //configurando as colunas do scene buildes
        columId_Estudante.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        columNome_Estudante.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome()));
        columIdade_Estudante.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getIdade()).asObject());
        columSexo_Estudante.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSexo()));

        tabelaEstudantes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                Optional.ofNullable(newValue).ifPresent(estudante -> preencherCampos((Estudante) estudante))
        );//ficar atualizando os campos selecionados na tableViewr

//        columId_Estudante.setCellValueFactory(new PropertyValueFactory<Estudante, Integer>("id"));
//        columNome_Estudante.setCellValueFactory(new PropertyValueFactory<Estudante, String>("nome"));
//        columIdade_Estudante.setCellValueFactory(new PropertyValueFactory<Estudante, Integer>("idade"));
//        columSexo_Estudante.setCellValueFactory(new PropertyValueFactory<Estudante, String>("sexo"));


        carregarDados();
    }
    private void preencherCampos(Estudante estudanteSelecionado) {

        modeEdit = true;
        nome_estudante.setText(estudanteSelecionado.getNome());
        idade_estudante.setText(String.valueOf(estudanteSelecionado.getIdade()));

        if ("F".equals(estudanteSelecionado.getSexo())) {
            sexo_femenino.setSelected(true);
        } else if ("M".equals(estudanteSelecionado.getSexo())) {
            sexo_masculino.setSelected(true);
        }

        // Salva a referência do estudante selecionado para edição
        estudante = estudanteSelecionado;
    }

        public void carregarDados() {

            List<Estudante> estudantes = estudanteRepository.buscEstudantes();
            if (estudantes != null && !estudantes.isEmpty()) {
                ObservableList<Estudante> listaEstudantes = FXCollections.observableArrayList(estudantes);
                tabelaEstudantes.setItems(listaEstudantes);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText("Nenhum dado encontrado");
                alert.setContentText("Não há estudantes cadastrados no banco de dados.");
                alert.show();
            }
        }

//    public void editarEstudante(){
//
//        estudante.setNome("Osvaldo");
//        estudante.setSexo("M");
//        estudante.setIdade(27);
//
//        estudanteDao.editarEstudante(estudante,1);
//    }


    @FXML
    public void salvarEstudante(ActionEvent event) {

        estudante.setNome(nome_estudante.getText().toString());
        estudante.setIdade(Integer.valueOf(idade_estudante.getText().toString()));

        if(sexo_femenino.isSelected()){
            estudante.setSexo("F");
        }else{
            estudante.setSexo("M");
        }
        if(modeEdit == true){
            estudanteDao.editarEstudante(estudante, estudante.getId());
            System.out.println("Estudante atualizado!");
        }else{
            estudanteDao.inserir(estudante);
            System.out.println("Novo estudante salvo!");
        }


        carregarDados();//chama o método para atualizar a tabela de novo com o novo usuário

        limparCampos();



    }


    public void limparCampos(){
        nome_estudante.clear();
        idade_estudante.clear();
        sexo_femenino.setSelected(false);
        sexo_masculino.setSelected(false);
        estudante = new Estudante(); // Reseta o objeto estudante
        modeEdit = false; // Retorna para o modo de inserção
    }


    public void deletarEstudante(ActionEvent actionEvent) {

        estudanteDao.apagarPorId(estudante.getId());
        System.out.println("Estudante deletado");
        limparCampos();
        carregarDados();
    }
}
