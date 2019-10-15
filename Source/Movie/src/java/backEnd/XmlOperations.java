package backEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.*;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import myObjects.MovieEntry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlOperations {

    /**
     * @param args
     */
    private void ToDB(String FileName) {

        MoviesDataAccess database = null;

        try {
            database = new MoviesDataAccess();
        } catch (Exception e1) {

            e1.printStackTrace();
        }



        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;

            db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FileName));  //"movies.xml"


            Element root = doc.getDocumentElement();



            NodeList movielist = root.getElementsByTagName("movie");


            for (int i = 0; i < movielist.getLength(); i++) {
                MovieEntry movieEntry = new MovieEntry();
                Element movieNode = (Element) movielist.item(i);

                movieEntry.setTitle(movieNode.getElementsByTagName("title").item(0).getTextContent());
                movieEntry.setSynopsis(movieNode.getElementsByTagName("synopsis").item(0).getTextContent());
                movieEntry.setLength(Integer.parseInt(movieNode.getElementsByTagName("length").item(0).getTextContent()));
                movieEntry.setYear(Integer.parseInt(movieNode.getElementsByTagName("year").item(0).getTextContent()));


                NodeList actorsList = movieNode.getElementsByTagName("actor");

                for (int j = 0; j < actorsList.getLength(); j++) {
                    movieEntry.addActor(actorsList.item(j).getTextContent());
                }

                NodeList directorsList = movieNode.getElementsByTagName("director");

                for (int j = 0; j < directorsList.getLength(); j++) {
                    movieEntry.addDirector(directorsList.item(j).getTextContent());
                }

                NodeList genresList = movieNode.getElementsByTagName("genre");

                for (int j = 0; j < genresList.getLength(); j++) {
                    movieEntry.addGenre(genresList.item(j).getTextContent());
                }




                database.addMovie(movieEntry);  //adds the movie to the db




            }

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private boolean validate(String xmlFileName, String xsdFileName) throws SAXException {

        SchemaFactory factory =
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // 2. Compile the schema. 
        // Here the schema is loaded from a java.io.File, but you could use 
        // a java.net.URL or a javax.xml.transform.Source instead.
        File schemaLocation = new File(xsdFileName);
        Schema schema = factory.newSchema(schemaLocation);

        // 3. Get a validator from the schema.
        Validator validator = schema.newValidator();

        // 4. Parse the document you want to check.
        Source source = new StreamSource(xmlFileName);
        

        try 
        {
            validator.validate(source);
            return true;
        }
        
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        return true;

    }
    
    
    
    public String putInDB (String xmlFileName) 
    {
        String result ="a";
        String path ="c:\\MovieSystemXmlData\\";
        
        Properties properties = new Properties();
        
        try 
        {
            properties.load(new FileInputStream("c:\\movieSystem.properties"));
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        String shcemaFileName= properties.getProperty("shcemaFileName");
       
        try 
        {
            validate(path+xmlFileName,path+shcemaFileName);
        }
        
        catch (SAXException ex) 
        {
            result = "can not insert data, "+xmlFileName + " is not valid because" + "\n" + ex.getMessage();
            return result;
        }
         
        ToDB(path+xmlFileName);
         
        result = "data inserted successfully";
        return result;
        
    }
    
}