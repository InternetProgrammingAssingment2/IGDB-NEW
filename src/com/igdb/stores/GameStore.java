package com.igdb.stores;

import java.util.*;

public class GameStore {
	int gameId;
	String gameName;
	Set<String> Platforms;
	String RealeaseDate;
	String GameOverview;
	Set<String> Genres;
	String PlayersNo;
	String CoOP;
	String YoutubeLink;
	String Publisher;
	String Developer;
	
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Set<String> getPlatforms() {
		return Platforms;
	}
	public void setPlatforms(Set<String> platforms) {
		Platforms = platforms;
	}
	public String getRealeaseDate() {
		return RealeaseDate;
	}
	public void setRealeaseDate(String releseDate) {
		RealeaseDate = releseDate;
	}
	public String getGameOverview() {
		return GameOverview;
	}
	public void setGameOverview(String gameOverview) {
		GameOverview = gameOverview;
	}
	public Set<String> getGenres() {
		return Genres;
	}
	public void setGenres(Set<String> genres) {
		Genres = genres;
	}
	public String getPlayersNo() {
		return PlayersNo;
	}
	public void setPlayersNo(String playersNo) {
		PlayersNo = playersNo;
	}
	public String getCoOP() {
		return CoOP;
	}
	public void setCoOP(String coOP) {
		CoOP = coOP;
	}
	public String getYoutubeLink() {
		return YoutubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		YoutubeLink = youtubeLink;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getDeveloper() {
		return Developer;
	}
	public void setDeveloper(String developer) {
		Developer = developer;
	}
	
	

}
