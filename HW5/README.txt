Class Descriptions

ESPNParser - This class takes care of all scraping data from ESPN using JSoup.

Player - The players' information is stored as an object of this class.

DataExtraction - This class extracts data from ESPN by creating an object of ESPNParser 
				 class to scrape data from ESPN. After extracting data, this class also 
				 constructs a graph in the adjacency matrix form and two associated lists 
				 containing name and type of the nodes, and saves the data to a .txt file. 
				 The data can also be loaded from an existing .txt file.
BFSConnections - This class implements the functions needed to find the shortest number of
				 links between two players using BFS. 
				 
Recommendation - This class implements the functions needed for a user to find out which NBA 
				 team would best match their input (hometown or college), based off of membership 
				 and focal closure, from Social Affiliation Networks. The core idea revolves 
				 around finding the maximum number of players that match the type input, and 
				 outputting the corresponding team with the most players (and the players).
				 
ParserMain - This is the main Class where users can interact with a graph by scraping data from ESPN
			 or loading data previously scraped from ESPN to find the shortest number of links
			 between two players or give team recommendations based on a college or hometown.
			 