package main.java.it.polimi.ingsw.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class algoCommonGoalCard {

    public boolean algo0() {
        /*
        riflettere sul concetto di separati
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<String,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put("Red",0);
        propertySatisfiedCounter.put("Blue",0);
        propertySatisfiedCounter.put("lightBlue",0);
        propertySatisfiedCounter.put("Green",0);
        propertySatisfiedCounter.put("White",0);
        propertySatisfiedCounter.put("Yellow",0);

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                if ((library[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col)));
                    String colorToCheck = library[row][col].getColor();

                    if ((library[row+1][col]!=null) && library[row+1][col].getColor().equals(colorToCheck)) {
                        checkedCells.add((row+1)+"_"+(col));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                        continue;
                    }

                    if ((library[row+1][col]!=null) && library[row][col+1].getColor().equals(colorToCheck)) {
                        checkedCells.add((row)+"_"+(col+1));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                        continue;
                    }
                }
            }
        }
        return false;
    }

    public boolean algo1() {
        ArrayList<Position> arr = ArrayList<Position>(new Position(0,0),new Position(1,0),new Position(5,0),new Position(4,0));

        boolean ans = false;
        for(int i=0;i<arr.size();i++) {
            if (!ans) {
                if (i==0) {
                    boolean partial_ans = true;
                    String color = library[0][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[z][z].getColor().equals(color))) {
                            partial_ans = false;
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                } else if (i==1) {
                    boolean partial_ans = true;
                    String color = library[1][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[1+z][z].getColor().equals(color))) {
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                } else if (i==2) {
                    boolean partial_ans = true;
                    String color = library[1][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[1+z][z].getColor().equals(color))) {
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                }
            } else {
                break;
            }
        }
        return ans;
    }

    public boolean algo2() {
        String color = library[0][0].getColor();
        if (color.equals(library[0][4].getColor())
            && color.equals(library[5][4].getColor())
            && color.equals(library[5][0].getColor())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean algo3() {
        int count_row = 0;
        for(int row=0;row<6;row++) {
            HashSet<String> s = new HashSet<>();
            for(int col=0;col<5;col++) {
                s.add(library[row][col].getColor());
            }
            count_row = (s.size()<=3) ? count_row+1 : count_row;
        }

        if (count_row>=4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean algo4() {
        /*
        Vedi Library countAdjacentPoints() ma ti fermi a 4 ed incrementi contatore:
        riflettere sul concetto di separati: cosa succede se abbiamo due strade possibili? cosa succede se ne ho 8 vicine?
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<String,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put("Red",0);
        propertySatisfiedCounter.put("Blue",0);
        propertySatisfiedCounter.put("lightBlue",0);
        propertySatisfiedCounter.put("Green",0);
        propertySatisfiedCounter.put("White",0);
        propertySatisfiedCounter.put("Yellow",0);

        int row = 0;
        int column = 0;
        while (row<getNumberOfRows()) {
            while ((column <getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
                checkedCells.add(row+"_"+column);
                int sameColorNeighboursUp = countSameColorNeighbours(row, column,"Up");
                String colorToCheck = library[row][col].getColor();
                if (sameColorNeighboursUp >= 4) {
                    propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                    if (propertySatisfiedCounter.get(colorToCheck==4)) {
                        return true;
                    }
                } else {
                    int sameColorNeighboursRight = countSameColorNeighbours(row, column,"Right");
                    if ((sameColorNeighboursUp+sameColorNeighboursRight)>=4) {
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==4) {
                            return true;
                        }
                    }
                }

                /* da implementare la verifica dei punti da aggiungere in base al numero di sameColorNeighbours*/
                numberOfAdjacentPoints += addAdjacentPoints(sameColorNeighbours);
                column++;
            }
            column=0;
            row++;
        }
        return false;
    }

    public boolean algo5() {
        int count_col = 0;
        for(int col=0;col<5;col++) {
            HashSet<String> s = new HashSet<>();
            for(int row=0;row<6;row++) {
                s.add(library[row][col].getColor());
            }
            count_col = (s.size()==3) ? count_col+1 : count_col;
        }

        if (count_col>=2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean algo6() {
        /*
        due gruppi separati di 4 tessere dello stesso tipo.
        Idea:
        - Va verificata per tutti i 6 colori, al primo che viene trovato mi fermo. Uso un vettore di contatori di 6 elementi che partono da zero.
        - Parto con una DFS,come per countAdjiacent, e cerco le sequenze con stesso colore (presente,su,destra,giù) che inglobino celle non già incluse in altre sequenze
        - Una volta che ho trovato una sequenza, incremento il contatore di 1 per tale tipo/colore. Se uno raggiunge 2, mi fermo (anche prima di giungere al termine).
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<String,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put("Red",0);
        propertySatisfiedCounter.put("Blue",0);
        propertySatisfiedCounter.put("lightBlue",0);
        propertySatisfiedCounter.put("Green",0);
        propertySatisfiedCounter.put("White",0);
        propertySatisfiedCounter.put("Yellow",0);

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                if ((library[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col));
                    String colorToCheck = library[row][col].getColor();
                    if ((library[row+1][col] != null) && (library[row+1][col].getColor().equals(colorToCheck))) {
                        if ((library[row+1][col+1] != null) && (library[row+1][col+1].getColor().equals(colorToCheck))) {
                            if ((library[row][col+1] != null) && (library[row][col+1].getColor().equals(colorToCheck))) {
                                checkedCells.add((row+1)+"_"+(col));
                                checkedCells.add((row+1)+"_"+(col+1));
                                checkedCells.add((row)+"_"+(col+1));
                                propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);

                                if (propertySatisfiedCounter.get(colorToCheck)==2) {
                                    return true;
                                }
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean algo7() {
        int count_row = 0;
        for(int row=0;row<6;row++) {
            HashSet<String> s = new HashSet<>();
            for(int col=0;col<5;col++) {
                s.add(library[row][col].getColor());
            }
            count_row = (s.size()==5) ? count_row+1 : count_row;
        }

        if (count_row>=2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean algo8() {
        int count_col = 0;
        for(int col=0;col<5;col++) {
            HashSet<String> s = new HashSet<>();
            for(int row=0;row<6;row++) {
                s.add(library[row][col].getColor());
            }
            count_col = (s.size()==3) ? count_col+1 : count_col;
        }

        if (count_col>=3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean algo9() {
        /*
        Cinque tessere delle stesso tipo che formano una x.
        Idea:
        - Verifico usando come punto di partenza la cella centrale della stella.
        - sicuramente la ricerca è ristretta al riquadro interno (5x4) per le celle centrali. Poiche quelle nei lati esterni non possono avere la stella completa.
        - Itero su tutte le celle del riquadro interno cercando la formazione a stella, mi fermo una volta trovata oppure quando sono finite le celle.
        Sarà poi da aggiungere la verifica che la carta sia effettivamente presente (nel senso library[row][col] != null
         */
        for(int row=1;row<=5;row++) {
            for (int col=1;col<=4;col++) {
                String centralCellColor = library[row][col].getColor();
                if (library[row+1][col-1].getColor().equals(centralCellColor)) {
                    if (library[row-1][col-1].getColor().equals(centralCellColor)) {
                        if (library[row+1][col+1].getColor().equals(centralCellColor)) {
                            if (library[row-1][col+1].getColor().equals(centralCellColor)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
