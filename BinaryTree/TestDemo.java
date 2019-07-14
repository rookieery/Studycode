package BinaryTree;

public class TestDemo {

    public static void main(String[] args) {
        TestBinaryTree testBinaryTree = new TestBinaryTree();
        String str = "abc##de#g##f###";
        TestBinaryTree.TreeNode root = testBinaryTree.createTestTree(str);
        testBinaryTree.binaryTreeInOrder(root);
        System.out.println();
        System.out.println(testBinaryTree.getLeafSize(root));
        System.out.println(testBinaryTree.getKLevelSize(root, 3));
        System.out.println(testBinaryTree.find(root, 'g') == null ? 0 : testBinaryTree.find(root, 'g').val);
        System.out.println(testBinaryTree.height(root));
        testBinaryTree.binaryTreeInOrderNonR(root);
        testBinaryTree.binaryTreePostOrderNonR(root);
    }
}

