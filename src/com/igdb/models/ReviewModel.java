package com.igdb.models;

import java.util.*;

import com.datastax.driver.core.*;
import com.igdb.stores.ReviewStore;

public class ReviewModel {
	Cluster cluster;

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	public LinkedList<ReviewStore> getGameReviews(int gameId){
		LinkedList<ReviewStore> reviewList = new LinkedList<ReviewStore>();
		
		Session session = cluster.connect("igdatabase");
		
		UserModel um = new  UserModel();
		um.setCluster(cluster);
		GameModel gm = new GameModel();
		gm.setCluster(cluster);
				
		PreparedStatement statement = session.prepare("SELECT * from reviews WHERE game_id = ?");		
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(gameId);
		
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Users returned");
		} else {
			for (Row row : rs) {
				ReviewStore reviewStore = new ReviewStore();
				reviewStore.setId(row.getUUID("id"));
				reviewStore.setGame_id(row.getInt("game_id"));
				reviewStore.setUser_id(row.getUUID("user_id"));
				reviewStore.setRatings(row.getInt("ratings"));
				reviewStore.setReview(row.getString("review"));
				reviewStore.setDate_time(row.getDate("date_time"));
				reviewStore.setUser_name(um.getUserName(reviewStore.getUser_id()));
				reviewStore.setGame_name(gm.getGameName(gameId));
				reviewList.add(reviewStore);
			}
		}
		session.close();
		return reviewList;
		
	}
	
	public void postReview(ReviewStore reviewStore){
		
		Session session = cluster.connect("igdatabase");
		PreparedStatement statement = session.prepare("INSERT INTO reviews (id,game_id,user_id,review,date_time) "
				+ "VALUES (now(), ?, ?, ?, dateof(now()));");		
		BoundStatement boundStatement = new BoundStatement(statement);
		
		String review = reviewStore.getReview().replace("<", "&lt;").replace(">", "&gt;");
		
		boundStatement.bind(reviewStore.getGame_id(),reviewStore.getUser_id(), review);
		session.execute(boundStatement);
		session.close();
	}
	
	
	public LinkedList<ReviewStore> sortReviews(LinkedList<ReviewStore> reviews) {

		LinkedList<ReviewStore> SortedReviews = new LinkedList<>();

		System.out.println("in the Sort Method.");

		Collections.sort(reviews, new Comparator<ReviewStore>() {
			@Override
			public int compare(ReviewStore review1, ReviewStore review2) {
				return review2.getDate_time().compareTo(review1.getDate_time());
			}

		});

		System.out.println(reviews.size());
		for (int i = 0; i < reviews.size(); i++) {
			ReviewStore tmpReview = reviews.get(i);
			System.out.println(tmpReview.getReview());
			SortedReviews.add(tmpReview);
			// System.out.println(SortedTweets.get(i).getTweetBody());
		}

		System.out.println("in the for loop");
		// System.out.println(Arrays.toString(SortedTweets.toArray()));

		return SortedReviews;
	}
	
	public LinkedList<ReviewStore> getUserReviews(UUID userId){
		LinkedList<ReviewStore> reviewList = new LinkedList<ReviewStore>();
		
		Session session = cluster.connect("igdatabase");
		
		UserModel um = new  UserModel();
		um.setCluster(cluster);
		GameModel gm = new GameModel();
		gm.setCluster(cluster);
		
		PreparedStatement statement = session.prepare("SELECT * from reviews WHERE user_id = ?");		
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(userId);
		
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Users returned");
		} else {
			for (Row row : rs) {
				ReviewStore reviewStore = new ReviewStore();
				reviewStore.setId(row.getUUID("id"));
				reviewStore.setGame_id(row.getInt("game_id"));
				reviewStore.setUser_id(row.getUUID("user_id"));
				reviewStore.setRatings(row.getInt("ratings"));
				reviewStore.setReview(row.getString("review"));
				reviewStore.setDate_time(row.getDate("date_time"));
				reviewStore.setUser_name(um.getUserName(userId));
				reviewStore.setGame_name(gm.getGameName(reviewStore.getGame_id()));
				reviewList.add(reviewStore);
			}
		}
		session.close();
		return reviewList;
		
	}
	

}
