package com.carlabeatriz.cursoWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlabeatriz.cursoWeb.domain.Produto;

@Repository
public interface ProtudoRepository extends JpaRepository<Produto, Integer> {

}
