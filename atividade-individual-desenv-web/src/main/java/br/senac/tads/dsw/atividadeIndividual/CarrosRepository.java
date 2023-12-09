package br.senac.tads.dsw.atividadeIndividual;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarrosRepository extends JpaRepository<Carros, Integer> {
//    Page<Carros> findByModeloContainingIgnoreCase(String modelo, Pageable page);

//    Page<Carros> findByModeloContainingIgnoreCaseOrFabricanteContainingIgnoreCase(String modelo, String fabricante,Pageable page);

    @Query("""
        SELECT car FROM Carros car
            WHERE UPPER(car.modelo) LIKE UPPER(concat('%', ?1,'%')) OR
                UPPER(car.fabricante) LIKE UPPER(concat('%', ?1,'%'))
            """)
    Page<Carros> findComJpql(String termo, Pageable page);

}
