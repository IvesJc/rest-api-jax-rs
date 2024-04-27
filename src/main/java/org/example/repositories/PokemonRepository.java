package org.example.repositories;

import org.example.model.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PokemonRepository {

    public static final String URL_CONNECTION = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    public static final String USER = "rm553243";
    public static final String PASSWORD = "180600";

    public PokemonRepository() {
    }

    public List<Pokemon> findAll(){
        List<Pokemon> listPokemon = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM pokemons");
            ResultSet rs = psmt.executeQuery()
        ){
            while (rs.next()){
                Pokemon pokemon = new Pokemon();
                mapResultSetToPokemon(rs, pokemon);
                listPokemon.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPokemon;
    }

    public Optional<Pokemon> findPokemonById(int id){
        Pokemon pokemon = null;

        try (
            Connection conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM pokemons WHERE ID = ?")
        ){
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()){
                pokemon = new Pokemon();
                mapResultSetToPokemon(rs, pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pokemon);
    }

    public int cretePokemon(Pokemon pokemon){
        try (
            Connection conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO pokemons (NOME, TIPO, HP, " +
                    "HABILIDADES, FRAQUEZA, COR) VALUES (?, ?, ?, ?, ?, ?)");
        ){
            prepareStatementForPokemonInsert(pokemon, psmt);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updatePokemon(Pokemon pokemon){
        try(
            Connection conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("UPDATE pokemons SET NOME=?, TIPO=?, HP=?, " +
                    "HABILIDADES=?, FRAQUEZA=?, COR=? WHERE ID=?");
        ){
            prepareStatementForPokemonInsert(pokemon, psmt);
            psmt.setInt(7, pokemon.getId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deletePokemon(int id){
        try (
            Connection conn = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("DELETE pokemons WHERE ID=?")
        ){
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    private void mapResultSetToPokemon(ResultSet rs, Pokemon pokemon) {
        try {
            pokemon.setId(rs.getInt("ID"));
            pokemon.setNome(rs.getString("NOME"));
            pokemon.setTipo(rs.getString("TIPO"));
            pokemon.setHp(rs.getInt("HP"));
            pokemon.setHabilidades(rs.getInt("HABILIDADES"));
            pokemon.setFraqueza(rs.getString("FRAQUEZA"));
            pokemon.setCor(rs.getString("COR"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void prepareStatementForPokemonInsert(Pokemon pokemon, PreparedStatement psmt) throws SQLException {
        psmt.setString(1, pokemon.getNome());
        psmt.setString(2, pokemon.getTipo());
        psmt.setInt(3, pokemon.getHp());
        psmt.setInt(4, pokemon.getHabilidades());
        psmt.setString(5, pokemon.getFraqueza());
        psmt.setString(6, pokemon.getCor());
    }

}
