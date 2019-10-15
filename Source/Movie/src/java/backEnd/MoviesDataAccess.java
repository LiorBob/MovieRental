package backEnd;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.util.Properties;
import myObjects.MovieEntry;
import myObjects.ReviewEntry;
import myObjects.UserEntry;
import myObjects.RentEntry;


public class MoviesDataAccess 
{
        //used PreparedStatements
	private Connection connection;
	
	private PreparedStatement sqlMovieID;
        private PreparedStatement sqlFindMovie;
        private PreparedStatement sqlInsertMovie;
        
        private PreparedStatement sqlFindAllTitles;
        private PreparedStatement sqlFindAllEmails;
        private PreparedStatement sqlFindAllRentedCopyIDs;

	private PreparedStatement sqlActorID;
	private PreparedStatement sqlFindActor;
	private PreparedStatement sqlInsertActor;
	
	private PreparedStatement sqlDirectorID;
	private PreparedStatement sqlFindDirector;
	private PreparedStatement sqlInsertDirector;

	private PreparedStatement sqlGenreID;
	private PreparedStatement sqlFindGenre;
	private PreparedStatement sqlInsertGenre;
	
	private PreparedStatement sqlInsertMovieByActors;

	private PreparedStatement sqlInsertMovieByDirectors;
	
	private PreparedStatement sqlInsertMovieByGenres;
	
	private PreparedStatement sqlFindMovieByID;
	private PreparedStatement sqlActorsByMovieId;	
	private PreparedStatement sqlDirectorsByMovieId;
	private PreparedStatement sqlGenresByMovieId;
        private PreparedStatement sqlReviewsByMovieId;
	
	
	private PreparedStatement sqlFindMoviesIDByGenreID;
	private PreparedStatement sqlFindMoviesIDByActorID;	
	private PreparedStatement sqlFindMoviesIDByDirectorID;
	
	private PreparedStatement sqlInsertCopy;
	private PreparedStatement sqlFindAvailableCopy;
	private PreparedStatement sqlUpdateRentedCopy;
	private PreparedStatement sqlAddRented;
	private PreparedStatement sqlDeleteRented;
	private PreparedStatement sqlInsertReview;
	private PreparedStatement sqlUpdateMovieGrade;
	private PreparedStatement sqlInsertUser;
	private PreparedStatement sqlIsEmailExists;
	private PreparedStatement sqlSetUserManger;
	private PreparedStatement sqlGetPassword;
	private PreparedStatement sqlIsUserManger;
        
        private PreparedStatement sqlGetUser;
        private PreparedStatement sqlGetRents;
        
        private PreparedStatement sqlFindMovieIDByCopyID;
        
        private PreparedStatement sqlDidGiveReview;
        private PreparedStatement sqlAdvancedSearch;
        
        private PreparedStatement sqlGetMoviesWithNoPicture;
        private PreparedStatement sqlAddPicture;

