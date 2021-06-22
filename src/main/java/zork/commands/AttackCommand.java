package zork.commands;

import zork.Game;

import java.util.List;

public class AttackCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "attack";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.currentRoom.isMonsterExist()) {
            boolean result = game.fight(game.currentRoom.getMonster());
            if (result) {
                game.currentLevel.monsterNum -= 1;
                game.player.gainEXP(game.currentRoom.monster.expDrop);
            }
            else {
                System.out.println("*********You were defeated*********");
            }
        }
        else {
            System.out.println("There is no any monster in this room");
        }
    }
}
