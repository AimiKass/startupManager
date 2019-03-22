package models;

public class Site
{
    String url;
    String name ;

    public Site(String url, String name)
    {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString()
    {
        int length =90-name.length();
        String newUrlFormat = "("+ getTrimmedUrl(url)+")";

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


    private String getTrimmedUrl(String url)
    {
        StringBuilder stringBuilder = new StringBuilder(url);

        int substringStartIndex = 0;
        int substringEndIndex = 0;

        if (stringBuilder.toString().contains("www"))
            substringStartIndex = stringBuilder.toString().indexOf("www");
        else
            substringStartIndex = stringBuilder.toString().indexOf("/");

        stringBuilder.deleteCharAt(stringBuilder.toString().indexOf("/"));

        if (stringBuilder.toString().contains("/"))
            substringEndIndex = stringBuilder.toString().indexOf("/");
        else
            substringEndIndex = stringBuilder.toString().length();


        return stringBuilder.toString().substring(substringStartIndex-1,substringEndIndex);
    }
}
