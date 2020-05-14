package com.openclassrooms.watchlist;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingService;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

    @Mock
    private WatchlistRepository watchlistRepositoryMock;

    @Mock
    private MovieRatingService movieRatingServiceMock;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository(){

        //Arrange
        WatchlistItem item1=new WatchlistItem(1,"Star Wars","7.7","M","");
        WatchlistItem item2=new WatchlistItem(2,"Star Treck","8.8","M","");
        List<WatchlistItem> mockItems= Arrays.asList(item1,item2);
        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result=watchlistService.getWatchlistItems();

        //Assert
        Assert.assertTrue(result.size()==2);
        Assert.assertTrue(result.get(0).getTitle().equals("Star Wars"));
        Assert.assertTrue(result.get(1).getTitle().equals("Star Treck"));
    }

    @Test
    public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem(1,"Star Wars", "7.7", "M" , "");
        List<WatchlistItem> mockItems = Arrays.asList(item1);

        Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        Mockito.when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");

        //Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        //Assert
        Assert.assertTrue(result.get(0).getRating().equals("10"));
    }
}
