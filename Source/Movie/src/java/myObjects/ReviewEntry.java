package myObjects;

public class ReviewEntry 
{
	
	private int reviewID;
	private int movieID;
	private int userID;
	
	private String Headline;
	private String Text;
	
	private int Rating;
        
        
        public ReviewEntry()
	{		
	}
        
        public ReviewEntry(int reviewID, int movieID, int userID, String Headline, String Text, int Rating) 
        {
            super();
            this.reviewID = reviewID;
            this.movieID = movieID;
            this.userID = userID;
            this.Headline = Headline;
            this.Text = Text;
            this.Rating = Rating;
        }        

	public int getReviewId() {
		return reviewID;
	}

	public void setReviewId(int reviewId) {
		this.reviewID = reviewId;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getUserId() {
		return userID;
	}

	public void setUserId(int userId) {
		this.userID = userId;
	}

	public String getHeadline() {
		return Headline;
	}

	public void setHeadline(String headline) {
		Headline = headline;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
}
