package com.igdb.lib;

import java.util.*;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;



public class Modle {
	
	Cluster cluster;
	CassandraHosts cass =  new CassandraHosts();
	
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	public void AddToDb(int Id, String GameName) {
		Session session = cluster.connect("igdatabase");
		PreparedStatement statement = session
				.prepare("Insert into gameinfotable (gameid  , gameName ) values ( ? , ?);");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(Id, GameName);

		session.execute(boundStatement);
		session.close();
	}
	
	
	public List<Integer> getDuplicatesId(String GameName) {	
		List<Integer> Idlist = new ArrayList<Integer>();
		Session session = cluster.connect("igdatabase");
		PreparedStatement statement = session
				.prepare("SELECT * FROM gameinfotable WHERE gameName = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);

		boundStatement.bind(GameName);

		
		ResultSet rs = session.execute(boundStatement);
		List<Row> reL = rs.all();
		session.close();
		
		if(reL.size() > 1){
			System.out.println("Got Duplicates!");
			System.out.println("ID: " + reL.get(0).getInt("gameid"));
			for (Row row : reL){
				Idlist.add(row.getInt("gameid"));
			}
			deleteDuplicate(Idlist);
				
		}	
		
		
		return Idlist;
	}
	
	public void deleteDuplicate(List<Integer> idList) {
		Session session = cluster.connect("igdatabase");
		
		
		for (int i = 1; i < idList.size(); i++) {
			PreparedStatement statement = session
					.prepare("DELETE FROM gameinfotable WHERE gameid = ?;");
			BoundStatement boundStatement = new BoundStatement(statement);

			boundStatement.bind(idList.get(i));
			session.execute(boundStatement);
		}
		System.out.println("Deleted Duplicates!");
		
		session.close();

	}
	

}
