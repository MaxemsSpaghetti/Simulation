package org.maxems_spagetti.simulation;

import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.MapRenderer;
import org.maxems_spagetti.actions.InitActions;
import org.maxems_spagetti.actions.TurnActions;

import java.util.Scanner;


public class Simulation {

    private static Map map = new Map();

    private static final MapRenderer renderer = new MapRenderer();

    private final Scanner scanner = new Scanner(System.in);

    private volatile boolean isSimulationPaused = false;

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        InitActions initActions = new InitActions(map);
        map = initActions.fillMap();

        renderer.render(map);
        simulation.startSimulation();
    }


    private void nextTurn(TurnActions turnActions, Map map) {
        turnActions.turnActions();
        renderer.render(map);
    }

    private void  startSimulation() {
        Thread thread = new Thread(() -> {
            TurnActions turnActions = new TurnActions(map);
            while (true) {
                if (!isSimulationPaused) {
                    System.out.println();
                    nextTurn(turnActions, map);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        thread.start();

        System.out.println("If you want to pause the simulation, press 'p'");
        System.out.println("If you want to resume the simulation, press 'r'");
        System.out.println("If you want to quit the simulation, press 'q'");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("p")) {
                pauseSimulation();
            } else if (input.equalsIgnoreCase("r")) {
                resumeSimulation();
            } else if (input.equalsIgnoreCase("q")) {
                endSimulation();
            }
        }
    }

    private void resumeSimulation() {
        isSimulationPaused = false;
        System.out.println("Simulation resumed");
    }

    private void pauseSimulation() {
        isSimulationPaused = true;
        System.out.println("Simulation paused");
    }

    private void endSimulation() {
        System.out.println("Simulation ended");
        scanner.close();
        System.exit(0);
    }
}
