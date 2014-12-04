package org.mageTech.mageUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * logging utility for logging to a file
 *
 * @author Isaac.Wheeler
 * @version 12.3.14
 */
public class logUtil {

    // log file printer
    private PrintWriter out;

    /**
     * sets up the logUtil
     *
     * @param logName the name of the log File
     */
    public logUtil(String logName) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        try {
            out = new PrintWriter(new FileWriter(logName
                    + dateFormat.format(date) + ".txt"), true);
        } catch (IOException e) {
            e.printStackTrace(); // TODO: better exception handling
        }
    }

    /**
     * logs a msg to the log file
     *
     * @param msg the msg being logged
     */
    public void log(String msg) {
        out.print(msg);
        out.flush();
    }

    /**
     * logs a msg to the log file on and moves the line id to a new line
     *
     * @param msg the msg being logged
     */
    public void logln(String msg) {
        out.println(msg);
        out.flush();
    }

    /**
     * closes the log file
     */
    public void closeLog() {
        out.close();
    }
}
