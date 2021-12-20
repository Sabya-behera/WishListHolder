package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Model.Nft;
import com.example.Like_and_Unlike_feature.Response;
import com.example.Like_and_Unlike_feature.Service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
public class NFTController {

    @Autowired
    NFTService nftService;

    @PostMapping(value = "/post")
    public Response create(@RequestBody Nft nft, UriComponentsBuilder builder) throws URISyntaxException {
        Response responseDTO = new Response();
        try {
            responseDTO.setMessage("Success");
            responseDTO.setData( nftService.save(nft));
            responseDTO.setCode(HttpStatus.CREATED.value());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/post").buildAndExpand(nft.getTokenId()).toUri());
        } catch (Exception e)
        {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
        return responseDTO;
    }
}
//response----->{
//        "code": 200,
//        "msg": "success",
//        "hash": "0x21c0997a2582a9e1c21043a74002575ef841240f794c60eb2dfb77e96161211b"
//        }