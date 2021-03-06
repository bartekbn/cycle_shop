package com.example.cycleshop.api;

import com.example.cycleshop.api.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    //@Query(nativeQuery = true, value = "delete from bike where id= ?1") //sql
    @Modifying
    @Query(value = "delete from Bike b  where b.id = :id") //jpql
    int deleteBikeById(Long id);

    @Modifying
    @Query(value = "update  Bike b set b.price = b.price + :value") //jpql
    //@Query(nativeQuery = true, value  = "update  bike set price = price + :value") //sql
    void switchPrice(BigDecimal value);
}
