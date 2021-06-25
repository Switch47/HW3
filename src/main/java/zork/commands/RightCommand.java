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
        if (Game.getGameStatus() == Game.PLAY_STATUS) {
            if (Game.currentRoom.rightRoom != null) {
                Game.currentRoom = Game.currentRoom.rightRoom;
                Game.player.increaseHP(2);
                System.out.println("Now you are in : [ " + Game.currentRoom.name + " ]");
                String x = "Available Exit : |";
                if (Game.currentRoom.rightRoom != null) {
                    x = x + " EAST : " + Game.currentRoom.rightRoom.name + " |";
                }
                if (Game.currentRoom.leftRoom != null) {
                    x = x + " WEST : " + Game.currentRoom.leftRoom.name + " |";
                }
                if (Game.currentRoom.upRoom != null) {
                    x = x + " NORTH : " + Game.currentRoom.upRoom.name + " |";
                }
                if (Game.currentRoom.downRoom != null) {
                    x = x + " SOUTH : " + Game.currentRoom.downRoom.name + " |";
                }
                if (Game.currentRoom.downRoom == null
                        && Game.currentRoom.upRoom == null
                        && Game.currentRoom.leftRoom == null
                        && Game.currentRoom.rightRoom == null) {
                    System.out.println("You are trapped!!!");
                    System.out.println("This is a locked room!!!");
                }
                System.out.println(x);
                if (Game.currentRoom.isMonsterExist()) {
                    System.out.println("There is a monster in this room!!!");
                }
            } else {
                System.out.println("There is no door.");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
