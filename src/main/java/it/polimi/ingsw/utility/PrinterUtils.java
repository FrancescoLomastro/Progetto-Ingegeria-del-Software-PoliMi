package it.polimi.ingsw.utility;

/**
 * utility class useful to print proportioned grids in CLI
 */
public class PrinterUtils {
    /**
     * @param string a referent string
     * @return a string entirely made of space with the same length of the string in the parameter
     * @author Francesco Lo Mastro
     */
    public static String printEquivalentSpace(String string)
    {
        String s="";
        for(int i=0; i<string.length();i++)
            s+=" ";
        return s;
    }
}
