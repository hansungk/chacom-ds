public class StackTest {

    public static void main(String[] args) throws Exception {

        Stack<Integer> stack = new StackArray<Integer>();
	
        for (int i = 0; i < 15; i++) {
            if (stack.isEmpty()) {
                stack.push(new Integer((int)(Math.random() * 100)));
                System.out.println("(" + stack.size() + ") PUSH: " + stack);
            }
            else {
                if ((int)(Math.random() * 10) > 4) {
                    stack.push(new Integer((int)(Math.random() * 100)));
                    System.out.println("(" + stack.size() + ") PUSH: " + stack);
                }
                else {
                    Integer io = null;
                    try {
                        io = stack.pop();
                    }
                    catch (ESException e) {
                    }
                    System.out.println("(" + stack.size() + ") POP : " + stack + " " + io);
                }
            }
        }
    }

}
