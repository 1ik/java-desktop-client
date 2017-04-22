package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.PrintStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Discount_model;
import structures.Discount;

public class Discount_sync
{
  static boolean requestSent = false;
  
  public static void check()
  {
    if (requestSent)
    {
      System.err.println("Discount request already sent. aborting.");
      return;
    }
    requestSent = true;
    try
    {
      Gson gson = new Gson();
      HttpRequest request = new HttpRequest(new URL(Config.BASE_URL + "api/get_discounts/"));
      HttpResponse server_response = request.post();
      String response_string = server_response.getReponse();
      Discount[] discounts = (Discount[])gson.fromJson(response_string, Discount[].class);
      System.out.println("Discount response received. updating table.");
      Discount_model.add_discounts(discounts);
      System.out.println("Discount table updated.");
    }
    catch (Exception ex)
    {
      Logger.getLogger(Memo_sync.class.getName()).log(Level.SEVERE, null, ex);
    }
    requestSent = false;
    System.out.println("discount check ended");
  }
  
  public static void main(String[] args) {}
}
