package com.tarun.sampleweatherapp.pojo.futureTemp;

public class City
{
    private String country;

    private Coord coord;

    private String timezone;

    private String name;

    private String id;

    private String population;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPopulation ()
    {
        return population;
    }

    public void setPopulation (String population)
    {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", coord = "+coord+", timezone = "+timezone+", name = "+name+", id = "+id+", population = "+population+"]";
    }
}