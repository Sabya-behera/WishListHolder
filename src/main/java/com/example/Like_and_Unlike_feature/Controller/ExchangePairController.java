package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Model.ExchangePair;
import com.example.Like_and_Unlike_feature.Response;
import com.example.Like_and_Unlike_feature.Service.ExchangePairService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(name = "/exchange/pair")
public class ExchangePairController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ExchangePairController.class);
    @Autowired
    private ExchangePairService exchangePairService;

    @GetMapping("/list")
    public Object getAllPairs()
    {
        LOGGER.info("My message set at info level------Getting All Pairs");
        LOGGER.debug("Message logged at DEBUG level");
//        LOGGER.error("Message logged at ERROR level");


        return exchangePairService.getAllPairs();
    }

    @PostMapping("/save1")
    public Object savePairs(@RequestParam String fromCoin, @RequestParam String toCoin, @RequestParam ExchangePair.Status status)
    {
        LOGGER.info("My message set at info level------Data Saved in!!");
        return exchangePairService.saveResponse(fromCoin,toCoin,status);
    }

    @DeleteMapping("/delete")
    public Object deletePairs(@RequestParam String fromCoin,@RequestParam String toCoin)
    {
        Object pair= exchangePairService.deletePairs(fromCoin,toCoin);
        LOGGER.info("My message set at info level------Data Deleted !!!");
        return pair;

    }

    @PutMapping("/update/{id}")
    public Object updatePairs(@RequestParam String fromCoin, @RequestParam String toCoin, @PathVariable Long id)
    {
        LOGGER.info("My message set at info level------Updating the data received ...");
        return exchangePairService.updatePairs(fromCoin,toCoin,id);
    }
    @PutMapping("/updateStatus")
    public Object updatePairs1(@RequestParam String fromCoin, @RequestParam String toCoin, @RequestParam ExchangePair.Status status)
    {
        LOGGER.info("My message set at info level------Updating the status...");
        return exchangePairService.updatePairs1(fromCoin,toCoin,status);
    }

    @GetMapping("/listActiveOnly")
    public Response<?> ActiveExpchangePairList(@RequestHeader(name = "AcceptLanguage",required = false)Locale locale)
    {
        return (Response<?>) exchangePairService.selectByActiveStatus();
    }
    @GetMapping("/getCurrencies")
    public List<HashMap> getCurrencies(@RequestHeader(name = "Accept-Language", required = false) Locale locale) throws JsonProcessingException {
        return exchangePairService.getAllCurrencies();
    }

}
