package br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Estado;
import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository.EstadoRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.util.ModoBusca;




@RestController
public class EstadoController {
    private final EstadoRepository repositorio;
    public EstadoController(EstadoRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/estados")
    public List<Estado> getTodosEstados() {
        return (List<Estado>) repositorio.findAll();
    }

    @GetMapping("/estado/{id}")
    public Estado getEstadoPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("ID Inválido."));
    }

    @GetMapping("/estados/nome/{nome}")
    public List<Estado> getEstadosPorNome(@PathVariable String nome,
    @RequestParam(required = true) ModoBusca modoBusca) {
        if(modoBusca.equals(ModoBusca.EXATO)){
            return repositorio.findByNome(nome);
        }else if (modoBusca.equals(ModoBusca.INICIADO)){
            return repositorio.findByNomeStartingWithIgnoreCase(nome);
        }else if (modoBusca.equals(ModoBusca.FINALIZADO)){
            return repositorio.findByNomeEndingWithIgnoreCase(nome);
        }else{
            return repositorio.findByNomeContainingIgnoreCase(nome);
        }       
    }
    
    @PostMapping("/estado")
    public Estado criarEstado(@RequestBody Estado entity) throws Exception {
        try{
            return repositorio.save(entity);
        } catch(Exception e){
            throw new Exception("Erro ao salvar o Estado.");
        }
    }

    @PutMapping("/estado/{id}")
    public Estado alterarEstado(@PathVariable BigInteger id, 
                                @RequestBody Estado novosDados) throws Exception {

        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()){
            //Atribuir novo nome ao objeto já existem no banco de dados
            estadoArmazenado.get().setNome(novosDados.getNome());
            //
            return repositorio.save(estadoArmazenado.get());
        }        
        throw new Exception("Alteração não foi realizada.");
    }
    
    @DeleteMapping("/estado/{id}")
    public String deletarEstado(@PathVariable BigInteger id) throws Exception {
        
        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()){
            repositorio.delete(estadoArmazenado.get());
            return "Excluído!";
        }
        throw new Exception("ID não encontrado para a exclusão.");
    }
}