package com.igdb.models;

import java.util.UUID;

import com.datastax.driver.core.*;
import com.igdb.stores.UserStore;

public class UserModel {

	Cluster cluster;

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public Boolean UserExist(String Username) {
		Session session = cluster.connect("IGDataBase");

		PreparedStatement statement = session
				.prepare("SELECT username from userinfotable where username = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(Username);
		ResultSet rs = session.execute(boundStatement);

		if (rs.isExhausted()) {
			session.close();
			return false;
		} else {
			session.close();
			return true;
		}
		
	}
	
	public UserStore GetUser(String Username){
		if(UserExist(Username)){
			Session session = cluster.connect("IGDataBase");
			UserStore userStore = new UserStore();

			PreparedStatement statement = session
					.prepare("SELECT * FROM userinfotable where username = ?;");
			BoundStatement boundStatement = new BoundStatement(statement);
			boundStatement = boundStatement.bind(Username);
			ResultSet rs = session.execute(boundStatement);
			
			for (Row row : rs) {
				userStore.setId(row.getUUID("userid"));
				userStore.setCountry(row.getString("country"));
				userStore.setEmail(row.getString("emailaddress"));
				userStore.setFirstName(row.getString("firstname"));
				userStore.setLastName(row.getString("lastname"));
				userStore.setPassword(row.getString("password"));
				userStore.setUsername(row.getString("username"));
				
				System.out.println("Got result?");
			}
			session.close();		
			
			return userStore;
		}
		else{
			System.out.println("null?");
			return null;
		}
	}
	
	
	public String getUserName(UUID id){
		Session session = cluster.connect("IGDataBase");
		String userName = "";

		PreparedStatement statement = session
				.prepare("SELECT * FROM userinfotable where userid = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement = boundStatement.bind(id);
		ResultSet rs = session.execute(boundStatement);
		
		for (Row row : rs) {
			userName = row.getString("username");
			
			System.out.println("Got result?");
		}
		session.close();		
		
		return userName;
	}
}
