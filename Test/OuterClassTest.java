public class OuterClassTest {
    int var1 = 2;

    public class InnerClassTest {
        public InnerClassTest() {
            System.out.println("var 1: " + OuterClassTest.this.var1);
        }
    }

    public static void main(String[] args) {
        OuterClassTest oct = new OuterClassTest();
        OuterClassTest.InnerClassTest ict = oct.new InnerClassTest();
    }
}