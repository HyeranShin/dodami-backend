package com.soma.dodam.dodami.util;

import com.soma.dodam.dodami.exception.NotExistException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellUtil {
    private static final String BASH = "/bin/bash";
    private static final String RUN = "-c";
    private static final String FAIL_MESSAGE = "FAIL TO PROCESS.";
    private static final String NO_PARAMETER = "";
    private static final String BLANK = " ";

    public static Map execCommand(String... str) {
        ProcessBuilder pb = new ProcessBuilder(str);
        pb.redirectErrorStream(true);

        Process process = startProcess(pb);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> echos = getEchos(reader);
        waitExit(process);

        return getMap(echos, process);
    }

    private static Process startProcess(ProcessBuilder pb) {
        try {
            return pb.start();
        } catch (IOException e) {
            throw new NotExistException("shell", "해당 쉘이 존재하지 않습니다.");
        }
    }

    private static List<String> getEchos(BufferedReader reader) {
        try {
            List<String> echos = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                echos.add(line);
            }
            return echos;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void waitExit(Process process) {
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Map getMap(List<String> echos, Process process) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, String.valueOf(process.exitValue()));
        for (int i = 1; i <= echos.size(); i++) {
            map.put(i, echos.get(i - 1));
        }
        return map;
    }

    public static String[] getBashCmd(final String shell) {
        return getBashCmd(shell, NO_PARAMETER);
    }

    public static String[] getBashCmd(final String shell, final String... parameter) {
        List<String> callCmd = getBasicCmd();
        StringBuilder command = assembleCommand(shell, parameter);
        callCmd.add(command.toString());
        return callCmd.toArray(new String[0]);
    }

    private static StringBuilder assembleCommand(String shell, String... parameter) {
        StringBuilder command = new StringBuilder();
        command.append(shell)
                .append(BLANK);
        for (int i = 0; i < parameter.length; i++) {
            command.append(parameter[i])
                    .append(BLANK);
        }
        return command;
    }

    private static List<String> getBasicCmd() {
        List<String> callCmd = new ArrayList<>();
        callCmd.add(BASH);
        callCmd.add(RUN);
        return callCmd;
    }

    public static Map getFailResult() {
        Map<Integer, String> result = new HashMap<>();
        result.put(0, FAIL_MESSAGE);
        return result;
    }
}
