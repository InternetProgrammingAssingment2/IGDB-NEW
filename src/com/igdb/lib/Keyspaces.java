package com.igdb.lib;

import com.datastax.driver.core.*;

public final class Keyspaces {

	public Keyspaces() {

	}

	public static void SetUpKeySpaces(Cluster c) {
		try {
			// Add some Keyspaces here
			String createkeyspace = "create keyspace if not exists igdatabase  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
			String CreateUserTable = "CREATE TABLE if not exists userinfotable (userid uuid PRIMARY KEY,firstname text,lastname text,username text,password text,emailaddress ascii,country text);";
			String CreateGamesTable = "CREATE TABLE if not exists games (id int PRIMARY KEY,title text,platforms set < text >,release_date text,overview text,genres set < text >,players text,co_op text,youtube text,publisher text,developer text);";
			String CreateReviewTable = "CREATE TABLE if not exists reviews (id timeuuid PRIMARY KEY,game_id int,user_id uuid,ratings int,review text,date_time timestamp);";
			String CreateUserUserNameIndex = "CREATE INDEX if not exists ON userinfotable(username);";
			String CreateGamesTileIndex = "CREATE INDEX if not exists ON games(title);";
			String CreateReviewsUserIdIndex = "CREATE INDEX if not exists ON reviews(user_id);";
			String CreateReviewsGameIdIndex = "CREATE INDEX if not exists ON reviews(game_id);";


			Session session = c.connect();
			try {
				PreparedStatement statement = session.prepare(createkeyspace);
				BoundStatement boundStatement = new BoundStatement(statement);
				session.execute(boundStatement);

			} catch (Exception et) {
				System.out.println("Can't create keyspace2 " + et);
			}

			// now add some column families
			session.close();
			session = c.connect("tclone");

			try {
				SimpleStatement cqlQuery = new SimpleStatement(CreateUserTable);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateGamesTable);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateReviewTable);
				session.execute(cqlQuery);

				cqlQuery = new SimpleStatement(CreateUserUserNameIndex);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateGamesTileIndex);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateUserUserNameIndex);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateReviewsUserIdIndex);
				session.execute(cqlQuery);
				cqlQuery = new SimpleStatement(CreateReviewsGameIdIndex);
				session.execute(cqlQuery);

				System.out.println("all tables created if not exist");
			} catch (Exception et) {
				System.out.println("Can't create DB " + et);
			}
			session.close();

		} catch (Exception et) {
			System.out.println("Other keyspace or coulm definition error" + et);
		}

	}
}