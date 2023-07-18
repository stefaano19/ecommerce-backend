package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProdottoOrdinato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoOrdinatoRepository extends JpaRepository<ProdottoOrdinato, Long> {

}
