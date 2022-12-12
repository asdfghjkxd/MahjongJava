package constants;

import java.util.*;

public class VALID_TILE_ACTIONS {
    public static final List<ArrayList<Integer>> ALL_ACTIONS = new LinkedList<>();
    public static final List<ArrayList<Integer>> HEAD_ACTIONS = new LinkedList<>();

    static {
        for (int i = 0; i < 34; i++) {
            ArrayList<Integer> pairBasis = new ArrayList<>(47);
            ArrayList<Integer> thripleBasis = new ArrayList<>(47);
            ArrayList<Integer> quadrupleBasis = new ArrayList<>(47);
            for (int j = 0; j < 47; j++) {
                if (j == i) {
                    pairBasis.add(2);
                    thripleBasis.add(3);
                    quadrupleBasis.add(4);
                } else {
                    pairBasis.add(0);
                    thripleBasis.add(0);
                    quadrupleBasis.add(0);
                }
            }

            HEAD_ACTIONS.add(pairBasis);
            ALL_ACTIONS.add(thripleBasis);
            ALL_ACTIONS.add(quadrupleBasis);
        }

        for (int i = 0; i < 9 - 2; i++) {
            ArrayList<Integer> chowBasis = new ArrayList<>(47);
            for (int j = 0; j < 45; j++) {
                if (j == i) {
                    chowBasis.add(1);
                    chowBasis.add(1);
                    chowBasis.add(1);
                } else {
                    chowBasis.add(0);
                }
            }
            ALL_ACTIONS.add(chowBasis);
        }

        for (int i = 9; i < 18 - 2; i++) {
            ArrayList<Integer> chowBasis = new ArrayList<>(47);
            for (int j = 0; j < 45; j++) {
                if (j == i) {
                    chowBasis.add(1);
                    chowBasis.add(1);
                    chowBasis.add(1);
                } else {
                    chowBasis.add(0);
                }
            }
            ALL_ACTIONS.add(chowBasis);
        }

        for (int i = 18; i < 27 - 2; i++) {
            ArrayList<Integer> chowBasis = new ArrayList<>(47);
            for (int j = 0; j < 45; j++) {
                if (j == i) {
                    chowBasis.add(1);
                    chowBasis.add(1);
                    chowBasis.add(1);
                } else {
                    chowBasis.add(0);
                }
            }
            ALL_ACTIONS.add(chowBasis);
        }

        ArrayList<Integer> basisFor13 = new ArrayList<>(
                Arrays.asList(
                        1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                        )
        );

        for (int i = 0; i < basisFor13.size(); i++) {
            if (basisFor13.get(i) != 0) {
                ArrayList<Integer> newBasis = new ArrayList<>(basisFor13);
                newBasis.set(i, newBasis.get(i) + 1);
                ALL_ACTIONS.add(newBasis);
            }
        }
    }
}
