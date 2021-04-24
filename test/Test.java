public class Test {

    private static Test test = new Test();

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Fehler");
        test.test(args[0]);
    }

    public void test(String text) {
        System.out.println(text);
    }

}