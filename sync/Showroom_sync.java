package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.Showroom;

public class Showroom_sync
{
  public static Showroom[] get_showrooms()
  {
    Showroom[] showrooms = null;
    try
    {
      HttpRequest request = new HttpRequest(new URL(Config.BASE_URL + "api/get_showrooms_list/"));
      System.out.println("Fetching showroom name lists");
      HttpResponse response = request.get();
      System.out.println("got response");
      String jsonResponse = response.getReponse();
      System.out.println(jsonResponse);
      Gson gson = new Gson();
      return (Showroom[])gson.fromJson(jsonResponse, Showroom[].class);
    }
    catch (MalformedURLException ex)
    {
      Logger.getLogger(Showroom_sync.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IOException ex)
    {
      Logger.getLogger(Showroom_sync.class.getName()).log(Level.SEVERE, null, ex);
    }
    return showrooms;
  }
}
