package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Model.ExchangePair;
import com.example.Like_and_Unlike_feature.Service.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/exchange/pair")
public class ExchangePairController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ExchangePairController.class);
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/list")
    public Object getAllPairs()
    {
        LOGGER.info("My message set at info level------Getting All Pairs");
        LOGGER.debug("Message logged at DEBUG level");
//        LOGGER.error("Message logged at ERROR level");
        return exchangeService.getAllPairs();
    }

    @PostMapping("/save1")
    public Object savePairs(@RequestParam String fromCoin, @RequestParam String toCoin, @RequestParam ExchangePair.Status status)
    {
        LOGGER.info("My message set at info level------Data Saved in!!");
        return exchangeService.saveResponse(fromCoin,toCoin,status);
    }

    @DeleteMapping("/delete")
    public Object deletePairs(@RequestParam String fromCoin,@RequestParam String toCoin)
    {
        Object pair=exchangeService.deletePairs(fromCoin,toCoin);
        LOGGER.info("My message set at info level------Data Deleted !!!");
        return pair;

    }

    @PutMapping("/update/{id}")
    public Object updatePairs(@RequestParam String fromCoin, @RequestParam String toCoin, @PathVariable Long id)
    {
        LOGGER.info("My message set at info level------Updating the data received ...");
        return exchangeService.updatePairs(fromCoin,toCoin,id);
    }
    @PutMapping("/updateStatus")
    public Object updatePairs1(@RequestParam String fromCoin, @RequestParam String toCoin, @RequestParam ExchangePair.Status status)
    {
        LOGGER.info("My message set at info level------Updating the status...");
        return exchangeService.updatePairs1(fromCoin,toCoin,status);
    }

}
