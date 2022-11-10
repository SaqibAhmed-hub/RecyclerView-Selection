package com.example.recyclerview

data class Movie(var title: String,var genre : String,var year: Int)

object Supplier{
    val Movies  = listOf(
        Movie("Mad Max","Action and Adventure",2010),
        Movie("Fast and Furious","Action and Adventure",2007),
        Movie("Mentalist", "Thriller and Drama",2007),
        Movie("The Devil Attorney","Drama",1996),
        Movie("Ocean Elevean","Action and Drama",2011),
        Movie("The BlackList","Action and Adventure",2016),
        Movie("Mission Impossible","Action and Adventure",1999),
        Movie("Top Gun","Action and Adventure",1987),
        Movie("DisneyLand","Animation",2002),
        Movie("Devil Eye","Animation",2003),
        Movie("Devil May Cry","Game Genre",2009),
        Movie("Identity","Action and Adventure",2014),
        Movie("Bad Boys","Action and Adventure",2001),
        Movie("Kung fu Yoga","Action and Comedy",2017),
        Movie("Drunken Master","Action and Comedy",1994),
        Movie("Small Solider","Animation",1984),
        Movie("Game", "Drama",2002),
        Movie("Fantastic Four","Action and Adventure",2008),
        Movie("Iron man","Action and Adventure",2008),
        Movie("Iron Man 2","Action and Adventure",2010),
        Movie("Avenger","Action and Adventure",2012),
        Movie("Captain America","Action and Adventure",2011),
        Movie("Guardian of the Galaxy","Action and Adventure",2014),
        Movie("Avenger 2","Action and Adventure",2016)
    )
}