	public MoviesDataAccess() throws Exception
	{

		connect();


		sqlInsertMovie = connection.prepareStatement(
				"INSERT INTO movies (title, synopsis, year, length) " +
		"VALUES ( ? , ? , ?, ?)" );
		sqlMovieID =  connection.prepareStatement(
		"SELECT MAX(movieID) FROM movies");

                sqlFindMovie = connection.prepareStatement(
		"SELECT movieID FROM movies WHERE title= ? ");
                
                sqlFindAllTitles = connection.prepareStatement(
                "SELECT title FROM movies ORDER BY title");
                
                sqlFindAllEmails = connection.prepareStatement(
                "SELECT eMail FROM users ORDER BY eMail");
                
                sqlFindAllRentedCopyIDs = connection.prepareStatement(
                "SELECT copyId FROM rented ORDER BY copyId");
                
                
		sqlActorID = connection.prepareStatement(
		"SELECT MAX(actorID) FROM actors");

		sqlFindActor = connection.prepareStatement(
		"SELECT actorID FROM actors WHERE actorName= ? ");

		sqlInsertActor= connection.prepareStatement(
				"INSERT INTO actors (actorName) " +
		"VALUES (?)" );  
		
		
		sqlDirectorID = connection.prepareStatement(
		"SELECT MAX(directorID) FROM directors");

		sqlFindDirector = connection.prepareStatement(
		"SELECT directorID FROM directors WHERE directorName= ? ");

		sqlInsertDirector= connection.prepareStatement(
				"INSERT INTO directors (directorName) " +
		"VALUES (?)" );  
		
		
		sqlGenreID = connection.prepareStatement(
		"SELECT MAX(genreID) FROM genres");

		sqlFindGenre = connection.prepareStatement(
		"SELECT genreID FROM genres WHERE genreName= ? ");

		sqlInsertGenre= connection.prepareStatement(
				"INSERT INTO genres (genreName) " +
		"VALUES (?)" );  
                
		sqlInsertMovieByActors = connection.prepareStatement(
				"INSERT INTO movieByActors (movieID,actorID) " +
		"VALUES (?,?)" );  	
		
		sqlInsertMovieByDirectors = connection.prepareStatement(
				"INSERT INTO movieByDirectors (movieID,directorID) " +
		"VALUES (?,?)" );  	
		
		sqlInsertMovieByGenres = connection.prepareStatement(
				"INSERT INTO movieByGenres (movieID,genreID) " +
		"VALUES (?,?)" );  	
		
		sqlFindMovieByID =  connection.prepareStatement(
				"select * from movies where movieID=?");
		
		
		sqlActorsByMovieId =  connection.prepareStatement("SELECT actors.actorName "+
				"FROM actors, moviebyactors "+
				"WHERE moviebyactors.actorID=actors.actorID "+
				"AND moviebyactors.movieID= ? ");
		
		sqlDirectorsByMovieId =  connection.prepareStatement("SELECT directors.directorName "+
				"FROM directors, moviebydirectors "+
				"WHERE moviebydirectors.directorID=directors.directorID "+
				"AND moviebydirectors.movieID= ? ");
		
		sqlGenresByMovieId =  connection.prepareStatement("SELECT genres.genreName "+
				"FROM genres, moviebygenres "+
				"WHERE moviebygenres.genreID=genres.genreID "+
				"AND moviebygenres.movieID= ? ");
                
                sqlReviewsByMovieId = connection.prepareStatement("SELECT * FROM reviews WHERE movieID= ? ");

                
		sqlFindMoviesIDByGenreID = connection.prepareStatement("SELECT movieId FROM " +
				"moviebygenres WHERE genreID= ? ");
		
		sqlFindMoviesIDByActorID = connection.prepareStatement("SELECT movieId FROM " +
		"moviebyactors WHERE actorID= ? ");
        
		sqlFindMoviesIDByDirectorID = connection.prepareStatement("SELECT movieId FROM " +
		"moviebydirectors WHERE directorID= ? ");
		
		
		
		sqlInsertCopy = connection.prepareStatement("insert into copy " +
				"(movieID,technologyID) values (?,?)");
		
		sqlFindAvailableCopy =connection.prepareStatement("SELECT copyId FROM " +
		"copy WHERE movieID= ? AND technologyID =? AND isRented=0");
		
		sqlUpdateRentedCopy = connection.prepareStatement("UPDATE copy SET isRented = ? " +
				"WHERE copyID = ?");
		
		sqlAddRented =   connection.prepareStatement("insert into rented " +
				"(userID, CopyID, rentTime) values (?,?,?)"); 
		
		
		sqlDeleteRented = connection.prepareStatement("DELETE FROM rented " +
		"WHERE copyID=?"); 

          sqlInsertReview = connection.prepareStatement("insert into reviews " +
        		"(movieID, UserId, Headline, Text, Rating) values (?,?,?,?,?)");
        
          sqlUpdateMovieGrade = connection.prepareStatement("UPDATE movies SET numOfVotes = ? " +
		",totalGrade = ? WHERE movieID = ?");
        
          sqlInsertUser = connection.prepareStatement("insert into users(eMail,password," +
        		"firstName,lastName,address,phoneNumber) values (?,?,?,?,?,?)");
        
          sqlIsEmailExists =  connection.prepareStatement("Select userId from " +
        		"users where email=?");
          sqlIsUserManger =  connection.prepareStatement("select isManger from " +
        		"users where eMail=?");
        
          sqlSetUserManger = connection.prepareStatement("update users set " +
        		"isManger='1' where eMail=?");
        
          sqlGetPassword = connection.prepareStatement("select password from " +
        		"users where email=?");
        
          sqlGetUser = connection.prepareStatement("select * from " +
        		"users where email=?");
        
          sqlGetRents = connection.prepareStatement("select * from " +
        		"rented where UserID=?");
        
          sqlFindMovieIDByCopyID = connection.prepareStatement("select movieID from " +
        		"copy where copyID=?");
        
          sqlDidGiveReview = connection.prepareStatement("SELECT reviewID FROM reviews WHERE  movieID= ? And userID=?");
        
          sqlAdvancedSearch = connection.prepareStatement("SELECT distinct movies.movieid  FROM movies join movieByactors join actors join movieBydirectors join directors on movies.movieid = movieByactors.movieid " +
                "and movieByactors.actorid=actors.actorid and movies.movieid = movieBydirectors.movieid and  " +
                "movieBydirectors.directorid = directors.directorid WHERE title like ?  or actorname like ? or directorname like ?"); 
          
          sqlGetMoviesWithNoPicture = connection.prepareStatement("select * from movies where picturePath = ?");
          sqlAddPicture = connection.prepareStatement("update movies set picturePath = ? where movieID = ?");
        }

        
        private void connect() throws Exception
	{

            Properties properties = new Properties();
            
            try 
            {
                properties.load(new FileInputStream("c:\\movieSystem.properties"));
            } 
            
            catch (IOException e) 
            {
                e.printStackTrace();
            }
                     
            String driver = properties.getProperty("jdbcdriver");

            String url = properties.getProperty("DBurl");

            Class.forName( driver );
                  
            String user = properties.getProperty("DBuser");
            String password = properties.getProperty("DBpassword");
                    
            connection = DriverManager.getConnection(url,user,password); 
		
	}    


