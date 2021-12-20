package com.example.Like_and_Unlike_feature.Repository;

import com.example.Like_and_Unlike_feature.Model.Favourites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FavouritesRepo extends JpaRepository<Favourites,Long> {
    @Query(value = "SELECT * FROM favourites where from_coin=:fromCoin and to_coin=:toCoin and user_details_id=:userDetailsId",nativeQuery = true)
    List<Favourites> existsByUserDetailsId(String fromCoin,String toCoin,Long userDetailsId);
    @Modifying
    @Transactional
    @Query(value = "delete from favourites where from_coin=:fromCoin and to_coin=:toCoin and user_details_id=:userDetailsId", nativeQuery = true)
    void deleteByUnlike(String fromCoin,String toCoin,Long userDetailsId);
    @Query(value = "SELECT * from favourites where user_details_id=:userDetailsId",nativeQuery = true)
    List<Favourites> getByUserDetailsId(Long userDetailsId);

    //Current Page ,Record per Page-5
    @Query(value = "SELECT * from Favourites f ",nativeQuery = true)
    public Page<Favourites> arrange(Pageable pageable);


}

