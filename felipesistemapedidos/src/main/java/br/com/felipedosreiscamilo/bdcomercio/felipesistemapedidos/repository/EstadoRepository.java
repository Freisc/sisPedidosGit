package br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.felipedosreiscamilo.bdcomercio.felipesistemapedidos.model.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, BigInteger>{
    // SELECT * from tb_estados where tx_nome = ('nome')
    List<Estado> findByNome(String nome);

    // SELECT * from tb_estados where UPPER(tx_nome) like UPPER('nome%') | o % diz que pode ter qualquer palavra após ou antes do símbolo.
    List<Estado> findByNomeStartingWithIgnoreCase(String nome);

    List<Estado> findByNomeEndingWithIgnoreCase(String nome);

    // SELECT * from tb_estados where UPPER(tx_nome) like UPPER('%nome%')
    List<Estado> findByNomeContainingIgnoreCase(String nome);

    @Query("FROM Estado e WHERE e.nome like %?1") // o "?1" determina que é uma variável, se tivesse mais variáveis teria ?2, ?3... como o %d do C
    List<Estado> findByMinhaQuery(String nome);

    /*@Query(value = "Select * from tb_estados", nativeQuery = true)
    List<Estado> findByNativeQuery();*/
}