	public boolean addMovie(MovieEntry movie)
	{
            // insert person in database
		
            int movieID=0; 
            
            // insert first and last name in names table
            try 
            {
                sqlInsertMovie.setString(1, movie.getTitle());		
		sqlInsertMovie.setString(2, movie.getSynopsis());
		sqlInsertMovie.setInt(3, movie.getYear());
		sqlInsertMovie.setInt(4, movie.getLength());

                sqlInsertMovie.executeUpdate();

		ResultSet  resultMovieID =  sqlMovieID.executeQuery();

		if ( resultMovieID.next() ) 
                    movieID =  resultMovieID.getInt( 1 );

		//handle actors insert
		ArrayList actorsList = movie.getActors();
                
		for (int i=0;i<actorsList.size();i++)
		{
                    String actorName=(String) actorsList.get(i); 
                    int actorID=0;

                    sqlFindActor.setString(1, actorName);
                    ResultSet  resultActorID =  sqlFindActor.executeQuery();

                    if (resultActorID.next() )   
                    {
                        actorID =  resultActorID.getInt( 1 );
                    }
				
                    if (actorID==0)     //the actor is not in the DB, needs to be added 
                    {                   
                        sqlInsertActor.setString(1, actorName);
                        sqlInsertActor.executeUpdate();
					
                        resultActorID = sqlActorID.executeQuery();
					
			if ( resultActorID.next() ) 
			{
                            actorID =  resultActorID.getInt( 1 );
			}					
                    }
								
                    //we have the movieID and the actorID, now we insert into acted 
                    sqlInsertMovieByActors.setInt(1, movieID);
                    sqlInsertMovieByActors.setInt(2, actorID);
                    sqlInsertMovieByActors.executeUpdate();
		
		}
			
		//handle directors insert
		ArrayList directorsList = movie.getDirectors();
		
                for (int i=0;i<directorsList.size();i++)
		{
                    String directorName=(String) directorsList.get(i); 
                    int directorID=0;

                    sqlFindDirector.setString(1, directorName);
                    ResultSet  resultdirectorID =  sqlFindDirector.executeQuery();

                    if (resultdirectorID.next() )   
                    {
                        directorID =  resultdirectorID.getInt( 1 );
                    }
				
                    if (directorID==0)     //the director is not in the DB, needs to be added 
                    {                   
                        sqlInsertDirector.setString(1, directorName);
			sqlInsertDirector.executeUpdate();
					
			resultdirectorID = sqlDirectorID.executeQuery();
					
			if ( resultdirectorID.next() ) 
			{
                            directorID =  resultdirectorID.getInt( 1 );
			}					
                    }
								
                    //we have the movieID and the directorID, now we insert into acted 
                    sqlInsertMovieByDirectors.setInt(1, movieID);
                    sqlInsertMovieByDirectors.setInt(2, directorID);
                    sqlInsertMovieByDirectors.executeUpdate();

		}


		//handle genres insert
		ArrayList genresList = movie.getGenres();
                
		for (int i=0;i<genresList.size();i++)
		{
                    String genreName=(String) genresList.get(i); 
                    int genreID=0;

                    sqlFindGenre.setString(1, genreName);
                    ResultSet  resultgenreID =  sqlFindGenre.executeQuery();

                    if (resultgenreID.next() )   
                    {
                        genreID =  resultgenreID.getInt( 1 );
                    }
				
                    if (genreID==0)     //the genre is not in the DB, needs to be added 
                    {                   
                        sqlInsertGenre.setString(1, genreName);
			sqlInsertGenre.executeUpdate();
					
			resultgenreID = sqlGenreID.executeQuery();
					
			if ( resultgenreID.next() ) 
			{
                            genreID =  resultgenreID.getInt( 1 );
			}					
                    }
								
                    //we have the movieID and the genreID, now we insert into acted 
                    sqlInsertMovieByGenres.setInt(1, movieID);
                    sqlInsertMovieByGenres.setInt(2, genreID);
                    sqlInsertMovieByGenres.executeUpdate();

		}
			
                return true; //movie added
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
		return false;
            }
	}

        
	public MovieEntry getMovieByID (int movieID)
	{
            MovieEntry movieEntry= new MovieEntry();
		
            try 
            {
                sqlFindMovieByID.setInt(1, movieID);
		ResultSet rs = sqlFindMovieByID.executeQuery();
		
                if ( !rs.next() )  //no movie was found
                    return null;
	         
                movieEntry.setMovieID(rs.getInt(1));
	        movieEntry.setTitle(rs.getString(2));
	        movieEntry.setSynopsis(rs.getString(3));
	        movieEntry.setYear(rs.getInt(4));
	        movieEntry.setLength(rs.getInt(5));
	        movieEntry.setPicturePath(rs.getString(6));
	        movieEntry.setNumOfVotes(rs.getInt(7));
	        movieEntry.setTotalGrade(rs.getDouble(8));

	        sqlActorsByMovieId.setInt(1, movieID);
	        rs = sqlActorsByMovieId.executeQuery();
	         
	        while(rs.next()) 
	        {
                    movieEntry.addActor(rs.getString(1));
	        }

                 
	        sqlDirectorsByMovieId.setInt(1, movieID);
	        rs = sqlDirectorsByMovieId.executeQuery();
	        while(rs.next()) 
                {     	 
                    movieEntry.addDirector(rs.getString(1));
        	}
	         
	        
	        sqlGenresByMovieId.setInt(1, movieID);
	        rs = sqlGenresByMovieId.executeQuery();
	         
	        while(rs.next()) 
                {     	 
                    movieEntry.addGenre(rs.getString(1));
        	}
	         
	         
	        sqlReviewsByMovieId.setInt(1, movieID);
                rs =  sqlReviewsByMovieId.executeQuery();
                 
                ArrayList<ReviewEntry> reviews = new ArrayList<ReviewEntry>();
                 
                while(rs.next()) 
                {
                    reviews.add(new ReviewEntry(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6)));      	 
                }
                 
                movieEntry.setReviews(reviews);
		
            } 
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
            }
	   
            return movieEntry;
	}
	
	
	
	public ArrayList<MovieEntry> findMoviesByGenreID (int genreID)
	{
            ArrayList<MovieEntry> movies=new ArrayList<MovieEntry>();
		 
            try 
            {
                sqlFindMoviesIDByGenreID.setInt(1, genreID);
		ResultSet rs = sqlFindMoviesIDByGenreID.executeQuery();
			
		while(rs.next()) 
                {
                    movies.add(getMovieByID(rs.getInt(1)));      	 
                }
		 
            } 
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
            }
		
            return movies;
	}

        
	public ArrayList<MovieEntry> findMoviesByActorID (int actorID)
	{
            ArrayList<MovieEntry> movies=new ArrayList<MovieEntry>();
		 
            try 
            {
                sqlFindMoviesIDByActorID.setInt(1, actorID);
		ResultSet rs = sqlFindMoviesIDByActorID.executeQuery();
			
		while(rs.next()) 
                {
                    movies.add(getMovieByID(rs.getInt(1)));      	 
                }
		 
		System.out.println(movies.size());
		System.out.println(movies.get(0).toString());
		 
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return movies;
	}
	
        
	public ArrayList<MovieEntry> findMoviesByDirectorID (int directorID)
	{
            ArrayList<MovieEntry> movies=new ArrayList<MovieEntry>();
		 
            try
            {
                sqlFindMoviesIDByDirectorID.setInt(1, directorID);
		ResultSet rs = sqlFindMoviesIDByDirectorID.executeQuery();
		 
		while(rs.next()) 
                {
                    movies.add(getMovieByID(rs.getInt(1)));      	 
                }
		 
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return movies;
	}

	//ALTER TABLE copy AUTO_INCREMENT = 3000; <- use it!
	//used by mangers to add copies of a movie
	public boolean addCopies(int movieId, int technologyID, int numOfCopiesToAdd)
	{
		
            try 
            {
                sqlInsertCopy.setInt(1, movieId);
		sqlInsertCopy.setInt(2, technologyID);
                
		for (int i=0;i<numOfCopiesToAdd;i++)
		{		
			sqlInsertCopy.executeUpdate();
		}
		
		return true;
                
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
		return false;
            }
		
	}

	
	public boolean checkAvailableCopy (int movieId, int technologyID)
	{
            try
            {
                sqlFindAvailableCopy.setInt(1, movieId);
		sqlFindAvailableCopy.setInt(2, technologyID);
		
		ResultSet rs = sqlFindAvailableCopy.executeQuery();
                
		if (rs.next()) 
                {
                    return true;				
                }	
			
		return false;
                
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
		return false; //no available copy
            }
	}
	
	
	public int getAndRentAvailableCopy (int movieId, int technologyID, int userID)
	{
            int copyID=0;
		
            try 
            {
                sqlFindAvailableCopy.setInt(1, movieId);
		sqlFindAvailableCopy.setInt(2, technologyID);
		
		ResultSet rs = sqlFindAvailableCopy.executeQuery();
		if (rs.next()) 
                {
                    copyID=rs.getInt(1);     	 
                }
                
		System.out.println(copyID);
			
		if (copyID!=0)
		{
                    rentCopy(copyID,userID);
		}
			
		return copyID; 
                
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
		return 0; //no available copy
            }
		
	}
	
	
	//getAndRentAvailableCopy uses this method
	private void rentCopy (int copyID, int userID) throws SQLException
	{
            connection.setAutoCommit( false );  //start tranaction
                
            sqlUpdateRentedCopy.setBoolean(1, true);
            sqlUpdateRentedCopy.setInt(2, copyID);
		
            sqlUpdateRentedCopy.executeUpdate();  //the copy rented flag is true now
		
            sqlAddRented.setInt(1, userID);
            sqlAddRented.setInt(2, copyID);
		
            GregorianCalendar gc = new GregorianCalendar();
		
            java.util.Date now =new java.util.Date();
            gc.setTime(now);
            gc.getTimeInMillis();
		
            sqlAddRented.setLong(3, gc.getTimeInMillis());
            sqlAddRented.executeUpdate();    //added to rented table
                
            connection.commit();
            connection.setAutoCommit( true );  //end tranaction
	}
	
	
        public void returnCopy(int copyID)
	{
            try 
            {
                sqlUpdateRentedCopy.setBoolean(1, false);	
                sqlUpdateRentedCopy.setInt(2, copyID);
                sqlUpdateRentedCopy.executeUpdate();  //the true rented flag is false now
		   
                sqlDeleteRented.setInt(1, copyID);		   
                sqlDeleteRented.executeUpdate();  //the movie is returned (return)
		   
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
				
	}

        
        public boolean addUser (UserEntry  userEntry)
        {
            try 
            {
                sqlInsertUser.setString(1, userEntry.getEMail());
                sqlInsertUser.setString(2, userEntry.getPassword());
                sqlInsertUser.setString(3, userEntry.getFirstName());
                sqlInsertUser.setString(4, userEntry.getLastName());
                sqlInsertUser.setString(5, userEntry.getAddress());
                sqlInsertUser.setString(6, userEntry.getPhoneNumber());
    	
                sqlInsertUser.executeUpdate();
            } 
        
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
    	
            return true;
        }
	
        public boolean addReview (ReviewEntry reviewEntry, int NumOfVotesSofar, double TotalGradeSofar)
	{
            int NumOfVotes;
            double TotalGrade;
		 
            try 
            {
                connection.setAutoCommit( false );  //start transaction
                 
                sqlInsertReview.setInt(1, reviewEntry.getMovieID());
                sqlInsertReview.setInt(2, reviewEntry.getUserId());
		sqlInsertReview.setString(3, reviewEntry.getHeadline());
		sqlInsertReview.setString(4, reviewEntry.getText());
		sqlInsertReview.setInt(5, reviewEntry.getRating());
		 
		sqlInsertReview.executeUpdate();
		 
		NumOfVotes=NumOfVotesSofar+1;
		 
		TotalGrade=(TotalGradeSofar*NumOfVotesSofar + reviewEntry.getRating())/NumOfVotes;
		 
		 
		sqlUpdateMovieGrade.setInt(1, NumOfVotes);
		sqlUpdateMovieGrade.setDouble(2, TotalGrade);
		sqlUpdateMovieGrade.setInt(3, reviewEntry.getMovieID());
		 
		sqlUpdateMovieGrade.executeUpdate();
                connection.commit();
                connection.setAutoCommit( true ); //end transaction
                
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
		return false;
            }
            
            return true;
	 }
	
	 
	 public boolean checkIfUserManger (String Email)
	 {
            try 
            {
                sqlIsUserManger.setString(1, Email);
		 
		ResultSet rs =sqlIsUserManger.executeQuery();
		 
		rs.next();
		 
		if  (rs.getInt(1)==1)
                    return true;
		 
		return false;
		 
            } 
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
		return false;
            }
	 }
	 
         
	 public boolean checkIfEmailExists (String Email)
	 {
            try 
            {
                sqlIsEmailExists.setString(1, Email);
	
		ResultSet rs =sqlIsEmailExists.executeQuery();
		  
		if(rs.next())
                    return false;
		   
		return true;
            }
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
		return false;
            }
            
         }
	 
	 
	 public void setUserManger (String Email)
	 {
            try 
            {
                sqlSetUserManger.setString(1, Email);
		sqlSetUserManger.executeUpdate();
		 
            }
            
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
            }

         }
	 
	 
	 public String getPassword (String Email)
	 {
            String password=null;
            
            try 
            {
                sqlGetPassword.setString(1, Email);
		 
		ResultSet rs = sqlGetPassword.executeQuery();
		if (rs.next())
                {
                    password=rs.getString(1);     	 
                }		
		 
		return password;
            }
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
		return password;
            }
         
         }
         
         
         
         public UserEntry getUser (String Email)
         {
            UserEntry user=new UserEntry();
            
            try 
            { 
                sqlGetUser.setString(1, Email);
		 
                ResultSet rs =  sqlGetUser.executeQuery();
              
                if ( !rs.next() )  //no such user
                    return null;
                 
                user.setUserId(rs.getInt(1));
                user.setManger(rs.getBoolean(2));
                user.setEMail(rs.getString(3));
                user.setPassword(rs.getString(4));        
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setPhoneNumber(rs.getString(8));       
                sqlGetRents.setInt(1, user.getUserId());
                      
                rs =  sqlGetRents.executeQuery();   
               
                ArrayList<RentEntry> rents=new ArrayList<RentEntry>();
                while(rs.next()) 
                {
                    rents.add(new RentEntry(rs.getInt(1),rs.getInt(2),rs.getLong(3)));      	 
                }
		 
                user.setRents(rents);
              
                return user;
              
            }
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
		return user;
            }  
             
         }
         
         
         public int getGenreID(String genreName)
         {
            int genreID = 0;
            
            try
            {
                sqlFindGenre.setString(1, genreName);
                ResultSet resultgenreID = sqlFindGenre.executeQuery();
                
                if (resultgenreID.next())
                {
                    genreID = resultgenreID.getInt(1);
                }
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return genreID;
            
         }
         

         public int getActorID(String actorName)
         {
            int actorID = 0;
            
            try
            {
                sqlFindActor.setString(1, actorName);
                ResultSet resultactorID = sqlFindActor.executeQuery();
                
                if (resultactorID.next())
                {
                    actorID = resultactorID.getInt(1);
                }
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return actorID;
            
         }
         
         
         public int getDirectorID(String directorName)
         {
            int directorID = 0;
            
            try
            {
                sqlFindDirector.setString(1, directorName);
                ResultSet resultdirectorID = sqlFindDirector.executeQuery();
                
                if (resultdirectorID.next())
                {
                    directorID = resultdirectorID.getInt(1);
                }
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return directorID;
            
         }
         
         
         public int getMovieID(String movieTitle)
         {
            int movieID = 0;
            
            try
            {
                sqlFindMovie.setString(1, movieTitle);
                ResultSet resultmovieID = sqlFindMovie.executeQuery();
                
                if (resultmovieID.next())
                {
                    movieID = resultmovieID.getInt(1);
                }
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return movieID;
            
         }
         
         
         public int getMovieIDByCopyID(int copyID)
         {
            int movieID = 0;
            
            try
            {
                sqlFindMovieIDByCopyID.setInt(1, copyID);
                ResultSet resultmovieIDByCopyID = sqlFindMovieIDByCopyID.executeQuery();
                
                if (resultmovieIDByCopyID.next())
                {
                    movieID = resultmovieIDByCopyID.getInt(1);
                }
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return movieID;
            
         }         
         

         public ArrayList<String> getMoviesTitles()
         {
            ArrayList<String> moviesTitles = new ArrayList<String>();
		 
            try 
            {
                ResultSet rs = sqlFindAllTitles.executeQuery();
			
		while(rs.next()) 
                {
                    moviesTitles.add(rs.getString(1));      	 
                }

            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return moviesTitles;
         }
         
         
         public ArrayList<String> getUsersEmails()
         {
            ArrayList<String> usersEmails = new ArrayList<String>();
		 
            try 
            {
                ResultSet rs = sqlFindAllEmails.executeQuery();
			
		while(rs.next()) 
                {
                    usersEmails.add(rs.getString(1));      	 
                }

            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return usersEmails;
         }        
         

         public ArrayList<Integer> getRentedCopyIDs()
         {
            ArrayList<Integer> rentedCopyIDs = new ArrayList<Integer>();
		 
            try 
            {
                ResultSet rs = sqlFindAllRentedCopyIDs.executeQuery();
			
		while(rs.next()) 
                {
                    rentedCopyIDs.add(rs.getInt(1));      	 
                }

            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return rentedCopyIDs;
            
         }         
        
        
         public boolean didGiveReview (int movieID, int userID)
         {
            try 
            { 
                sqlDidGiveReview.setInt(1, movieID);
                sqlDidGiveReview.setInt(2, userID);
                ResultSet rs =  sqlDidGiveReview.executeQuery();
              
                if ( !rs.next() )  //no review for that movie by that user was found
	             return false;
            } 
            
            catch (SQLException e) 
            {
		// TODO Auto-generated catch block
		e.printStackTrace();
                return false;
            }   
                
            return true;
              
         }
        
         public ArrayList<MovieEntry> searchByKeyword (String keyword)
         {
            ArrayList<MovieEntry> movies=new ArrayList<MovieEntry>();
		 
            try 
            {
                keyword= "%"+keyword+"%";
                sqlAdvancedSearch.setString(1, keyword);
                sqlAdvancedSearch.setString(2, keyword);
                sqlAdvancedSearch.setString(3, keyword);
		ResultSet rs = sqlAdvancedSearch.executeQuery();
			
		while(rs.next()) 
                {
                    movies.add(getMovieByID(rs.getInt(1)));      	 
                }
		 
            } 
            
            catch (SQLException e) 
            {
                // TODO Auto-generated catch block
		e.printStackTrace();
            }

            return movies;
         }
        
        
         public ArrayList<MovieEntry> getMoviesWithNoPicture ()
         {
            ArrayList<MovieEntry> movies=new ArrayList<MovieEntry>();
		 
            try 
            {
                sqlGetMoviesWithNoPicture.setString(1, "defaultPic.jpg");
		ResultSet rs = sqlGetMoviesWithNoPicture.executeQuery();
			
		while(rs.next()) 
                {
                    movies.add(getMovieByID(rs.getInt(1)));      	 
                }
		 
            }
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            return movies;
         }

         
         public void addPicturePath (String  picturePath, int movieID)
         {
            try 
            {
                sqlAddPicture.setString(1, picturePath);
                sqlAddPicture.setInt(2,movieID);
    	
                sqlAddPicture.executeUpdate();
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
    	
         }
         
}

