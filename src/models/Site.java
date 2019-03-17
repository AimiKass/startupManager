package models;

public class Site
{
    String url;
    String name;

    public Site(String url, String name)
    {
        this.url = url;
        this.name = name;
    }

    @Override

    public String toString()
    {
        // TODO: 3/17/2019 make it look beautiful :)
        return (name+"       ("+url+")");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
