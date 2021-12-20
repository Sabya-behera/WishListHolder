package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.Nft;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class NFTService
 {

    public Object save(Nft nft) throws URISyntaxException {
        Nft nft1=new Nft();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("https://rest.tux-wallet.com/nft/transfer");
        nft1.setContractAddress(nft.getContractAddress());
        nft1.setFromAddress(nft.getFromAddress());
        nft1.setReceiverAddress(nft.getReceiverAddress());
        nft1.setPrivateKey(nft.getPrivateKey());
        nft1.setTokenId(nft.getTokenId());
        HttpEntity<Nft> httpEntity = new HttpEntity<>(nft, headers);
        RestTemplate restTemplate = new RestTemplate();
        Nft nft2= restTemplate.postForObject(uri, httpEntity,Nft.class);
        System.out.println("Id: " + nft2.getTokenId());
        return nft1;
    }
}

