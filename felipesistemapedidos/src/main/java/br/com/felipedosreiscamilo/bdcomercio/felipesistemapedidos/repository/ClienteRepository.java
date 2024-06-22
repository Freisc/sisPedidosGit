package br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, BigInteger> {

    List<Cliente> findByMunicipioId(BigInteger id);
    List<Cliente> findByMunicipioNomeIgnoreCase(String nome);
}
