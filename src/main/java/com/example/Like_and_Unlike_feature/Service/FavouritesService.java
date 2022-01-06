package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.Favourites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FavouritesService {
    public Object saveResponse(Favourites favourites);
    Object update(Long id, Favourites favourites);

    Object getByUserDetailsId(Long id);

    Page<Favourites> arrange(Pageable pageable);

    Page<Favourites> find(int offset, int limit);
    String saveCoinImage(String symbol, MultipartFile multipartFile);
}
