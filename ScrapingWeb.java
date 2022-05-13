/* Srapear la web de 20Minutos y obtener los H1 y los párrafos. */

package scrapingweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Metodo para obtener el codigo HTML de la pagina web
public class ScrapingWeb {

    public static Document getHTML(String url){
        Document html = null;
        try {
            html = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (Exception e) {
            System.out.println("Error al obtener el codigo HTML");
        }
        return html;
        
    }
  
    //Metodo para obtener la informacion
    public void scraping(){
        Elements articulos = ScrapingWeb.getHTML("https://www.20minutos.es/").select("article.media");
        for(Element noticia : articulos){
            try {
                String urlNoticia = noticia.select("a").attr("abs:href");
                Document htmlNoticia = ScrapingWeb.getHTML(urlNoticia);
                //Cojer el titulo
                String titulo = htmlNoticia.select("h1").text();
                //Cojer la informacion
                String informacion = htmlNoticia.select("p").text();
                
                System.out.println(titulo);
                System.out.println(informacion);
                System.out.println("----------");
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Error al entrar en la noticia");
            }
        }
    }
    
    public static void main(String[] args) {
        //System.out.println(ScrapingWeb.getHTML("https://www.20minutos.es/"));
        new ScrapingWeb().scraping();
    }
    
}
