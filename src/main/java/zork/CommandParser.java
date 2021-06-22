package zork;


import zork.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {

    private List<String> allCommandsSortedByLength = new ArrayList<>();
    {
        allCommandsSortedByLength.addAll(CommandFactory.getAllCommands());
        allCommandsSortedByLength.sort((o1, o2) -> o2.length() - o1.length());
    }
    private String matchInputCommand(String input) {
        for(String command: allCommandsSortedByLength) {
            if (input.startsWith(command)) {
                return command;
            }
        }
        return null;
    }

    public List<String> parse(String stringInput) {
        String cleanedInput = stringInput.trim();
        String cmd = matchInputCommand(cleanedInput);
        Command command = CommandFactory.get(cmd);
        if (command == null) {}
        else if (command.numArgs() > 0) {
            String argString = cleanedInput.substring(0,cmd.length() + 1);
            System.out.println(argString);
            return Arrays.asList(cmd, argString);
        }
        else {
            return Arrays.asList(cmd);
        }
        return Arrays.asList(cmd);
    }
}
