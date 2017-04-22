package httpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest
{
  String method = "GET";
  URL url;
  List<String> postParamKeys = new ArrayList();
  List<String> postParamValues = new ArrayList();
  List<String> getParamKeys = new ArrayList();
  List<String> getParamValues = new ArrayList();
  Map<String, String> headers = new HashMap();
  HttpURLConnection conn = null;
  InputStream in = null;
  public int timeout = 10000;
  
  public static HttpRequest create(URL u)
    throws IOException
  {
    return new HttpRequest(u);
  }
  
  public HttpRequest(URL u)
    throws IOException
  {
    this.url = u;
  }
  
  public List<String> getPostKeys()
  {
    return this.postParamKeys;
  }
  
  public List<String> getPostValues()
  {
    return this.postParamValues;
  }
  
  public List<String> getGetKeys()
  {
    return this.getParamKeys;
  }
  
  public List<String> getGetValues()
  {
    return this.getParamValues;
  }
  
  public HttpRequest addPostValue(String key, String value)
  {
    this.postParamKeys.add(key);
    this.postParamValues.add(value);
    return this;
  }
  
  public HttpRequest addPostValue(String key, Object value)
  {
    addPostValue(key, value.toString());
    return this;
  }
  
  public HttpRequest setPostValues(Map<String, String> map)
  {
    this.postParamKeys = new ArrayList(map.keySet());
    this.postParamValues = new ArrayList(map.values());
    return this;
  }
  
  public HttpRequest addGetValue(String key, String value)
  {
    this.getParamKeys.add(key);
    this.getParamValues.add(value);
    return this;
  }
  
  public HttpRequest addGetValue(String key, Object value)
  {
    addGetValue(key, value.toString());
    return this;
  }
  
  public HttpRequest setGetValues(Map<String, String> map)
  {
    this.getParamKeys = new ArrayList(map.keySet());
    this.getParamValues = new ArrayList(map.values());
    return this;
  }
  
  public HttpRequest setHeader(String key, String value)
  {
    this.headers.put(key, value);
    return this;
  }
  
  public HttpRequest setHeaders(Map<String, String> map)
  {
    this.headers = map;
    return this;
  }
  
  public HttpRequest setMethod(String m)
  {
    this.method = m;
    return this;
  }
  
  public int timeout()
  {
    return this.timeout;
  }
  
  public HttpRequest setTimeout(int timeoutMilliseconds)
  {
    this.timeout = timeoutMilliseconds;
    return this;
  }
  
  private String getGetURL()
  {
    StringBuilder b = new StringBuilder();
    
    String prefix = "";
    int l = this.getParamKeys.size();
    for (int i = 0; i < l; i++)
    {
      String k = (String)this.getParamKeys.get(i);
      
      b.append(prefix);
      prefix = "&";
      
      b.append(encode(k)).append("=").append(encode((String)this.getParamValues.get(i)));
    }
    return b.toString();
  }
  
  private String getPostParms()
  {
    StringBuilder b = new StringBuilder();
    
    String prefix = "";
    int l = this.postParamKeys.size();
    for (int i = 0; i < l; i++)
    {
      String k = (String)this.postParamKeys.get(i);
      
      b.append(prefix);
      prefix = "&";
      
      b.append(encode(k)).append("=").append(encode((String)this.postParamValues.get(i)));
    }
    return b.toString();
  }
  
  public HttpResponse request()
    throws IOException
  {
    return request(this.method);
  }
  
  public HttpResponse request(String requestMethod)
    throws IOException, SocketTimeoutException
  {
    if (this.getParamKeys.size() > 0)
    {
      String parms = getGetURL();
      
      String us = this.url.toString();
      if (us.contains("?")) {
        this.url = new URL(this.url.toString().concat(parms));
      } else {
        this.url = new URL(this.url.toString().concat("?").concat(parms));
      }
    }
    this.conn = ((HttpURLConnection)this.url.openConnection());
    this.conn.setConnectTimeout(this.timeout);
    this.conn.setRequestMethod(requestMethod.toUpperCase());
    this.conn.setDoInput(true);
    if (this.headers.size() > 0) {
      for (String k : this.headers.keySet()) {
        this.conn.setRequestProperty(k, (String)this.headers.get(k));
      }
    }
    if (this.postParamKeys.size() > 0)
    {
      this.conn.setDoOutput(true);
      
      OutputStreamWriter wr = new OutputStreamWriter(this.conn.getOutputStream());
      wr.write(getPostParms());
      wr.flush();
      wr.close();
    }
    if (this.conn.getResponseCode() >= 400) {
      this.in = this.conn.getErrorStream();
    } else {
      this.in = this.conn.getInputStream();
    }
    return new HttpResponse(this.conn.getResponseCode(), this.in, this.conn.getHeaderFields());
  }
  
  public HttpResponse get()
    throws IOException, SocketTimeoutException
  {
    return request("GET");
  }
  
  public HttpResponse head()
    throws IOException, SocketTimeoutException
  {
    return request("HEAD");
  }
  
  public HttpResponse post()
    throws IOException, SocketTimeoutException
  {
    return request("POST");
  }
  
  public HttpResponse put()
    throws IOException, SocketTimeoutException
  {
    return request("PUT");
  }
  
  public HttpResponse delete()
    throws IOException, SocketTimeoutException
  {
    return request("DELETE");
  }
  
  private String encode(String s)
  {
    try
    {
      return URLEncoder.encode(s, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return s;
  }
  
  public void close()
  {
    if (this.in != null) {
      try
      {
        this.in.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    if (this.conn != null) {
      this.conn.disconnect();
    }
  }
}
