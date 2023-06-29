package it.polimi.ingsw.network;

import java.net.*;
import java.util.Enumeration;

/**
 * This class is used as utilities for RMI type of connection.
 *
 * @author Alberto Aniballi
 */
public class UtilsForRMI {

    /**
     * It gets the local ip.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public static String getLocalIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isLoopback() && ni.isUp() && !ni.isVirtual() && !ni.getDisplayName().toLowerCase().contains("vmnet")) {
                    for (InterfaceAddress address : ni.getInterfaceAddresses())
                    {
                        InetAddress inetAddress = address.getAddress();
                        if (inetAddress != null && inetAddress instanceof Inet4Address)
                        {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
