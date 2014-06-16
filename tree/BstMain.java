public class BstMain {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();

		bst.add(10);
		bst.add(5);
		bst.add(16);
		bst.add(12);
		bst.add(20);
		
		if(bst.contains(16))
			System.out.println("HIT");
		else
			System.out.println("MISS");

        System.out.println(bst.size());

        //System.out.println("Parent test");
        //System.out.println(bst.checkParent(13));

        //System.out.println("Successor test");
        //bst.successorTest();
        
        System.out.println("Tree iterator test");
        bst.iteratorTest();

		if(bst.contains(7))
			System.out.println("HIT");
		else
			System.out.println("MISS");

        System.out.println(bst.size());

        bst.add(16);
        
        System.out.println(bst.size());

		bst.remove(16);

		if(bst.contains(16))
			System.out.println("HIT");
		else
			System.out.println("MISS");

        System.out.println(bst.size());

		bst.remove(10);

        System.out.println(bst.size());

	}
}
