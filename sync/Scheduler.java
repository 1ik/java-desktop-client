package sync;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Scheduler
  implements Runnable
{
  private int interval;
  private TaskExecuter executer;
  private boolean isRunning = true;
  
  public Scheduler(TaskExecuter executer, int interval)
  {
    this.executer = executer;
    this.interval = interval;
  }
  
  public void run()
  {
    while (this.isRunning)
    {
      try
      {
        Thread.sleep(this.interval);
      }
      catch (InterruptedException ex)
      {
        Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
      }
      this.executer.execute();
    }
  }
  
  public void stop()
  {
    this.isRunning = false;
    stop();
  }
}
