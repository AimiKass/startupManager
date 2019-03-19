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
        int length =90-name.length();
        String newUrlFormat = "("+url+")";

        return String.format(name +"%1$"+length+"s", newUrlFormat);
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
