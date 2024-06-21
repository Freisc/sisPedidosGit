package br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Cliente;
import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository.ClienteRepository;

@RestController
public class ClienteController {
    private final ClienteRepository repositorio;
    public ClienteController(ClienteRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Cliente> getTodosClientes() {
        return (List<Cliente>) repositorio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClientePorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("ID do Cliente não encontrado!"));
    }
    
    @GetMapping("/clientes/municipio-id/{id}")
    public List<Cliente> getClientePorMunicipioId(@PathVariable BigInteger id) {
        return (List<Cliente>) repositorio.findByMunicipioId(id);
    }

    @GetMapping("/clientes/municipio-nome/{nome}")
    public List<Cliente> getClientePorMunicipioNome(@PathVariable String nome) {
        return (List<Cliente>) repositorio.findByMunicipioNomeIgnoreCase(nome);
    }

    @PostMapping("/cliente")
    public Cliente criarCliente(@RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível criar o cliente!" + e.getMessage());
        }
    }
    
    @PutMapping("/cliente/{id}")
    public Cliente alterarCliente(@PathVariable BigInteger id, @RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível alterar o cliente!" + e.getMessage());
        }
    }

    @DeleteMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable BigInteger id) throws Exception {
        try{
            repositorio.deleteById(id);
            return "Excluído!";
        }catch(Exception e){
            throw new Exception("Não foi possível excluir o ID informado!" + e.getMessage());
        }
    }
}
