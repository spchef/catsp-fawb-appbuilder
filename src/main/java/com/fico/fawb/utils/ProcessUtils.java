package com.fico.fawb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

public class ProcessUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessUtils.class);

    public static void executeCommand(String directory, String command, File file)  {
        Process p = null;
        String command2Execute = (directory != null ? " cd " + directory + " && " + command : command);
        LOGGER.debug("executing command " + command2Execute);
        String arr[] = new String[2];
        if(System.getProperty("os.name").toLowerCase().startsWith("win"))
        {
            arr[0] = "cmd.exe";
            arr[1] = "/c";
        }
        else{
            arr[0]= "/bin/bash";
            arr[1] = "-c";
        }
        try {
            p = new ProcessBuilder().
                    command(Arrays.asList(new String[]{arr[0], arr[1], command2Execute})).redirectOutput(ProcessBuilder.Redirect.appendTo(file)).redirectErrorStream(true).start();
            p.waitFor();
            if (p.exitValue() != 0) {
                logException(command2Execute, p.getErrorStream());
                throw new RuntimeException("process terminated with status code: " + p.exitValue());
            }
            LOGGER.info("Successfully executed command: " + command2Execute);
        } catch (Exception e) {
            logException(command2Execute, e);
            throw new RuntimeException(e);

        } finally {
            if (p != null) {
                p.destroy();
            }
        }
    }

    private static void logException(String cmd, Object error) {
        LOGGER.error("Exception occurred while executing:{}  due to {}: ", cmd, error);
    }
}
