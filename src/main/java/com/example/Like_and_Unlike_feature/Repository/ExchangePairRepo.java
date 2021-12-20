package com.example.Like_and_Unlike_feature.Repository;

import com.example.Like_and_Unlike_feature.Model.ExchangePair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ExchangePairRepo extends JpaRepository<ExchangePair,Long> {

    @Query(value = "SELECT * FROM exchangepair where from_coin=:fromCoin and to_coin=:toCoin",nativeQuery = true)
    List<ExchangePair> existsByPairs(String fromCoin, String toCoin);

    @Modifying
    @Transactional
    @Query(value = "delete from exchangepair where from_coin=:fromCoin and to_coin=:toCoin", nativeQuery = true)
    Object deleteBydata(String fromCoin,String toCoin);

    @Query(value = "SELECT * FROM exchangepair where from_coin=:fromCoin and to_coin=:toCoin",nativeQuery = true)
    List<ExchangePair> existsByPairs1(String fromCoin, String toCoin);



//    @Modifying
//    @Query(value = "insert into exchangepair (fromCoin,toCoin,status) select :from_coin,:to_coin,:status from exchangepair")
//    List<ExchangePair>modifyingQueryInsertExchangePair(@Param("fromCoin")String fromCoin, @Param("toCoin")String toCoin, @Param("status") ExchangePair.Status status);

//    @Query(value = "SELECT distinct f.from_coin as fromCoin,f.to_coin as toCoin from favourites f",nativeQuery = true)
//    List<FromToRepoInterface> getDistinctCoinPair();
//    @Query(value = "SELECT new com.example.Like_and_Unlike_feature.Model.Favourites(id,fromcoin,tocoin,status) from favourites",nativeQuery = true)
//    List<ExchangePairRepoInterface> getExchangePairList();
}
