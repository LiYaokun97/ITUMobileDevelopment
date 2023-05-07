# ITUMobileDevelopment
ITU Mobile Development Course Project

Yaokun Li
yali@itu.dk

## Description of this project
This app has two pages:
1. News List Page. This is the launch page. When starting, we will fetch the news through an API. After fetching, the news will be displayed using LiveData. By clicking on each news item, you will enter the second page. 
   The news data will be stored in an SQLite database, so as long as the news is successfully fetched from the API for the first time, the news can be loaded from the database again in subsequent uses, even if there is no internet connection.
   
2. News details page: The news details page provides the news content, but due to API limitations, only a portion of the news content can be obtained (this is not a bug), so you may see something like [+1000 chars].

## The Concepts My app uses
- some MutableLiveData
  The data in the two pages are all stored in LiveData.
- ConstraintLayout
  The news card are implemented in ConstraintLayout.
- an internal SQLite database
  Data are stored in SQLite database.
- some networking (Http communication)
  Fetching news data from api.
