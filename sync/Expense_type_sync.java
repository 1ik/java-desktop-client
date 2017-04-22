package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import structures.Expense_type;

public class Expense_type_sync
{
  public static Expense_type[] DownloadExpense_types()
  {
    try
    {
      HttpRequest req = new HttpRequest(new URL(Config.BASE_URL + "api/get_expense_types"));
      HttpResponse response = req.get();
      String json = response.getReponse();
      Gson gson = new Gson();
      return (Expense_type[])gson.fromJson(json, Expense_type[].class);
    }
    catch (IOException ex)
    {
      System.err.println("Problem with connection : Expense_type_sync.java");
    }
    return null;
  }
}
