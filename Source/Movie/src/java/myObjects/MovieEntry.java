package myObjects;
import java.util.ArrayList;


public class MovieEntry 
{
	private int movieID;
	
	private String title;
	private String synopsis;
	private int length;
	private int year;
	
	private ArrayList<String> actors=new ArrayList<String>();
	private ArrayList<String> directors=new ArrayList<String>();
	private ArrayList<String> genres=new ArrayList<String>();
        
        private ArrayList<ReviewEntry> reviews=new ArrayList<ReviewEntry>();
	
	private String picturePath;
	private int numOfVotes;
	private double totalGrade;
	
	public MovieEntry()
	{		
	}
	
	public MovieEntry(String title, String synopsis, int length, int year) 
        {
		super();
		this.title = title;
		this.synopsis = synopsis;
		this.length = length;
		this.year = year;
	}
	
	public void addActor(String ActorName)
	{
		
		actors.add(ActorName);
	}
	
	public void addDirector(String directorName)
	{
		directors.add(directorName);
	}
	
	public void addGenre(String genreName)
	{
		genres.add(genreName);
	}

	public String getTitle() 
        {
		return title;
	}

	public void setTitle(String title) 
        {
		this.title = title;
	}

	public String getSynopsis() 
        {
		return synopsis;
	}

	public void setSynopsis(String synopsis) 
        {
		this.synopsis = synopsis;
	}

	public int getLength() 
        {
		return length;
	}

	public void setLength(int length) 
        {
		this.length = length;
	}

	public int getYear() 
        {
		return year;
	}

	public void setYear(int year) 
        {
		this.year = year;
	}

	public ArrayList <String> getActors() 
        {
		return actors;
	}

	public void setActors(ArrayList <String> actors) 
        {
		this.actors = actors;
	}

	public ArrayList <String> getDirectors() 
        {
		return directors;
	}

	public void setDirectors(ArrayList <String> directors) 
        {
		this.directors = directors;
	}

	public ArrayList <String> getGenres() 
        {
		return genres;
	}

	public void setGenres(ArrayList <String> genres) 
        {
		this.genres = genres;
	}
        
        public ArrayList <ReviewEntry> getReviews() 
        {
                return reviews;
        }
        
        public void setReviews(ArrayList <ReviewEntry> reviews) 
        {
                this.reviews = reviews;
        }

	public String getPicturePath() 
        {
		return picturePath;
	}

	public void setPicturePath(String picturePath) 
        {
		this.picturePath = picturePath;
	}

	public int getNumOfVotes() 
        {
		return numOfVotes;
	}

	public void setNumOfVotes(int numOfVotes) 
        {
		this.numOfVotes = numOfVotes;
	}

	public double getTotalGrade() 
        {
		return totalGrade;
	}

	public void setTotalGrade(double totalGrade) 
        {
		this.totalGrade = totalGrade;
	}

	public int getMovieID() 
        {
		return movieID;
	}

	public void setMovieID(int movieID) 
        {
		this.movieID = movieID;
	}
	
	
	public String toString() 
	{
		return movieID +" "+ title +" "+ length +" "+  year +" "+ picturePath +" "+ numOfVotes +" "+ totalGrade
		    +" "+ actors.toString()+" "+ directors.toString()+" "+ genres.toString();
	}
	
}
