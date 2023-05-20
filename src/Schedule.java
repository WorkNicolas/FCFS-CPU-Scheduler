import java.util.*;

public class Schedule extends Thread {
    Schedule() {

    }
    public Process createProcess(int id) {
        Process p = new Process(id);
        return p;
    }
    //scanner
    public void createSchedule() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        Process[] p;

        //Initialize processes
        while (true) {
            System.out.print("Number of Processes: ");
            int input = Integer.valueOf(sc.nextLine());
            if (input > 0) {
                p = new Process[input];
                break;
            } else {
                System.out.println("Input must be greater than 0");
            }
        }

        for (int i = 0; i < p.length; i++) {
            p[i] = createProcess(i+1);
        }

        //CPU Scheduling Menu
        while (!exit) {
            System.out.println("Creating new CPU schedule");
            System.out.println("Choose CPU schedule");
            System.out.println("[1] First-Come-First-Serve");

            switch(Integer.valueOf(sc.nextLine())) {
                case 1:
                    System.out.println("Input: First-Come-First-Serve");
                    fcfs(p);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }
    public void fcfs(Process[] p) {
        displayProcessInfo(p, false);
        displayGanttChart(p);
    }
    public void displayGanttChart(Process[] p) {
        ANSI_Colors color = new ANSI_Colors();

        System.out.println("Gantt Chart");
        int rand, tempRand = -1;
        for (int i = 0; i < p.length; i++) {

            //Color Randomizer
            do {
                rand = color.colorBackgroundRandomizer();
            } while (rand == tempRand);

            tempRand = rand;
            for (int j = 0; j <= p[i].getBt(); j++) {
                //Display Loop
                String processColor = color.COLOR_BG_ARRAY[rand];
                System.out.print(processColor + p[i].toString());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    public void displayProcessInfo(Process[] p, boolean prioritized) {
        //Process Info
        System.out.print("Process\tArrival Time\tBurst Time\tFinishing Time\tTurnaround Time\tWaiting Time\tResponse Time");
        if (prioritized) {
            System.out.print("\tPriority");
        }
        System.out.println();
        for (int i = 0; i < p.length; i++) {
            if (i > 0) {
                p[i].startCalculations(true, p[i - 1].getWt(), p[i - 1].getBt());
            } else {
                p[i].startCalculations(false, 0, 0);
            }
            System.out.print("|P" + p[i].getPid() + "|" + "\t" + p[i].getAt() + "\t\t\t\t" + p[i].getBt() + "\t\t\t" + p[i].getFt() + "\t\t\t\t" + p[i].getTat() + "\t\t\t\t" + p[i].getWt() + "\t\t\t\t" + Math.abs(p[i].getFt() - p[i].getAt()));
            if (prioritized) {
                System.out.print("\t\t\t\t" + p[i].getPr());
            }
            System.out.println();
        }
        //Mean
        //Calculate Waiting Time Mean
        float wt_mean = 0;
        for (int i = 0; i < p.length; i++) {
            wt_mean += p[i].getWt();
        }
        wt_mean /= p.length;
        System.out.printf(Locale.US, "Average Waiting Time: %.2f ms\n", wt_mean);

        //Calculate Turnaround Time Mean
        float tat_mean = 0;
        for (int i = 0; i < p.length; i++) {
            tat_mean += p[i].getTat();
        }
        tat_mean /= p.length;
        System.out.printf(Locale.US, "Average Turnaround Time: %.2f ms\n", tat_mean);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {

        }
    }
}
