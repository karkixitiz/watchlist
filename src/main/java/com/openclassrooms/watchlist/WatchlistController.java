package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();

    @RequestMapping(value="/watchlist", method=RequestMethod.GET)
    public ModelAndView getList() {

        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();
/*
        watchlistItems.clear();
        watchlistItems.add(new WatchlistItem(1, "Lion King","8.5","high","Hakuna matata!"));
        watchlistItems.add(new WatchlistItem(2, "Frozen","7.5","medium","Let it go!"));
        watchlistItems.add(new WatchlistItem(3, "Cars","7.1","low","Go go go!"));
        watchlistItems.add(new WatchlistItem(4, "Wall-E","8.4","high","You are crying!"));*/

        model.put("watchlistItems", watchlistItems);
        model.put("numberOfMovies", watchlistItems.size());

        return new ModelAndView(viewName , model);
    }
    @RequestMapping(value="/watchlistItemForm", method=RequestMethod.GET)
    public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
        Map<String,Object> model = new HashMap<String,Object>();
        WatchlistItem watchlistItem=findWatchlistItemById(id);
        if(watchlistItem==null){
            model.put("watchlistItem",new WatchlistItem());
        }else{
            model.put("watchlistItem", watchlistItem);
        }
        return new ModelAndView("watchlistItemForm", model);
    }

    private WatchlistItem findWatchlistItemById(Integer id) {
        for(WatchlistItem watchlistItem:watchlistItems){
            if(watchlistItem.getId().equals(id)){
                return  watchlistItem;
            }
        }
        return null;
    }

    @RequestMapping(value="/watchlistItemForm", method=RequestMethod.POST)
    public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ModelAndView("watchlistItemForm");
        }
        WatchlistItem existingItem=findWatchlistItemById(watchlistItem.getId());
        if(existingItem==null){
            if(itemAlreadyExists(watchlistItem.getTitle())){
                bindingResult.rejectValue("title","","This title already exists on your watchlist");
                return new ModelAndView("watchlistItemForm");
            }
            watchlistItem.setId(watchlistItem.index++);
            watchlistItems.add(watchlistItem);
        }else{
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }
    private boolean itemAlreadyExists(String title){
        for(WatchlistItem watchlistItem:watchlistItems){
            if(watchlistItem.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }
}