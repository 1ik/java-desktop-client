package httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class HttpResponse
{
  InputStream in;
  BufferedReader reader;
  InputStreamReader inreader;
  int statuscode;
  Map<String, List<String>> header;
  
  public HttpResponse(int statuscode, InputStream in, Map<String, List<String>> map)
  {
    this.statuscode = statuscode;
    this.in = in;
    this.header = map;
  }
  
  public boolean hasHeader(String key)
  {
    return this.header.containsKey(key);
  }
  
  public String getHeader(String key)
  {
    return (String)((List)this.header.get(key)).get(0);
  }
  
  public List<String> getHeaderList(String key)
  {
    return (List)this.header.get(key);
  }
  
  public Map<String, List<String>> getHeaders()
  {
    return this.header;
  }
  
  public int getStatusCode()
  {
    return this.statuscode;
  }
  
  public InputStream getInputStream()
  {
    return this.in;
  }
  
  public InputStreamReader getInputStreamReader()
  {
    if (this.inreader == null) {
      this.inreader = new InputStreamReader(this.in);
    }
    return this.inreader;
  }
  
  public BufferedReader getReader()
  {
    if (this.reader == null) {
      this.reader = new BufferedReader(getInputStreamReader());
    }
    return this.reader;
  }
  
  public String getReponse()
  {
    return toString();
  }
  
  public String toString()
  {
    try
    {
      InputStreamReader r = getInputStreamReader();
      
      StringBuilder b = new StringBuilder();
      char[] buffer = new char['က'];
      int n = 0;
      while (n >= 0)
      {
        n = r.read(buffer, 0, buffer.length);
        if (n > 0) {
          b.append(buffer, 0, n);
        }
      }
      this.in.close();
      
      return b.toString();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
