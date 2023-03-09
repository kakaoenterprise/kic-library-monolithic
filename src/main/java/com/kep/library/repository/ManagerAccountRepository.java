package com.kep.library.repository;

import com.kep.library.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository("ManagerAccountRepository")
public interface ManagerAccountRepository extends CrudRepository<ManagerEntity, String>, JpaRepository<ManagerEntity, String> {

  ManagerEntity findByLibrarianId(String librarianId);

  ManagerEntity findByLibrarianIdAndPassword(String librarianId, String password);

}
