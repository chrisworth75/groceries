package chris.groceries.parser;

import chris.groceries.domain.ResultContainer;
import chris.groceries.exception.GroceryParseException;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ParserTest {

   //TODO - Jsoup won't load a file locally
   /*@Test
   public void shouldParseDocumentIntoResultContainer(){

      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("berries-cherries-currants6039.html").getFile());

      ResultContainer resultContainer = null;

      try{
         resultContainer = Parser.parse("file://" + file.getAbsolutePath());
      }
      catch (GroceryParseException e){

      }
      assertEquals(resultContainer.getResults().size(), 3);
   }*/
}
