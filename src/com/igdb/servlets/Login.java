package com.igdb.servlets;

import java.io.IOException;

import com.datastax.driver.core.Cluster;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.igdb.lib.CassandraHosts;
import com.igdb.models.GameModel;
import com.igdb.models.UserModel;
import com.igdb.stores.GameStore;
import com.igdb.stores.UserStore;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Home" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			getGamesInfo(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("Home.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] url = request.getRequestURI().split("/");

		if ((url[(url.length) - 1]).equals("Home")) {
			try {
				userLogin(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void userLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		String userNameTxt = request.getParameter("username");
		String passwordTxt = request.getParameter("password");

		UserModel um = new UserModel();
		um.setCluster(cluster);

		UserStore us = um.GetUser(userNameTxt);

		if (us != null) {
			System.out.println(us.getUsername());
			if (passwordTxt.equals(us.getPassword())) {
				 session.setAttribute("user", us);
				 getGamesInfo(request, response);
				 
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
				System.out.println("Yes!");

			} else {
				System.out.println("check not success - password");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("Index.jsp");
				dispatcher.forward(request, response);
				System.out.println("No!");
			}
		} else {
			System.out.println("check not success - Username");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("Index.jsp");
			dispatcher.forward(request, response);
			System.out.println("No!");
		}

	}
	
	public void getGamesInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GameModel gm = new GameModel();
		gm.setCluster(cluster);
		
		GameStore farCry = gm.GetGameInfo(4419);
		GameStore NFSMW = gm.GetGameInfo(4814);
		GameStore FIFA14 = gm.GetGameInfo(17834);
		
		request.setAttribute("farCry", farCry);
		request.setAttribute("NFS", NFSMW);
		request.setAttribute("FIFA", FIFA14);
		
	}
}
