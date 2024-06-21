package br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, BigInteger> {

}
