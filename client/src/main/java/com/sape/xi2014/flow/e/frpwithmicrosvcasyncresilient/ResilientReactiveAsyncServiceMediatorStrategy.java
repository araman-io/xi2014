package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub.ResilentProductImageClient;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub.ResilentReviewServiceClient;
import com.sape.xi2014.service.ServiceMediator;

public class ResilientReactiveAsyncServiceMediatorStrategy implements ServiceMediator {

	ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();

	public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
		final long startTime = System.currentTimeMillis();

		Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);
		ClientResponse response = new ClientResponse();

		Observable<Tile> mergedTile = searchTile.flatMap(t -> {
			// Invoke the Hystrix wrapped review service client directly
				Observable<Reviews> reviews = new ResilentReviewServiceClient().observe().subscribeOn(Schedulers.io());
				
				//Another mode of invoking it
				/*
				 * Observable<Reviews> reviews = new
				 * ResilentReviewServiceClient().observe();
				 * reviews.subscribe(new Observer<Reviews>() {
				 * 
				 * @Override public void onCompleted() { // nothing needed here
				 * }
				 * 
				 * @Override public void onError(Throwable e) {
				 * e.printStackTrace(); }
				 * 
				 * @Override public void onNext(Reviews v) {
				 * System.out.println("onNext: " + v); }
				 * 
				 * });
				 */

				// Invoke the Hystrix wrapped product image service client
				Observable<String> imageUrl = new ResilentProductImageClient(t.getProductId()).observe().subscribeOn(
						Schedulers.io());

				return Observable.zip(reviews, imageUrl, (r, u) -> {
					return new Tile(t, r, u);
				});
			});

		List<Tile> single = mergedTile.doOnCompleted(() -> System.out.println("All Tiles Completed " + startTime))
				.toList().toBlocking().single();

		response.setTiles(new Tiles(single));

		return response;

	}

	public static void main(String[] args) throws Exception {
		ClientResponse searchResults = new ResilientReactiveAsyncServiceMediatorStrategy()
				.getAggregatedResponse("shoes");
		System.out.println(new Gson().toJson(searchResults));
	}

}