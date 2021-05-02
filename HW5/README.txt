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
			 
How to use the program?
1. Run the program on Eclipse.
2. The program will prompt you to enter '1', '2', or '3' based on the functions you want to explore.
	2.1. Enter '1' to find smallest number of connections between two players.
		- This is comparable to the Bacon numbers we explored in class.
		- Any two players are connected/adjacent if they are currently in the same team, went to 
		the same college, or have the same hometown.
		- You will be prompted to type the name of two players that you want to see the degree
		of separation or the smallest number of connections.
		- The input is case insensitive. You don't have to worry about letter cases. For example,
		"LeBron James", "Lebron James", or "lebron james" are all acceptable.
	2.2. 

			 
