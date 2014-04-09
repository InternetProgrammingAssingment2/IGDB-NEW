package com.igdb.lib;

import java.net.*;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class GameSearch {
	/*public static int ID;
	public static String GameTitle;*/
	
	public LinkedList<GameSearchStore> GetSearchList(String gameName) throws Exception {
		Document xml = GetXML(gameName);
		LinkedList<GameSearchStore> gamesList = new LinkedList<>();
		if (GameExist(xml)) {

			List<Integer> IdList = new ArrayList<Integer>();
			List<String> TitleList = new ArrayList<String>();
			
			Node n;
			Node child;
			
			NodeList nl = xml.getElementsByTagName("id");
			for (int k = 0; k < nl.getLength(); k++) {

				n = nl.item(k);
				// PrintFile(n);
				//gamesList.add(GetValues(n));
					/// fix this!!!!
				
				if (n.getNodeName() == "id") {
					child = n.getFirstChild();
					String val = child.getNodeValue();
					if (val != null) {
						if (val.trim().equals("")) {
							// Whitespace
							System.out.print("[WS]");
						} else {
							IdList.add(Integer.parseInt(child.getNodeValue()));
							System.out.println("ID: \"" + child.getNodeValue()
									+ "\"");
						}
					}
				}
			}
						
				
				NodeList nlTitle = xml.getElementsByTagName("GameTitle");
				for (int i = 0; i < nlTitle.getLength(); i++) {

					n = nlTitle.item(i);
					// PrintFile(n);
					//gamesList.add(GetValues(n));
						/// fix this!!!!
					
					if (n.getNodeName() == "GameTitle") {
						child = n.getFirstChild();
						String val = child.getNodeValue();
						if (val != null) {
							if (val.trim().equals("")) {
								// Whitespace
								System.out.print("[WS]");
							} else {
								TitleList.add(child.getNodeValue());
								System.out.println("Title: \"" + child.getNodeValue()
										+ "\"");
							}
						}
					}
				}		
			
			
			for (int j = 0; j < IdList.size(); j++) {
				GameSearchStore searchStore = new GameSearchStore();
				
				searchStore.setID(IdList.get(j));
				searchStore.setGameTitle(TitleList.get(j));
				
				gamesList.add(searchStore);
			}
			

		}

		System.out.println();
		System.out
				.println("---------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		return gamesList;
		
	}
	
	
	
	private Document GetXML(String name) throws Exception {

		URL url = new URL("http://thegamesdb.net/api/GetGamesList.php?name=" + name);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty("User-Agent", "Mozilla/4.76");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());

		return doc;

	}

	private Boolean GameExist(Document doc) {
		NodeList nl = doc.getElementsByTagName("*");

		if (nl.getLength() > 2 || nl.item(0).getNodeName() == "Error")
			return true;
		else
			return false;

	}
	
	
	public GameSearchStore GetValues(Node node) {
		GameSearchStore searchStore = new GameSearchStore();
		Node child;
		int nType = node.getNodeType();
		switch (nType) {
		case Node.ELEMENT_NODE:

			if (node.getNodeName() == "id") {
				child = node.getFirstChild();
				String val = child.getNodeValue();
				if (val != null) {
					if (val.trim().equals("")) {
						// Whitespace
						System.out.print("[WS]");
					} else {
						searchStore.setID(Integer.parseInt(child.getNodeValue()));
						System.out.println("ID: \"" + child.getNodeValue()
								+ "\"");
					}
				}

			}

			if (node.getNodeName() == "GameTitle") {
				child = node.getFirstChild();

				String val = child.getNodeValue();
				if (val != null) {
					if (val.trim().equals("")) {
						// Whitespace
						System.out.print("[WS]");
					} else {
						searchStore.setGameTitle(child.getNodeValue());
						System.out.println("Title: \"" + child.getNodeValue()
								+ "\"");
					}
				}

			}
		}
		return searchStore;
	}

	
	

}
