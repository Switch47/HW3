package zork.commands;

import zork.Game;

import java.io.IOException;
import java.util.List;

public interface Command {

    int numArgs();

    String getCommand();

    void execute(Game game, List<String> argument) throws IOException;

}
