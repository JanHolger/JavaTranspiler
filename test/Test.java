public class Test {

    private static Test test = new Test();

    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        if(a > b)
            System.out.println("A is greater than B");
        System.out.println(a / b);
        try {
            test.test(args);
        } catch (RuntimeException ex) {
            System.out.println("We catched an exception (:");
        }
    }

    public void test(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Some Exception :D");
        System.out.println(args[0]);
    }

}