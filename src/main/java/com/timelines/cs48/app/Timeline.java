import java.util.ArrayList;

/**
 * Created by afunk on 4/18/18.
 */
public class Timeline {
    String name;
    int length;
    Keys keys;
    ArrayList<Article> articles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Adds article at end of timeline
    public void addArticle(Article a){
        articles.add(a);
    }

    // Adds article at user-defined date
    public void addArticle(Article a, String date){
        //STUB : uses date-based sorting to insert the article at correct date
    }



    public void listArticles(){
        //STUB

        for(Article article : articles){
            String art_name = article.getName();
            String art_date = article.getDate();
            String art_desc = article.getDescription(); //Maybe necessary -- might be too long for a list
            String art_key = article.getKey();

        }
        //Returns list of articles
    }

}
