package com.example.Like_and_Unlike_feature.Repository;

import com.example.Like_and_Unlike_feature.Model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin,Long> {

    Coin findByShortName(String shortName);
}