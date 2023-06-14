import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.PrintStream;

public class AutomatedTelnetClient {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String prompt = "%";
	private String response="";

	public AutomatedTelnetClient(String server, Integer port, String password) {
		try {
			// Connect to the specified server
			telnet.connect(server, port);

			// Get input and output stream references
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());

			// Log the user on
			readUntil("Password: ");
			write(password);

			// Advance to a prompt
			response=readUntil("Password OK");
		} catch (Exception e) {
		}
	}

	public void su(String password) {
		try {
			write("su");
			readUntil("Password: ");
			write(password);
			prompt = "#";
			readUntil(prompt + " ");
		} catch (Exception e) {
		
		}
	}
	public String getResponse(){
		return response;
	}
	@SuppressWarnings("unused")
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
		
		}
		return null;
	}

	public String readUntill() {
		try {
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (ch!='>') {
				sb.append(ch);
				ch = (char) in.read();
			}
			return sb.toString();
		} catch (Exception e) {
		
		}
		return null;
	}
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
		
		}
	}

	public String sendCommand(String command) {
		try {
			write(command);
			return readUntill();
		} catch (Exception e) {
		
		}
		return null;
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
		
		}
	}

	public static void main(String[] args) {
		try {
			AutomatedTelnetClient telnet = new AutomatedTelnetClient(
					"myserver", 23, "Password");
			System.out.println("Got Connection...");
			telnet.sendCommand("ps -ef ");
			System.out.println("run command");
			telnet.sendCommand("ls ");
			System.out.println("run command 2");
			telnet.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
		
		}
	}
}