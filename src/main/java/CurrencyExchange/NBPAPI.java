package CurrencyExchange;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NBPAPI {
    ArrayList<Float> values_array;
    ArrayList<String> dates_array;

    public NBPAPI(){
        values_array = new ArrayList<Float>();
        dates_array = new ArrayList<String>();
    }

    public void downloadData(String currency, String period){
        int daystosubstract=30;
        if (period=="week"){
            daystosubstract=7;
        }
        if (period=="year"){
            daystosubstract=365;
        }
        JSONObject json = null;
        Date date = Calendar.getInstance().getTime();
        Date querydate= subtractDays(date, daystosubstract);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String querydate_string = dateFormat.format(querydate);
        String nowdate_string = dateFormat.format(date);
        try {
            json = readJsonFromUrl("http://api.nbp.pl/api/exchangerates/rates/a/"+currency+"/"+querydate_string+"/"+nowdate_string+"/?format=json");
            JSONArray the_json_array = json.getJSONArray("rates");
            values_array = new ArrayList<Float>();
            dates_array = new ArrayList<String>();
            int size = the_json_array.length();
            for (int i = 0; i < size; i++) {
                JSONObject another_json_object = the_json_array.getJSONObject(i);
                float mid = another_json_object.getFloat("mid");
                String eff_date = another_json_object.getString("effectiveDate");
                values_array.add(mid);
                dates_array.add(eff_date);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static Date subtractDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }
}
