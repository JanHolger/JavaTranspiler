public class Test {

    private static Test test = new Test();

    public static void main(String[] args) {
        try {
            test.test(args);
        } catch (RuntimeException ex) {
            System.out.println("Ein Fehler");
        }
    }

    public void test(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Fehler");
        System.out.println(args[0]);
    }

}