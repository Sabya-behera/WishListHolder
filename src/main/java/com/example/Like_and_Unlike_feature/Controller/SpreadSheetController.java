package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Configuration.ExcelGenerator;
import com.example.Like_and_Unlike_feature.Model.Favourites;
import com.example.Like_and_Unlike_feature.Repository.FavouritesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Data")
public class SpreadSheetController {

    @Autowired
    private FavouritesRepo favouritesRepo;

    @GetMapping("/ExcelSheet")
    public ResponseEntity<InputStreamResource> excelFavouriteReport() throws IOException
    {
        List<Favourites> list = favouritesRepo.findAll();

        ByteArrayInputStream in = ExcelGenerator.FavouritesToExcel(list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Favourites.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
