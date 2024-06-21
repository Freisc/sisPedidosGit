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

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Produto;
import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository.ProdutoRepository;

@RestController
public class ProdutoController {
    private final ProdutoRepository repositorio;
    public ProdutoController(ProdutoRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/produtos")
    public List<Produto> getTodosProdutos() {
        return (List<Produto>) repositorio.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("ID Não Encontrado!"));
    }
    
    @PostMapping("/produto")
    public Produto criarProduto(@RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o produto." + e.getMessage());
        }
    }
    
    @PutMapping("/produto/{id}")
    public Produto alterarProduto(@PathVariable BigInteger id, @RequestBody Produto entity) throws Exception {
        try{
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível alterar o produto." + e.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}")
    public String deleteProduto(@PathVariable BigInteger id) throws Exception {
        try{
            repositorio.deleteById(id);
            return "Excluído!";
        } catch (Exception e) {
            throw new Exception("Não foi possível apagar o ID fornecido!" + e.getMessage());
        }
    }
}
