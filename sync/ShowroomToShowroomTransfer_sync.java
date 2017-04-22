package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import models.Item_model;
import models.Transfer_model;
import structures.Item;
import structures.TransferList;

public class ShowroomToShowroomTransfer_sync
{
  private static boolean requestsent = false;
  
  public static void sync()
  {
    System.out.println("Showroom2Showroom");
    if (requestsent)
    {
      System.out.println("Showroom2Showroom : Aborting transfer sync request is already sent.");
    }
    else
    {
      requestsent = true;
      int latest_transfer_id = Transfer_model.get_last_ShowroomToShowroom_transfer_id();
      System.out.println(latest_transfer_id);
      String url = Config.BASE_URL + "api/get_new_showroom_transferred_items/" + Config.SHOWROOM_ID + "/";
      if (latest_transfer_id != -1) {
        url = url + latest_transfer_id;
      }
      System.out.println("Showroom2Showroom : " + url);
      try
      {
        HttpRequest request = new HttpRequest(new URL(url));
        HttpResponse response = request.get();
        String json_response = response.getReponse();
        System.out.println("Showroom2Showroom : Response received...");
        Gson gson = new Gson();
        TransferList transfers = (TransferList)gson.fromJson(json_response, TransferList.class);
        if (transfers.transfer_ids.length > 0)
        {
          System.out.println("Showroom2Showroom : updating transfer table..");
          Transfer_model.insert(transfers.transfer_ids);
          
          System.out.println("Showroom2Showroom : updating item table..");
          Item_model.insert(transfers.items);
        }
      }
      catch (MalformedURLException ex)
      {
        System.err.println("Showroom2Showroom : problem with connectin");
      }
      catch (IOException ex)
      {
        System.err.println("Showroom2Showroom : problem with connectin");
      }
      finally
      {
        System.out.println("Showroom2Showroom : Transfer synching ended ...");
        requestsent = false;
      }
    }
  }
  
  public static void main(String[] args)
    throws IOException
  {
    String url = "http://jhinukfashion.com/inventory/api/get_new_items/2/";
    HttpRequest request = new HttpRequest(new URL(url));
    HttpResponse response = request.get();
    String json_response = response.getReponse();
    System.out.println("Response received...");
    Gson gson = new Gson();
    
    TransferList transfer_list = (TransferList)gson.fromJson(json_response, TransferList.class);
    for (Item item : transfer_list.items) {
      System.err.println(item);
    }
  }
}
