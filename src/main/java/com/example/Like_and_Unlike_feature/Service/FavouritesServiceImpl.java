package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.TransactionCounter;
import com.example.Like_and_Unlike_feature.Repository.TransactionRepo;
import com.example.Like_and_Unlike_feature.Response;
import com.example.Like_and_Unlike_feature.Model.Favourites;
import com.example.Like_and_Unlike_feature.Repository.FavouritesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouritesRepo favouritesRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public Object saveResponse(Favourites favourites) {
        Response responseDTO = new Response();
        try {
            List<Favourites> favouritesList = favouritesRepo.existsByUserDetailsId(favourites.getFromCoin(), favourites.getToCoin(), favourites.getUserDetailsId());
            if (favouritesList.isEmpty())
            {
                if (favourites.getFavouriteStatus().equalsIgnoreCase("LIKE"))
                {
                    System.out.println("That else");
                    responseDTO.setData(favouritesRepo.save(favourites));
                    responseDTO.setCode(HttpStatus.OK.value());
                    responseDTO.setMessage("execute successfully !!");

                    TransactionCounter counter =new TransactionCounter();
                    Long id = transactionRepo.findByfavouriteCounter(counter.getFavouritesCounter());
                    System.out.println(id);
                    if(id==null)
                    {
                        transactionRepo.insertOne();
                    }
                    else if (id>=1)
                    {
//                       id+=id;
                       counter.setFavouritesCounter(transactionRepo.favouriteCounter(id));
                       transactionRepo.save(counter);
                       System.out.println("Here it is");
                    }
                }
            }
            else if (favouritesList.size() == 1)
            {
                if (favourites.getFavouriteStatus().equalsIgnoreCase("UNLIKE"))
                {
                    favouritesRepo.deleteByUnlike(favourites.getFromCoin(), favourites.getToCoin(), favourites.getUserDetailsId());
                    System.out.println("Delete operation");
                    responseDTO.setCode(HttpStatus.GONE.value());
                     responseDTO.setMessage("Deleted value");
                }
                else
                    System.out.println("Working");
             responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
             responseDTO.setMessage("Coin Pair already found!!");
            }

          } catch (Exception e)
            {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
            }
        return responseDTO;
    }

    @Override
    public Object update(Long id, Favourites favourites)
    {
        Response responseDTO = new Response();
        Favourites favourites1=favouritesRepo.getById(id);

        try
        {
        favourites1.setFavouriteStatus(favourites.getFavouriteStatus());
        favourites1.setFromCoin(favourites.getFromCoin());
        favourites1.setToCoin(favourites.getToCoin());
        favourites1.setUserDetailsId(favourites.getUserDetailsId());
        if (favourites1.getFavouriteStatus().equalsIgnoreCase("UNLIKE"))
        {
            favouritesRepo.deleteByUnlike(favourites.getFromCoin(), favourites.getToCoin(), favourites.getUserDetailsId());
            responseDTO.setCode(HttpStatus.GONE.value());
            responseDTO.setMessage("Deleted value");
        }
        else
        {
            responseDTO.setData(favouritesRepo.save(favourites1));
            responseDTO.setCode(HttpStatus.OK.value());
            responseDTO.setMessage("Request Completed Successfully !!");
        }
        } catch (Exception e)
        {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
        return responseDTO;
    }
    @Override
    public Object getByUserDetailsId(Long id)
    {
        Response responseDTO = new Response();
        try {
            responseDTO.setData(favouritesRepo.getByUserDetailsId(id));
            if (responseDTO.getData().equals(id)) {
                responseDTO.setCode(HttpStatus.FOUND.value());
                responseDTO.setMessage("Desried result displayed");
            }else
                responseDTO.setCode(HttpStatus.NOT_FOUND.value());
                responseDTO.setMessage("Value Not Available");
        }catch (Exception e)
        {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(" NOT Found Failure!!!");
        }
         return responseDTO;
    }

    @Override
    public Page<Favourites> arrange(Pageable pageable) {
        return favouritesRepo.arrange(pageable);
    }

    public Page<Favourites> find(int offset, int limit) {
        return favouritesRepo.findAll(PageRequest.of(offset, limit, Sort.Direction.DESC, "id"));
        }

    @Override
    public String saveCoinImage(String symbol, MultipartFile multipartFile)
    {
            String url = null;
            Coin byShortName =  (coinRepository.findByShortName(symbol.toUpperCase())==null?coinRepository.findByShortName(symbol):coinRepository.findByShortName(symbol.toUpperCase()));
            if (byShortName != null) {
                if (file != null) {
                    url = amazonClient.uploadFile(file);
                    byShortName.setIcon(url);
                    coinRepository.save(byShortName);
                }
                return url;
            } else {
                return null;
            }
        }
}