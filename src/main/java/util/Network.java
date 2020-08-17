package util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class Network {
	
	public static String getMyIP() {
		InetAddress iAddress;
		try {
			iAddress = InetAddress.getLocalHost();
			return iAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
