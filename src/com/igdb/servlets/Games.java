package com.igdb.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.igdb.lib.CassandraHosts;
import com.igdb.models.GameModel;
import com.igdb.models.ReviewModel;
import com.igdb.stores.GameStore;
import com.igdb.stores.ReviewStore;
import com.igdb.stores.UserStore;

/**
 * Servlet implementation class Games
 */
@WebServlet({"/Games/*","/Game/*"})
public class Games extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Games() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURI().split("/");
		
		if((url[url.length - 1]).equals("Games")){
			GamesList(request, response);
			
		}
		
		if(url[2].equals("Game")){
			try {
				GameOverview(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserStore loggedUser = (UserStore)session.getAttribute("user");
		
		String[] url = request.getRequestURI().split("/");
		int GameID = Integer.parseInt(url[(url.length)-1]);		
		
		if (!(request.getParameter("review").isEmpty()) || request.getParameter("review") == null) {
			ReviewModel rm = new ReviewModel();
			rm.setCluster(cluster);
			ReviewStore  reviewStore = new ReviewStore();
			reviewStore.setGame_id(GameID);
			reviewStore.setUser_id(loggedUser.getId());
			//reviewStore.setRatings(ratings);
			reviewStore.setReview(request.getParameter("review"));
			
			rm.postReview(reviewStore);		
		}
		this.doGet(request, response);
	}

	public void GamesList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GameModel gm = new GameModel();
		gm.setCluster(cluster);

		LinkedList<GameStore> GamesList = gm.GetTenGames();

		request.setAttribute("TenGames", GamesList);
		RequestDispatcher rd = request.getRequestDispatcher("/GamesList.jsp");
		rd.forward(request, response);

	}

	public void GameOverview( HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			Exception {
		
		GameModel gm = new GameModel();
		gm.setCluster(cluster);
		ReviewModel rm = new ReviewModel();
		rm.setCluster(cluster);
		
		String[] url = request.getRequestURI().split("/");
		int GameID = Integer.parseInt(url[(url.length)-1]);		

		GameStore gs = gm.GetGameInfo(GameID);
		LinkedList<ReviewStore> ReviewList = rm.getGameReviews(GameID);
		LinkedList<ReviewStore> SortedReviewList = rm.sortReviews(ReviewList);

		request.setAttribute("game", gs);
		request.setAttribute("reviewList", SortedReviewList);
				
		System.out.println("Set the Bean");
		RequestDispatcher rd = request.getRequestDispatcher("/Game.jsp");
		rd.forward(request, response);

	}

}
