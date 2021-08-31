package br.com.zupacademy.alana.casadocodigo.estado;

import br.com.zupacademy.alana.casadocodigo.paises.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    public List<Estado> findByNomeAndPais(String nome, Pais pais);

    public List<Estado> findByPais_Id(Long id);
}