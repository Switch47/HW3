package zork.commands;

import zork.Game;

import java.util.List;

public class RightCommand implements Command {
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "go east";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (Game.currentRoom.rightRoom != null) {
            Game.currentRoom = Game.currentRoom.rightRoom;
            Game.player.increaseHP(2);
            System.out.println("Now you are in : [ " + Game.currentRoom.name + " ]");
        }
        else {
            System.out.println("There is no door.");
        }
    }
}
