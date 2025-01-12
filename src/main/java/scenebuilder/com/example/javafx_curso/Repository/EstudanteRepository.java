package scenebuilder.com.example.javafx_curso.Repository;

import scenebuilder.com.example.javafx_curso.Dao.EstudanteDao;
import scenebuilder.com.example.javafx_curso.Model.Estudante;

import java.util.List;

public class EstudanteRepository {

    private final EstudanteDao estudanteDao;


    public EstudanteRepository(EstudanteDao estudanteDao) {
        this.estudanteDao = estudanteDao;
    }
    public List<Estudante> buscEstudantes(){
        return estudanteDao.buscarTodos();
    }
}
