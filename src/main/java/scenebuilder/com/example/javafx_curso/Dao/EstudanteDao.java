package scenebuilder.com.example.javafx_curso.Dao;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import scenebuilder.com.example.javafx_curso.Connection.ConnectBD;
import scenebuilder.com.example.javafx_curso.Model.Estudante;
import scenebuilder.com.example.javafx_curso.Repository.EstudanteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDao {


    public Estudante buscarPorId(Integer id) {

        Estudante estudante = null;

        try{
            String sql = "SELECT * from estudante where idestudante=?";

            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();//armazena o resultado do meu sql dentro de rs.

            if(rs.next()){
                estudante = new Estudante();

                estudante.setId(rs.getInt("idestudante"));
                estudante.setNome(rs.getString("nomeEstudante"));
                estudante.setIdade(rs.getInt("idadeEstudante"));
                estudante.setSexo(rs.getString("sexoEstudante"));
            }

        }catch (Exception e){
            System.out.println("ERRO "+e.getMessage());
        }

        return estudante;
    }


    public List<Estudante> buscarTodos() {


        List<Estudante> resultados = new ArrayList<>();
        String sql = "SELECT * FROM estudante";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Estudante estudante = new Estudante();
                estudante.setId(rs.getInt("idestudante"));
                estudante.setNome(rs.getString("nomeEstudante"));
                estudante.setIdade(rs.getInt("idadeEstudante"));
                estudante.setSexo(rs.getString("sexoEstudante"));

                resultados.add(estudante);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }


    public void editarEstudante(Estudante estudante, Integer id){


        try{
            String sql = "UPDATE estudante SET nomeEstudante=?, sexoEstudante=?, idadeEstudante=? where idestudante=?";

            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);

            ps.setString(1,estudante.getNome());
            ps.setString(2, estudante.getSexo());
            ps.setInt(3, estudante.getIdade());
            ps.setInt(4, id);

            ps.executeUpdate();

        }catch (Exception e){
            System.out.println("ERRO" + e.getMessage());

        }

    }
    public void inserir(Estudante estudante) {

        try{

            String sql = "INSERT INTO estudante (nomeEstudante, sexoEstudante, idadeEstudante) VALUES (?,?,?)";
            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);

            ps.setString(1, estudante.getNome());
            ps.setString(2, estudante.getSexo());
            ps.setInt(3, estudante.getIdade());

            ps.execute();

        }catch (Exception e){
            System.out.println("ERRO "+e.getMessage());
        }

    }


    public void apagarPorId(Integer id) {


        try{
            String sql = "DELETE from estudante where idestudante=?";

            PreparedStatement ps = ConnectBD.getConnection().prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();
            //Execute() só vai retornar verdadeira ou falso;
            //ExecuteQuery() vai retornar uma lista de resultados..




        }catch (Exception e){
            System.out.println("ERRO " + e.getMessage());

        }

    }


}


