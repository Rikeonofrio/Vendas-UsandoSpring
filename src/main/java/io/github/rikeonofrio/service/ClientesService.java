package io.github.rikeonofrio.service;

import io.github.rikeonofrio.model.Cliente;
import io.github.rikeonofrio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClienteRepository repository;

    @Autowired
    public ClientesService (ClienteRepository repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
//        ClienteRepository clienteRepository = new ClienteRepository();
       this.repository.persistir(cliente);

    }
    public void validarCliente(Cliente cliente){
        //aplicaValidaçoes
    }
}
