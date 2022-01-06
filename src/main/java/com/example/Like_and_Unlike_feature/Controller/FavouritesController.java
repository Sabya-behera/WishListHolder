package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Model.Favourites;
import com.example.Like_and_Unlike_feature.Repository.FavouritesRepo;
import com.example.Like_and_Unlike_feature.Service.FavouritesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Demo",description = "")
public class FavouritesController {

    @Autowired
    private FavouritesService favouritesService;

    @PostMapping("/save")
    public Object saveData(@RequestBody Favourites favourites) {
        return favouritesService.saveResponse(favourites);
    }

    @PutMapping("/put/{id}")
    public Object update(@PathVariable(name = "id") Long id, @RequestBody Favourites favourites) {
        favouritesService.update(id, favourites);
        return favourites;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "getUsers")
    public Object getByUserDetailsId(@PathVariable Long id) {
        return favouritesService.getByUserDetailsId(id);
    }

    //Per page =5[n]
    //current page = 0 [Page]
//    @GetMapping("/getPaging/{offset}")
//    public Object getAll(@PathVariable(value = "offset") Integer offset) {
//        //Current Page ,Record per Page-5
//        Sort sort = Sort.by(Sort.Order.desc("id"));
//        Pageable pageable = PageRequest.of(offset, 5, sort);
//        Page<Favourites> favouritesPage = this.favouritesService.arrange(pageable);
//        return favouritesPage;
//    }

    //offset = (page - 1) * itemsPerPage + 1
    @GetMapping("/getPaging/{offset}/{limit}")
    public Object getAll(@PathVariable(value = "offset") Integer offset,@PathVariable(value = "limit") int limit) {
     return favouritesService.find(offset,limit);
    }

}