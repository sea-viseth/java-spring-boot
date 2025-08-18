package org.springclass.springclassproject.respository;

import org.springclass.springclassproject.respository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("select a from AccountEntity a where a.accountHolderName = :accountHolderName")
    Optional<AccountEntity> getAccountEntityByAccountHolderName(@Param("accountHolderName") String accountHolderName);
}
