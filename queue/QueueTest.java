public class QueueTest {

    public static void main(String[] args) throws Exception {

        Queue<Integer> queue = new QueueArray<Integer>();
	
        for (int i = 0; i < 15; i++) {
            if (queue.isEmpty()) {
                queue.enqueue(new Integer((int)(Math.random() * 100)));
                System.out.println("(" + queue.size()
                                   + ") E: " + queue);
            }
            else {
                if ((int)(Math.random() * 10) > 4) {
                    queue.enqueue(new Integer((int)(Math.random() * 100)));
                    System.out.println("(" + queue.size()
                                       + ") E: " + queue);
                }
                else {
                    Integer io = null;
                    try {
                        io = queue.dequeue();
                    }
                    catch (EQException e) {
                    }
                    System.out.println("(" + queue.size() +
                                       ") D: " + queue + " " + io);
                }
            }
        }
    }

}
