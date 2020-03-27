# HTML to Confluence Wiki Markup

Useful for converting a web page to Confluence markup, for adding to the wiki or a JIRA ticket.

## Requires 
- Java 8 or above

## Build

`mvn clean install`

Builds an executable jar containing necessary dependencies.  You should then be able to move the jar whereever you want to run it.

## Usage

`java -jar target/html-to-confluence-1.0.0-SNAPSHOT.jar infile.html outfile.confluence`

You can also rename the jar file to something less unwieldy.

The output will be echoed to the screen as well as written to a file.  If you omit the output file parameter, the output will be written to a file 
with the same name as the input file, with ".cf" appended to it.

## Credits

Based on [html-to-jira-markup](https://github.com/paul-nelson-baker/html-to-jira-markup) by [Paul Nelson Baker](https://github.com/paul-nelson-baker)
