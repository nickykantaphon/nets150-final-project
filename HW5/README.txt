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
	2.2. Enter '2' to find the recommendations for the user based on the college input.
		- This uses focal and membership closure as a form of recommending which NBA team would be 
		  ideal for the user, based on their college.
		- After pressing '2', you will be prompted to enter the college name. This is case insensitive
		  for your ease of use ("duke" or "Duke" or "DUKE" or even "dUkE" would have the same acceptable result)
		- Essentially, the program finds all the current NBA players listed on ESPN that graduated from
		  the inputed college, and then finds the teams with the most number of these players using the 
		  logic from Social Affiliation Networks.
		- If not found, an error message will pop up stating an invalid input was found. 
	2.3. Enter '3' to find the recommendations for the user based on the hometown input.
	    - The implementation and logic is the same as 2.2 (but for hometowns this time).
	    - The difference here is that we made the search easier and wider by allowing the user to input
	      only the city name or a part of it (if they are unable to remember), or the state abbreviation.
	    - This uses 'contains' instead and is also case insensitive (same logic from above for this part).
	    - After entering '3', please be sure to only input hometowns or state abbreviations.
	    - If not found, an error message will pop up stating an invalid input was found.
3. To see/test a different function, or the same function, simply re-run the program and enter the 
   corresponding number (further instructions commented on ParserMain class).
   
4. Note that the program may take slightly long the first time running. This is because we are scraping data 
   directly from the ESPN website and building the graph, matrix, and relevant lists. 
   To make this process easier when testing, we have provided a txt file with the data loaded, and the
   relevant code to switch to this to save time when testing (see commented code in ParserMain). 
   Feel free to use either approach; they both work equivalently.

Thank you!

Nicole, Nicky, Arnav
