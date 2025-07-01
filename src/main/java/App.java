public class App {

  public static void main(String[] args) {
    AVLTree avlTree = new AVLTree();

    System.out.println("Erick Josue Yunga Chimbo -- Brandon Fernando Rivera Zambrano");

    avlTree.insert(10);
    avlTree.insert(20);
    avlTree.insert(15); // Aquí se va a imprimir el balance -2

    System.out.println("===> Balanceando el árbol...");
    avlTree.balance(); // Se aplica la rotación

    System.out.println("===> Nuevo balance de la raíz: " + avlTree.getBalanceRoot());
  }
}