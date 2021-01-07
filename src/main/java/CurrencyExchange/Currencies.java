package CurrencyExchange;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Currencies {
    float EUR_buy_kantor1;
    float EUR_sell_kantor1;
    float USD_buy_kantor1;
    float USD_sell_kantor1;
    float GBP_buy_kantor1;
    float GBP_sell_kantor1;

    float EUR_buy_kantor2;
    float EUR_sell_kantor2;
    float USD_buy_kantor2;
    float USD_sell_kantor2;
    float GBP_buy_kantor2;
    float GBP_sell_kantor2;

    float EUR_buy_kantor3;
    float EUR_sell_kantor3;
    float USD_buy_kantor3;
    float USD_sell_kantor3;
    float GBP_buy_kantor3;
    float GBP_sell_kantor3;

    float exchange_kantor1;
    float exchange_kantor2;
    float exchange_kantor3;
    int best_cantor;


    public Currencies(){
        {
            try {
                Document doc;
                doc = Jsoup.connect("https://internetowykantor.pl/kurs-euro/").timeout(60000).get();
                EUR_buy_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_sprzedazy.bem-single-rate-box__item-rate").text().replace(",","."));
                EUR_sell_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_kupna.bem-single-rate-box__item-rate").text().replace(",","."));
                doc = Jsoup.connect("https://internetowykantor.pl/kurs-dolara/").timeout(60000).get();
                USD_buy_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_sprzedazy.bem-single-rate-box__item-rate").text().replace(",","."));
                USD_sell_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_kupna.bem-single-rate-box__item-rate").text().replace(",","."));
                doc = Jsoup.connect("https://internetowykantor.pl/kurs-funta/").timeout(60000).get();
                GBP_buy_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_sprzedazy.bem-single-rate-box__item-rate").text().replace(",","."));
                GBP_sell_kantor1= Float.parseFloat(doc.select("span.kurs.kurs_kupna.bem-single-rate-box__item-rate").text().replace(",","."));
                doc = Jsoup.connect("https://kantoronline.pl/kursy-walut").timeout(60000).get();
                EUR_buy_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(0).select("td").get(1).text());
                EUR_sell_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(0).select("td").get(3).text());
                USD_buy_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(1).select("td").get(1).text());
                USD_sell_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(1).select("td").get(3).text());
                GBP_buy_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(3).select("td").get(1).text());
                GBP_sell_kantor2 =Float.parseFloat(doc.select("tbody").get(1).select("tr").get(3).select("td").get(3).text());
                doc = Jsoup.connect("https://www.walutomat.pl/").timeout(60000).get();
                String string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(0).select("div").get(1).select("span").get(0).text();
                EUR_sell_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
                string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(0).select("div").get(2).select("span").get(0).text();
                EUR_buy_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
                string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(1).select("div").get(1).select("span").get(0).text();
                USD_sell_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
                string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(1).select("div").get(2).select("span").get(0).text();
                USD_buy_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
                string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(3).select("div").get(1).select("span").get(0).text();
                GBP_sell_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
                string_value =doc.select("div.row.exchange-rates-row").get(0).select("a").get(3).select("div").get(2).select("span").get(0).text();
                GBP_buy_kantor3=Float.parseFloat(string_value.substring(0, string_value.length() - 4).replace(",","."));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void evaluateBestCourse(String from, String to){
        float how_much_in_pln_kantor1=0;
        float how_much_in_pln_kantor2=0;
        float how_much_in_pln_kantor3=0;
        if(from==to){
            this.exchange_kantor1=1;
            this.exchange_kantor2=1;
            this.exchange_kantor3=1;
            this.best_cantor=0;
            return;
        }
        switch (from) {
            case "PLN":
                how_much_in_pln_kantor1=1;
                how_much_in_pln_kantor2=1;
                how_much_in_pln_kantor3=1;
                break;
            case "GBP":
                how_much_in_pln_kantor1=this.GBP_buy_kantor1;
                how_much_in_pln_kantor2=this.GBP_buy_kantor2;
                how_much_in_pln_kantor3=this.GBP_buy_kantor3;
                break;
            case "EUR":
                how_much_in_pln_kantor1=this.EUR_buy_kantor1;
                how_much_in_pln_kantor2=this.EUR_buy_kantor2;
                how_much_in_pln_kantor3=this.EUR_buy_kantor3;
                break;
            case "USD":
                how_much_in_pln_kantor1=this.USD_buy_kantor1;
                how_much_in_pln_kantor2=this.USD_buy_kantor2;
                how_much_in_pln_kantor3=this.USD_buy_kantor3;
                break;
        }
        switch (to) {
            case "PLN":
                this.exchange_kantor1=how_much_in_pln_kantor1/1;
                this.exchange_kantor2=how_much_in_pln_kantor2/1;
                this.exchange_kantor3=how_much_in_pln_kantor3/1;
                break;
            case "GBP":
                this.exchange_kantor1=how_much_in_pln_kantor1/this.GBP_sell_kantor1;
                this.exchange_kantor2=how_much_in_pln_kantor2/this.GBP_sell_kantor2;
                this.exchange_kantor3=how_much_in_pln_kantor3/this.GBP_sell_kantor3;
                break;
            case "EUR":
                this.exchange_kantor1=how_much_in_pln_kantor1/this.EUR_sell_kantor1;
                this.exchange_kantor2=how_much_in_pln_kantor2/this.EUR_sell_kantor2;
                this.exchange_kantor3=how_much_in_pln_kantor3/this.EUR_sell_kantor3;
                break;
            case "USD":
                this.exchange_kantor1=how_much_in_pln_kantor1/this.USD_sell_kantor1;
                this.exchange_kantor2=how_much_in_pln_kantor2/this.USD_sell_kantor2;
                this.exchange_kantor3=how_much_in_pln_kantor3/this.USD_sell_kantor3;
                break;
        }

        if (this.exchange_kantor1 >= this.exchange_kantor2 && this.exchange_kantor1 >= this.exchange_kantor3){
            best_cantor = 1;
        } else if (this.exchange_kantor2 >= this.exchange_kantor1 && this.exchange_kantor2 >= this.exchange_kantor3){
            best_cantor = 2;
        } else{
            best_cantor = 3;
        };
    }
}

