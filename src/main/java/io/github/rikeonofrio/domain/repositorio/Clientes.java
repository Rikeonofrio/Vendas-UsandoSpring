package io.github.rikeonofrio.domain.repositorio;

import io.github.rikeonofrio.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  //Classe q acessa a dataBase
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static  String SELECT_ALL = "SELECT * FROM CLIENTE ";

    private  static  String UPDATE = "update cliente set nome = ? where id = ?";

    private static String DELETE ="delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar (Cliente cliente) {

        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    //CASO DE ERR OTESTA CODIG ABAIXO
    public  void deletar (Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }


    public List<Cliente> buscaPorNome (String nome){
        return  jdbcTemplate.query(SELECT_ALL.concat("where nome like  ?"),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }

    public Cliente atualizar (Cliente cliente){
        jdbcTemplate.update(UPDATE,
                cliente.getNome(),
                cliente.getId());
                return cliente;
    }

    public List<Cliente> obterTodos() {
        return  jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
