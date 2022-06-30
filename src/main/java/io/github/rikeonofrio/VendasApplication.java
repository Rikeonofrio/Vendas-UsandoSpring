package io.github.rikeonofrio;


import io.github.rikeonofrio.domain.entity.Cliente;
import io.github.rikeonofrio.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }

    ;

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {

            System.out.println("Salvando clientes");
            clientes.salvar(new Cliente("rike"));
            clientes.salvar(new Cliente("kaynne"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println(" Buscando clientes ");
            clientes.buscaPorNome("kay").forEach(System.out::print);

//            System.out.println("deletando clientes");
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });

            todosClientes = clientes.obterTodos();
            if (todosClientes.isEmpty()) {
                System.out.println("nenhum cliente encontrado");
            } else {
                todosClientes.forEach(System.out::println);
            }
        };


    }
}





