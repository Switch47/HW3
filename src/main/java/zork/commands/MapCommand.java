package zork.commands;

import zork.Game;
import java.io.IOException;
import java.util.List;

public class MapCommand implements Command{


    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "map";
    }

    @Override
    public void execute(Game game, List<String> argument) throws IOException {
        if (Game.getGameStatus() == Game.PLAY_STATUS) {
            System.out.println("!!!PLEASE READ!!!");
            System.out.println(" [ X ] -> WALL");
            System.out.println(" [ |,---- ] -> DOOR");
            System.out.println();
            if (Game.currentLevel.name.equals("EvilHouse")) {
                System.out.println("============================");
                System.out.println("         EVILHOUSE          ");
                System.out.println("----------------------------");
                System.out.println("|XXXXXXXXXXXXXXXX          |");
                System.out.println("|X G3 | G2 | G1 X  OutSide |");
                System.out.println("|XXXXXXXXXXX----X----XXXXXX|");
                System.out.println("|X Ba | Ki | Li | Ha X Ba X|");
                System.out.println("|X----XXXXXXXXXXX----X----X|");
                System.out.println("|X Be | Ha | Ha | Ha | Be X|");
                System.out.println("|XXXXXXXXXXX----X----XXXXXX|");
                System.out.println("|     X Ba | Be X Ga | Ga X|");
                System.out.println("|     XXXXXXXXXXXXXXXXXXXXX|");
                System.out.println("----------------------------");
                System.out.println("============================");
            }
            else if (Game.currentLevel.name.equals("ATutorial")) {
                System.out.println("==================");
                System.out.println("     ATUTORIAL    ");
                System.out.println("------------------");
                System.out.println("|XXXXXXXXXXXXXXXX|");
                System.out.println("|X R3 | R2 | R1 X|");
                System.out.println("|XXXXXXXXXXXXXXXX|");
                System.out.println("------------------");
                System.out.println("==================");
            }
            else if (Game.currentLevel.name.equals("Bloodborne")) {
                System.out.println("=================================");
                System.out.println("            BLOODBORNE           ");
                System.out.println("---------------------------------");
                System.out.println("|     XXXXXX                    |");
                System.out.println("|     X PG X                    |");
                System.out.println("|XXXXXX----XXXXXX    XXXXXXXXXXX|");
                System.out.println("|X P1 | R5 | PF X    X LR | KC X|");
                System.out.println("|X----X----X----XXXXXX----XXXXXX|");
                System.out.println("|X R3 | R4 | R6 | G2 | H1 | Ba X|");
                System.out.println("|X----XXXXXXXXXXXXXXXX----XXXXXX|");
                System.out.println("|X G1 X              X H2 | Be X|");
                System.out.println("|X----XXXXXXXXXXX    X----XXXXXX|");
                System.out.println("|X R2 | R1 | S1 X    X H3 X     |");
                System.out.println("|XXXXXX----XXXXXXXXXXX----X     |");
                System.out.println("|     X HH X G2 | G1 | S2 X     |");
                System.out.println("|     XXXXXXXXXXXXXXXXXXXXX     |");
                System.out.println("---------------------------------");
                System.out.println("=================================");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }

}
