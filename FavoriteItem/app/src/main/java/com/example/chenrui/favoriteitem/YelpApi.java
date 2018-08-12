package com.example.chenrui.favoriteitem;

import android.util.Log;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by chenrui on 2018/8/7.
 */


public class YelpApi {
        private static final String API_HOST = "api.yelp.com";
        private static final String SEARCH_PATH = "/v3/search";

        private static final String CONSUMER_KEY = "REPLACE_WITH_CONSUMER_KEY";
        private static final String CONSUMER_SECRET = "REPLACE_WITH_CONSUMER_SECRET";
        private static final String TOKEN = "REPLACE_WITH_TOKEN";
        private static final String TOKEN_SECRET = "REPLACE_WITH_TOKEN_SECRET";
        YelpFusionApiFactory apiFactory;
        YelpFusionApi yelpFusionApi;

        private OAuthService service;
        private Token accessToken;

        /**
         * Setup the Yelp API OAuth credentials.
         */
        public YelpApi() {
            apiFactory = new YelpFusionApiFactory();
            try{
            yelpFusionApi = apiFactory.createAPI("hdhb4-zuLymHqAtBbSyOCupLyU6t50NLTgiCQChPvU8UXN6t8j9GfubbGw4zu6urwCRYY9C7-FpurIaDUp4O_c1AOybdy_Tj54_4h8Im3sAza7KB4KuRcNMXdAppW3Yx");
            } catch (Exception e) {
                Log.d("error","invalid APIKEY");

            }

        }



    /**
     * Fire a search request to Yelp API.
     */
    public SearchResponse searchForBusinessesByLocation(String term, String location, int searchLimit){
        Map<String,String> params = new HashMap<>();
        params.put("term", term);
        params.put("location", location);
        params.put("limit", String.valueOf(searchLimit));
        Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
        SearchResponse searchResponse = null;
        try{
        searchResponse = call.execute().body();}
        catch(Exception e) {

        }
        Log.d("check",searchResponse.toString());

/*        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(searchLimit));
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        Log.i("message", response.getBody());
        return response.getBody();*/
        return searchResponse;
    }

    public SearchResponse searchForBusinessesByCoordinate(String term, String longitude, String latitude , int searchLimit) {
        Map<String,String> params = new HashMap<>();
        params.put("term", term);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("limit", String.valueOf(searchLimit));
        Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
        SearchResponse searchResponse = null;
        try{
            searchResponse = call.execute().body();}
        catch(Exception e) {

        }
        Log.d("check",searchResponse.toString());
        return searchResponse;
    }
}
