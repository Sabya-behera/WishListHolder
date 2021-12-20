package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Repository.TransactionRepo;
import com.example.Like_and_Unlike_feature.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping(name = "/transaction")
public class TransanctionController {

    @Autowired
    private TransactionRepo transactionRepo;
    @GetMapping("/sumfavourites")
    public Response getAllFavouritesTransactions(@RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {
        Response responseDTO = new Response();
        try {
            responseDTO.setMessage("Success");
            responseDTO.setData(transactionRepo.add());
            responseDTO.setCode(HttpStatus.OK.value());
        } catch (Exception e)
        {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
        return responseDTO;
    }

    @GetMapping("/sumEmail")
    public Response getAllEmailTransactions(@RequestHeader(name = "Accept-Language", required = false) Locale locale)
    {
        Response responseDTO = new Response();
        try {
            responseDTO.setMessage("Success");
            responseDTO.setData(transactionRepo.addmails());
            responseDTO.setCode(HttpStatus.OK.value());
        } catch (Exception e)
        {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
        return responseDTO;
    }
}
