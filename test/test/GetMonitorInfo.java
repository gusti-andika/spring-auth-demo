package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class GetMonitorInfo {

	/**
	 * 
	 * @param args
	 * 	 args[0] url to read content <br/>
	 *   args[1] dest to save
	 *   args[2] username for authentication
	 *   args[3] password for authentication
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			String msg = "Arguments should be 4 /n";
			msg += "arg[0]: url /n";
			msg += "arg[1]: dest /n";
			msg += "arg[2]: username /n";
			msg += "arg[3]: pass /n";
			
			throw new RuntimeException(msg);
		}
		
		GetMonitorInfo monitor = new GetMonitorInfo();
		String content = monitor.getContent(args[0], args[2], args[3]);
		if (content != null && content.trim().length() > 0)
			monitor.saveContent(content, args[1]);
	}
	
	protected String getContent(String url, String username, String password) {
		StringBuffer buffer = new StringBuffer();
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET"); 
			
			String encoded = Base64.getEncoder()
					.encodeToString((username+":"+password)
					.getBytes(StandardCharsets.UTF_8));
			connection.setRequestProperty("Authorization", "Basic "+encoded);
			
			connection.connect(); 
			int code = connection.getResponseCode();
			if (code != 200)
				throw new RuntimeException("GetMonitorInfo for url: " + url +" return status: " + code);
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				buffer.append(reader.readLine());
			}
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return buffer.toString();
	}
	
	protected void saveContent(String content, String dest) {
		try {
			content = "var data = " + content;
			Files.write(Paths.get(dest), content.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
