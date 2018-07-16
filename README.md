# groceries
a command line java app for scraping a portion of the sainsbury's groceries website.

# Installing dependencies

Git, a Java 8 JRE and Maven are all needed to clone, build and run this project.
All other dependencies (Jsoup, Junit, Jackson, Lombok) will be pulled in by Maven.

# Cloning the project, building and running

`git clone https://github.com/chrisworth75/groceries.git`

`cd groceries`

`mvn clean package`

`java -jar target/groceries-1.0-jar-with-dependencies.jar`


# Uses of tools/frameworks

+ Jsoup: To parse the html using css selectors. Useful because if/when the page changes the css selectors can be easily
 modified, and lend themselves to being config values in external config files.
+ Lombok: For convenience to avoid typing boilerplate code such as builders, getters, setters 

# Unfinished work

I ran out of time to use Junit properly, but the plan was to have ParserTest reading the local copy of the main product html.
Once it had parsed the file into a ResultContainer, all the contents could be verified.
ResultContainer could be refactored to have different methods to get each field, and each of those methods could have a test.

The app could allow the url to be specified on the command line.
The app could have debug/info logging. Info level logging would be provide useful feedback everytime a new page is requested,
particularly if the number of products is large and the app is taking more than a few seconds.
