public class Spinner implements Runnable {
    private final String label;
    private final int cycles;
    private final long delay;

    public Spinner(String label) {
        this(label, 5, 200);
    }

    public Spinner(String label, int cycles, long delay) {
        this.label = label == null ? "" : label;
        this.cycles = Math.max(1, cycles);
        this.delay = Math.max(20, delay);
    }

    @Override
    public void run() {
        try {
            System.out.print(label);
            for (int i = 0; i < cycles; i++) {
                Thread.sleep(delay);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("\n" + label + " interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
