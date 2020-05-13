package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
    public ModelAndView showWatchlistItemForm() {

        String viewName = "watchlistItemForm";

        Map<String,Object> model = new HashMap<String,Object>();

        model.put("watchlistItem", new WatchlistItem());

        return new ModelAndView(viewName,model);
    }

    @RequestMapping(value="/watchlistItemForm", method=RequestMethod.POST)
    public ModelAndView submitWatchlistItemForm(WatchlistItem watchlist) {
       watchlist.setId(watchlist.index++);
        watchlistItems.add(watchlist);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }
}