import java.io.IOException;

public class Tester {

    private Map map1;
    private Map map2;

    Tester() {
        this.map1 = new Map();
        this.map2 = new Map();
    }

    public boolean testAddLivingCell() {
        LivingCell lcell1 = new LivingCell(0,0, this.map1, false);
        DeadCell   dcell1 = new DeadCell(-1,-1, this.map2, false);
        DeadCell   dcell2 = new DeadCell(0,-1, this.map2, false);
        DeadCell   dcell3 = new DeadCell(1,-1, this.map2, false);
        DeadCell   dcell4 = new DeadCell(-1,0, this.map2, false);
        DeadCell   dcell5 = new DeadCell(1,0, this.map2, false);
        DeadCell   dcell6 = new DeadCell(-1,1, this.map2, false);
        DeadCell   dcell7 = new DeadCell(0,1, this.map2, false);
        DeadCell   dcell8 = new DeadCell(1,1, this.map2, false);

        return (this.map1.getBox(-1,-1).equals(dcell1) &&
                this.map1.getBox(0,-1).equals(dcell2) &&
                this.map1.getBox(1,-1).equals(dcell3) &&
                this.map1.getBox(-1,0).equals(dcell4) &&
                this.map1.getBox(1,0).equals(dcell5) &&
                this.map1.getBox(-1,+1).equals(dcell6) &&
                this.map1.getBox(0,+1).equals(dcell7) &&
                this.map1.getBox(1,+1).equals(dcell8));
    }

