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

import com.datastax.driver.core.Cluster;
import com.igdb.lib.CassandraHosts;
import com.igdb.lib.GameSearch;
import com.igdb.lib.GameSearchStore;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Cluster cluster;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LinkedList<GameSearchStore> gamesList = new LinkedList<>();
		GameSearch gameSearch = new GameSearch();
		if (!(request.getParameter("searchTxt").isEmpty()) || request.getParameter("searchTxt") == null) {
			String gameName = request.getParameter("searchTxt");
			try {
				gamesList = gameSearch.GetSearchList(gameName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("SearchList", gamesList);
		RequestDispatcher rd = request.getRequestDispatcher("/SearchList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
