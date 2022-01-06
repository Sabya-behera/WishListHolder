package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.Coin;
import com.example.Like_and_Unlike_feature.Model.ExchangePair;
import com.example.Like_and_Unlike_feature.Repository.CoinRepository;
import com.example.Like_and_Unlike_feature.Repository.ExchangePairRepo;
import com.example.Like_and_Unlike_feature.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangePairServiceImpl implements ExchangePairService {

    private final RestTemplate restTemplate;

    @Autowired
    private ExchangePairRepo exchangePairRepo;
    @Autowired
    private CoinRepository coinRepository;

    public ExchangePairServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Object getAllPairs() {
        Response responseDTO = new Response();
        {
            responseDTO.setData(exchangePairRepo.findAll());
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setMessage("Request Completed Successfully");
            return responseDTO;
        }
    }

    public Object saveResponse(String fromCoin, String toCoin, ExchangePair.Status status) {
        Response responseDTO = new Response();
        try {
            List<ExchangePair> exchangePairList = exchangePairRepo.existsByPairs(fromCoin, toCoin);
            if (exchangePairList.isEmpty()) {
                String s = fromCoin.toUpperCase();
                String s1 = toCoin.toUpperCase();
                ExchangePair exchangePair1 = new ExchangePair();
                exchangePair1.setFromCoin(s);
                exchangePair1.setToCoin(s1);
                exchangePair1.setStatus(status);
                responseDTO.setData(exchangePairRepo.save(exchangePair1));
                responseDTO.setCode(HttpStatus.OK.value());
                responseDTO.setMessage("Request Completed Successfully");
            } else if (exchangePairList.size() == 1) {
                List<ExchangePair> exchangePairList1 = exchangePairRepo.existsByPairs(fromCoin, toCoin);
                if (exchangePairList1.equals(exchangePairList)) {
                    responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
                    responseDTO.setMessage("exchange pair already exits");
                }
            }
        } catch (Exception e) {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
        return responseDTO;

    }

    @Override
    public Object deletePairs(String fromCoin, String toCoin) {
        Response responseDTO = new Response();
        List<ExchangePair> exchangePairList = exchangePairRepo.existsByPairs1(fromCoin, toCoin);
        if (exchangePairList.size() == 1) {
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setData(exchangePairRepo.deleteBydata(fromCoin, toCoin));
            responseDTO.setMessage("Request Completed Successfully");
        } else {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("exchange Pair not found");
        }
        return responseDTO;
    }

    @Override
    public Object updatePairs(String fromCoin, String toCoin, Long id) {
        Response responseDTO = new Response();
        ExchangePair exchangePair = exchangePairRepo.findById(id).get();
//           if(exchangePair.equals(exchangePairRepo.existsByPairs1(fromCoin,toCoin))) {
        exchangePair.setFromCoin(fromCoin.toUpperCase());
        exchangePair.setToCoin(toCoin.toUpperCase());
        exchangePairRepo.save(exchangePair);
        responseDTO.setData(exchangePair);
        responseDTO.setMessage("Request Completed Successfully");
        responseDTO.setCode(HttpStatus.OK.value());
//           }
//           else
//               responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
//               responseDTO.setMessage("Exchange Pair not found")
        return responseDTO;
    }

    @Override
    public Object updatePairs1(String fromCoin, String toCoin, ExchangePair.Status status) {
        Response responseDTO = new Response();
        List<ExchangePair> exchangePairList = exchangePairRepo.existsByPairs(fromCoin, toCoin);
        for (ExchangePair exchangePair : exchangePairList) {
            exchangePair.setStatus(status);
            responseDTO.setData(exchangePairRepo.save(exchangePair));
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setMessage("Request Completed Successfully");
        }
        return responseDTO;
    }

    @Override
    public Response selectByActiveStatus() {
        Response responseDTO = new Response();
        {
            responseDTO.setData(exchangePairRepo.findAllByStatus(ExchangePair.Status.ACTIVE));
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setMessage("Request Completed Successfully");

        }
        return responseDTO;
    }

    @Override
    public List<HashMap> getAllCurrencies() {
        List<HashMap> response=new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        Map<String, Object> params = new HashMap<>();
        params.put("active", true);
        params.put("fixedRate", true);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.changenow.io/v1/currencies");
        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
            ResponseEntity<List> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), List.class);

            if (exchange.getStatusCode() == HttpStatus.OK)
            {
                List<HashMap> body = exchange.getBody();
                body.forEach(o -> {
                    Coin ticker = coinRepository.findByShortName(o.get("ticker").toString().toUpperCase());
                    if (ticker != null)
                    {
                        response.add(o);
                    }

                });
            }
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
        return response;
    }
}
