public class Tree2Test {
public static void main(String[] args) {
Tree2 tree2 = new Tree2();


TreeNode2 node = new TreeNode2(3);
tree2.root(node);//���ڵ�
TreeNode2 node1 = new TreeNode2(9);
TreeNode2 node2 = new TreeNode2(20);
TreeNode2 node5 = new TreeNode2(91);
TreeNode2 node6 = new TreeNode2(44);
//public boolean Add(TreeNode2 parent, TreeNode2 child, boolean isLeft)
tree2.Add(node, node1, true);//Ҫ��ָ��Ϊ��ڵ���ҽڵ�
tree2.Add(node, node2, false);
TreeNode2 node3 = new TreeNode2(15);
TreeNode2 node4 = new TreeNode2(7);  
tree2.Add(node2, node3);//Ĭ�ϵ�һ����Ϊ���ӽڵ�
tree2.Add(node2, node4);//Ĭ�ϵڶ�����Ϊ���ӽڵ�
//�������������ӽڵ�
System.out.println("The depth of the tree is:" + tree2.GetDepth());
tree2.Print();
System.out.println("TraversalInOrder:"+tree2.TraversalInOrder());
System.out.println("TraversalPreOrder:"+tree2.TraversalPreOrder());
System.out.println("TraversalPostOrder:"+tree2.TraversalPostOrder());
System.out.println("TraversalLevelOrder:\n"+tree2.TraversalLevelOrder())
;
}
}