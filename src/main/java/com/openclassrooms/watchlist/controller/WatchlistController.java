package com.openclassrooms.watchlist.controller;

import java.util.HashMap;
import java.util.Map;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
public class WatchlistController {

    private WatchlistService watchlistService;
    private final Logger logger= LoggerFactory.getLogger(WatchlistController.class);
    @Autowired
    public WatchlistController(WatchlistService watchlistService){
    super();
    this.watchlistService=watchlistService;
    }

    @RequestMapping(value="/watchlist", method=RequestMethod.GET)
    public ModelAndView getList() {
        logger.info("HTTP GET request received at /watchlist URL");
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();
/*
        watchlistItems.clear();
        watchlistItems.add(new WatchlistItem(1, "Lion King","8.5","high","Hakuna matata!"));*/

        model.put("watchlistItems", watchlistService.getWatchlistItems());
        model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());

        return new ModelAndView(viewName , model);
    }

    @RequestMapping(value="/watchlistItemForm", method=RequestMethod.GET)
    public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
        logger.info("HTTP GET request received at /watchlistItemForm URL");
        Map<String,Object> model = new HashMap<String,Object>();
        WatchlistItem watchlistItem=watchlistService.findWatchlistItemById(id);
        if(watchlistItem==null){
            model.put("watchlistItem",new WatchlistItem());
        }else{
            model.put("watchlistItem", watchlistItem);
        }
        return new ModelAndView("watchlistItemForm", model);
    }

    @RequestMapping(value="/watchlistItemForm", method=RequestMethod.POST)
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
        logger.info("HTTP POST request received at /watchlistItemForm URL");
        if(bindingResult.hasErrors()){
            return new ModelAndView("watchlistItemForm");
        }
        try{
            watchlistService.addOrUpdateWatchlistItem(watchlistItem);
        }catch(DuplicateTitleException e){
           bindingResult.rejectValue("title","","This title already exists on your watchlist");
           return new ModelAndView("watchlistItemForm");
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }
}