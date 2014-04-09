package com.igdb.models;

import java.util.*;

import com.datastax.driver.core.*;
import com.igdb.stores.GameStore;

public class GameModel {
	
	Cluster cluster;

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	/*public int  getGameId(String GameName) {
		Session session = cluster.connect("IGDataBase");

		int id = 0;
		
		PreparedStatement statement = session
				.prepare("SELECT * from gameinfotable where gamename = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(GameName);
		ResultSet rs = session.execute(boundStatement);
		
		if (!rs.isExhausted()) {
			id = rs.one().getInt("gameid");
		}
		return id;
	}*/
	
	public LinkedList<GameStore> GetTenGames() {			
		LinkedList<GameStore> gameList = new LinkedList<GameStore>();
		Session session = cluster.connect("IGDataBase");

		PreparedStatement statement = session
				.prepare("SELECT * from games LIMIT 10;");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		
		if (rs.isExhausted()) {
			System.out.println("No Games");
		} else {
			for (Row row : rs) {
				GameStore gs = new GameStore();
				gs.setGameId(row.getInt("id"));
				gs.setGameName(row.getString("title"));
				gameList.add(gs);
				System.out.println("one Game returned");
			}
			System.out.println();
		}
		
		session.close();
		return gameList;
		
		
	}
	
	public GameStore GetGameInfo(int ID) throws Exception {
		
		GameStore gs = new GameStore();
		Session session = cluster.connect("IGDataBase");

		PreparedStatement statement = session
				.prepare("SELECT * from games WHERE id= "+ID+";");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		
		if (rs.isExhausted()) {
			System.out.println("No Games");
		} else {
			for (Row row : rs) {
				
				gs.setGameId(row.getInt("id"));
				gs.setGameName(row.getString("title"));
				gs.setPlatforms(row.getSet("platforms", String.class));
				gs.setGenres(row.getSet("genres", String.class));
				gs.setPlayersNo(row.getString("PLAYERS"));
				gs.setCoOP(row.getString("co_op"));
				gs.setDeveloper(row.getString("developer"));
				gs.setPublisher(row.getString("publisher"));
				gs.setRealeaseDate(row.getString("release_date"));
				gs.setGameOverview(row.getString("overview"));
				gs.setYoutubeLink(row.getString("youtube"));
				
				
				
			}
		}
		session.close();
		return gs;
	}
	
	
	public String getGameName(int gameID){
		Session session = cluster.connect("IGDataBase");
		String gameName = "";

		PreparedStatement statement = session
				.prepare("SELECT * FROM games where id = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(gameID);
		ResultSet rs = session.execute(boundStatement);
		
		for (Row row : rs) {
			gameName = row.getString("title");
			
			System.out.println("Got result?");
		}
		session.close();		
		
		return gameName;
	}

}
