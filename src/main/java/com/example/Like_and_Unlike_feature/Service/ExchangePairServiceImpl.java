package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.ExchangePair;
import com.example.Like_and_Unlike_feature.Repository.ExchangePairRepo;
import com.example.Like_and_Unlike_feature.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangePairServiceImpl implements ExchangeService {

    @Autowired
    private ExchangePairRepo exchangePairRepo;

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

    public Object saveResponse(String fromCoin, String toCoin, ExchangePair.Status status)
    {
        Response responseDTO = new Response();
        try {
            List<ExchangePair> exchangePairList = exchangePairRepo.existsByPairs(fromCoin,toCoin);
            if (exchangePairList.isEmpty())
            {
                String s = fromCoin.toUpperCase();
                String s1 = toCoin.toUpperCase();
                ExchangePair exchangePair1 = new ExchangePair();
                exchangePair1.setFromCoin(s);
                exchangePair1.setToCoin(s1);
                exchangePair1.setStatus(status);
                responseDTO.setData(exchangePairRepo.save(exchangePair1));
                responseDTO.setCode(HttpStatus.OK.value());
                responseDTO.setMessage("Request Completed Successfully");
            }
            else if (exchangePairList.size() == 1) {
                List<ExchangePair> exchangePairList1 = exchangePairRepo.existsByPairs(fromCoin, toCoin);
                if (exchangePairList1.equals(exchangePairList)) {
                    responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
                    responseDTO.setMessage("exchange pair already exits");
                }
            }
        }    catch (Exception e)
            {
                responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage(" NOT Found Failure!!!");
            }
          return responseDTO;

    }
    @Override
    public Object deletePairs(String fromCoin, String toCoin)
    {
        Response responseDTO = new Response();
        List<ExchangePair> exchangePairList = exchangePairRepo.existsByPairs1(fromCoin,toCoin);
        if(exchangePairList.size()==1)
        {
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setData(exchangePairRepo.deleteBydata(fromCoin,toCoin));
            responseDTO.setMessage("Request Completed Successfully");
        }
        else {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("exchange Pair not found");
        }
        return responseDTO;
    }

    @Override
    public Object updatePairs(String fromCoin, String toCoin, Long id)
    {
        Response responseDTO = new Response();
        ExchangePair exchangePair=exchangePairRepo.findById(id).get();
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
    public Object updatePairs1(String fromCoin, String toCoin, ExchangePair.Status status)
    {
        Response responseDTO = new Response();
        List<ExchangePair> exchangePairList=exchangePairRepo.existsByPairs(fromCoin, toCoin);
        for( ExchangePair exchangePair: exchangePairList) {
            exchangePair.setStatus(status);
            responseDTO.setData(exchangePairRepo.save(exchangePair));
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setMessage("Request Completed Successfully");
        }
        return responseDTO;
    }
}
