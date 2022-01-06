package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.ExchangePair;

import java.util.HashMap;
import java.util.List;


public interface ExchangePairService {
    Object getAllPairs();

    Object saveResponse(String fromCoin, String toCoin, ExchangePair.Status status);

    Object deletePairs(String fromCoin, String toCoin);

    Object updatePairs(String fromCoin, String toCoin, Long id);

    Object updatePairs1(String fromCoin, String toCoin, ExchangePair.Status status);

    Object  selectByActiveStatus();

    List<HashMap> getAllCurrencies();
}
