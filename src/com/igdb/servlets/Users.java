package com.igdb.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.datastax.driver.core.Cluster;
import com.igdb.lib.CassandraHosts;
import com.igdb.models.ReviewModel;
import com.igdb.models.UserModel;
import com.igdb.stores.ReviewStore;
import com.igdb.stores.UserStore;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Profile/*")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Users() {
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
		System.out.println("in the user servlet");
		String[] url = request.getRequestURI().split("/");

		if (url[2].equals("Profile")) {
			showProfile(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void showProfile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] url = request.getRequestURI().split("/");
		String userName = url[(url.length) - 1];

		UserModel um = new UserModel();
		um.setCluster(cluster);
		ReviewModel rm = new ReviewModel();
		rm.setCluster(cluster);

		UserStore user = um.GetUser(userName);
		LinkedList<ReviewStore> ReviewList = rm.getUserReviews(user.getId());
		LinkedList<ReviewStore> SortedReviewList = rm.sortReviews(ReviewList);

		if (um.UserExist(userName)) {
			request.setAttribute("user", user);
			request.setAttribute("reviewList", SortedReviewList);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/Profile.jsp");
			dispatcher.forward(request, response);
		}
	}

}
