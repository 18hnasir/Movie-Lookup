package application;

import java.sql.Date;

public class Movie {

	String movieid, title, language, productioncompany, productioncountry, runtime, avgrating;
	Date releasedate;
	
	public Movie(String movieid, String title, String language, String productioncompany, String productioncountry, Date releasedate, String runtime, String avgrating) {
		this.movieid = movieid;
		this.title = title;
		this.language = language;
		this.productioncompany = productioncompany;
		this.productioncountry = productioncountry;
		this.releasedate = releasedate;
		this.runtime = runtime; 
		this.avgrating = avgrating;
	}
	
	public String getMovieid() {
		return movieid;
	}

	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProductioncompany() {
		return productioncompany;
	}

	public void setProductioncompany(String productioncompany) {
		this.productioncompany = productioncompany;
	}

	public String getProductioncountry() {
		return productioncountry;
	}

	public void setProductioncountry(String productioncountry) {
		this.productioncountry = productioncountry;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	
	public String getAvgrating() {
		return avgrating;
	}
	
	public void setAvgrating(String avgrating) {
		this.avgrating = avgrating;
	}


	
	
}