    public static void main(String args[]) {
        Map map1 = new Map();
        Map map2 = new Map();
        Map map3 = new Map();
        GameDriver game = new GameDriver();

        LivingCell lcell1 = new LivingCell(0,0, map1, false);
        DeadCell   dcell1 = new DeadCell(-1,-1, map2, false);
        DeadCell   dcell2 = new DeadCell(0,-1, map2, false);
        DeadCell   dcell3 = new DeadCell(1,-1, map2, false);
        DeadCell   dcell4 = new DeadCell(-1,0, map2, false);
        DeadCell   dcell5 = new DeadCell(1,0, map2, false);
        DeadCell   dcell6 = new DeadCell(-1,1, map2, false);
        DeadCell   dcell7 = new DeadCell(0,1, map2, false);
        DeadCell   dcell8 = new DeadCell(1,1, map2, false);

        System.out.println("1: " + (map1.getBox(-1,-1).equals(dcell1) &&
                map1.getBox(0,-1).equals(dcell2) &&
                map1.getBox(1,-1).equals(dcell3) &&
                map1.getBox(-1,0).equals(dcell4) &&
                map1.getBox(1,0).equals(dcell5) &&
                map1.getBox(-1,+1).equals(dcell6) &&
                map1.getBox(0,+1).equals(dcell7) &&
                map1.getBox(1,+1).equals(dcell8)));

        DeadCell _dcell1 = (DeadCell) map1.getBox(-1,-1);
        DeadCell _dcell2 = (DeadCell) map1.getBox(0,-1);
        DeadCell _dcell3 = (DeadCell) map1.getBox(1,-1);
        DeadCell _dcell4 = (DeadCell) map1.getBox(-1,0);
        DeadCell _dcell5 = (DeadCell) map1.getBox(1,0);
        DeadCell _dcell6 = (DeadCell) map1.getBox(-1,1);
        DeadCell _dcell7 = (DeadCell) map1.getBox(0,1);
        DeadCell _dcell8 = (DeadCell) map1.getBox(1,1);

        System.out.println("2: " + (_dcell1.getNumberAliveNeighbors() == 1 &&
                _dcell2.getNumberAliveNeighbors() == 1 &&
                _dcell3.getNumberAliveNeighbors() == 1 &&
                _dcell4.getNumberAliveNeighbors() == 1 &&
                _dcell5.getNumberAliveNeighbors() == 1 &&
                _dcell6.getNumberAliveNeighbors() == 1 &&
                _dcell7.getNumberAliveNeighbors() == 1 &&
                _dcell8.getNumberAliveNeighbors() == 1));

        LivingCell _lcell1 = new LivingCell(1,0, map3, false);
        LivingCell _lcell2 = new LivingCell(0,1, map3, false);
        LivingCell _lcell3 = new LivingCell(-1,0, map3, false);
        LivingCell _lcell4 = new LivingCell(0,-1, map3, false);

        DeadCell __dcell1 = (DeadCell)map3.getBox(0,0);
        System.out.println("3: " + (__dcell1.getNumberAliveNeighbors() == 4));

        System.out.println("4: " + (map3.getLivingCells().size() == 4));
        System.out.println("5: " + (map3.getDeadCells().size() == 17));

        System.out.println("6: " + (map3.getLivingCells().size() == 4 &&
                map3.getDeadCells().size() == 17 &&
                map3.getBox(-1,2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(0,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(1,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(-2,1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(0,1).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(1,1).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(2,1).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(-2,0).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,0).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(0,0).getNumberAliveNeighbors()   == 4 &&
                map3.getBox(1,0).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(2,0).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(-2,-1).getNumberAliveNeighbors() == 1 &&
                map3.getBox(-1,-1).getNumberAliveNeighbors() == 2 &&
                map3.getBox(0,-1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(1,-1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(2,-1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,-2).getNumberAliveNeighbors() == 1 &&
                map3.getBox(0,-2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(1,-2).getNumberAliveNeighbors()  == 1));

        map3.livingCellDies(1,0);

        System.out.println("7: " + (map3.getLivingCells().size() == 3 &&
                map3.getDeadCells().size() == 18 &&
                map3.getBox(-1,2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(0,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(1,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(-2,1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(0,1).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(1,1).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(2,1).getNumberAliveNeighbors()   == 0 &&
                map3.getBox(-2,0).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,0).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(0,0).getNumberAliveNeighbors()   == 3 &&
                map3.getBox(1,0).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(2,0).getNumberAliveNeighbors()   == 0 &&
                map3.getBox(-2,-1).getNumberAliveNeighbors() == 1 &&
                map3.getBox(-1,-1).getNumberAliveNeighbors() == 2 &&
                map3.getBox(0,-1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(1,-1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(2,-1).getNumberAliveNeighbors()  == 0 &&
                map3.getBox(-1,-2).getNumberAliveNeighbors() == 1 &&
                map3.getBox(0,-2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(1,-2).getNumberAliveNeighbors()  == 1));

        map3.livingCellBorn(0, 0);

        System.out.println("8: " + (map3.getLivingCells().size() == 4 &&
                map3.getDeadCells().size() == 17 &&
                map3.getBox(-1,2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(0,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(1,2).getNumberAliveNeighbors()   == 1 &&
                map3.getBox(-2,1).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,1).getNumberAliveNeighbors()  == 3 &&
                map3.getBox(0,1).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(1,1).getNumberAliveNeighbors()   == 2 &&
                map3.getBox(2,1).getNumberAliveNeighbors()   == 0 &&
                map3.getBox(-2,0).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(-1,0).getNumberAliveNeighbors()  == 3 &&
                map3.getBox(0,0).getNumberAliveNeighbors()   == 3 &&
                map3.getBox(1,0).getNumberAliveNeighbors()   == 3 &&
                map3.getBox(2,0).getNumberAliveNeighbors()   == 0 &&
                map3.getBox(-2,-1).getNumberAliveNeighbors() == 1 &&
                map3.getBox(-1,-1).getNumberAliveNeighbors() == 3 &&
                map3.getBox(0,-1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(1,-1).getNumberAliveNeighbors()  == 2 &&
                map3.getBox(2,-1).getNumberAliveNeighbors()  == 0 &&
                map3.getBox(-1,-2).getNumberAliveNeighbors() == 1 &&
                map3.getBox(0,-2).getNumberAliveNeighbors()  == 1 &&
                map3.getBox(1,-2).getNumberAliveNeighbors()  == 1));

        // Blinker 2-Period
//        game.addLivingCell(-1,0);
//        game.addLivingCell(0,0);
//        game.addLivingCell(1,0);

        // Glider
//        game.addLivingCell(0,1);
//        game.addLivingCell(1,0);
//        game.addLivingCell(2,0);
//        game.addLivingCell(2,1);
//        game.addLivingCell(2,2);

        // Pulsar
        game.addLivingCell(3,1);
        game.addLivingCell(4,1);
        game.addLivingCell(5,1);
        game.addLivingCell(9,1);
        game.addLivingCell(10,1);
        game.addLivingCell(11,1);
        game.addLivingCell(1,3);
        game.addLivingCell(1,4);
        game.addLivingCell(1,5);
        game.addLivingCell(6,3);
        game.addLivingCell(6,4);
        game.addLivingCell(6,5);
        game.addLivingCell(8,3);
        game.addLivingCell(8,4);
        game.addLivingCell(8,5);
        game.addLivingCell(13,3);
        game.addLivingCell(13,4);
        game.addLivingCell(13,5);
        game.addLivingCell(3,6);
        game.addLivingCell(4,6);
        game.addLivingCell(5,6);
        game.addLivingCell(9,6);
        game.addLivingCell(10,6);
        game.addLivingCell(11,6);
        game.addLivingCell(3,8);
        game.addLivingCell(4,8);
        game.addLivingCell(5,8);
        game.addLivingCell(9,8);
        game.addLivingCell(10,8);
        game.addLivingCell(11,8);
        game.addLivingCell(1,9);
        game.addLivingCell(1,10);
        game.addLivingCell(1,11);
        game.addLivingCell(6,9);
        game.addLivingCell(6,10);
        game.addLivingCell(6,11);
        game.addLivingCell(8,9);
        game.addLivingCell(8,10);
        game.addLivingCell(8,11);
        game.addLivingCell(13,9);
        game.addLivingCell(13,10);
        game.addLivingCell(13,11);
        game.addLivingCell(3,13);
        game.addLivingCell(4,13);
        game.addLivingCell(5,13);
        game.addLivingCell(9,13);
        game.addLivingCell(10,13);
        game.addLivingCell(11,13);

//        System.out.println("9: " + (game.getMinXValue() == -2 &&
//                game.getMinYValue() == -1 &&
//                game.getMaxXValue() ==  2 &&
//                game.getMaxYValue() ==  1));

//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
//        game.step();
//        game.printMap();
        try {
            game.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
