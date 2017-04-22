package sync;

import com.google.gson.Gson;
import config.Config;
import httpClient.HttpRequest;
import httpClient.HttpResponse;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MemoResponseObject;
import models.Memo_model;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import structures.Memo;

public class Memo_sync
  implements Job
{
  boolean requestSent = false;
  
  public void execute(JobExecutionContext jec)
    throws JobExecutionException
  {
    System.out.println("Syncing Memo : ");
    if (this.requestSent)
    {
      System.err.println("Memo request already sent. Aborting memo sync..");
      return;
    }
    this.requestSent = true;
    ArrayList<Memo> memos = Memo_model.getUnupdatedMemos();
    if (memos.size() > 0) {
      try
      {
        System.out.println(memos.size() + " items found !!!");
        Gson gson = new Gson();
        String memo_json = gson.toJson(memos);
        
        System.out.println(memo_json);
        
        System.out.println("sending Memos ....");
        HttpRequest request = new HttpRequest(new URL(Config.BASE_URL + "api/submit_new_memos/"));
        request.addPostValue("memos", memo_json);
        HttpResponse server_response = request.post();
        String response_string = server_response.getReponse();
        System.out.println(response_string);
        
        MemoResponseObject[] responses = (MemoResponseObject[])gson.fromJson(response_string, MemoResponseObject[].class);
        System.out.println("Updating memo rows....");
        Memo_model.updateMemos(responses);
      }
      catch (Exception ex)
      {
        Logger.getLogger(Memo_sync.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      System.out.println("No items in the memo table found!!");
    }
    this.requestSent = false;
    System.out.println("memo table checking ended");
  }
  
  public static void main(String[] args)
  {
    ArrayList<Memo> memos = Memo_model.get();
    for (Memo memo : memos) {
      System.out.println(memo);
    }
    Gson gson = new Gson();
    System.out.println(gson.toJson(memos));
  }
}
