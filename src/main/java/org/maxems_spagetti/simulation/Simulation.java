package org.maxems_spagetti.simulation;

import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.MapRenderer;
import org.maxems_spagetti.actions.InitActions;
import org.maxems_spagetti.actions.TurnActions;

public class Simulation {

    private static Map map = new Map();

    private static MapRenderer renderer = new MapRenderer();

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        InitActions initActions = new InitActions(map);
        map = initActions.fillMap();

        renderer.render(map);
        simulation.startSimulation();
    }


    public void nextTurn(TurnActions turnActions, Map map) {
        turnActions.turnActions();
        renderer.render(map);
    }

    public void  startSimulation() {
        TurnActions turnActions = new TurnActions(map);
        int i = 7;
        while (i >= 0) {
            System.out.println();
            nextTurn(turnActions, map);

            System.out.println();
            System.out.println();
            i--;
        }
    }

    public void pauseSimulation() {

    }
}
