package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import models.Expense_model;
import structures.Expense;

public class Expense_sync
{
  public static void startsynching(int milliseconds)
  {
    Scheduler d = new Scheduler(new TaskExecuter()
    {
      public void execute()
      {
        ArrayList<Expense> expense_list = Expense_model.getExpenses();
        System.out.println("Expense Sync");
        if (expense_list.size() > 0)
        {
          System.out.println("synching...." + expense_list.size() + " items");
          Gson gson = new Gson();
          String json = gson.toJson(expense_list);
          try
          {
            HttpRequest request = new HttpRequest(new URL(Config.BASE_URL + "api/request_new_expense"));
            System.out.println(json);
            request.addPostValue("expenses", json);
            HttpResponse response = request.post();
            json = response.getReponse();
            System.out.println(json);
            
            int[] ids = (int[])gson.fromJson(json, int[].class);
            Expense_model.delete(ids);
            System.out.println("removing " + ids.length + " items from local table");
          }
          catch (IOException ex)
          {
            System.err.println("Problem with connection : Expense_sync.java");
          }
        }
      }
    }, milliseconds);
    
    Thread s = new Thread(d);
    s.start();
  }
}
