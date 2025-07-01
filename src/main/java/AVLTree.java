public class AVLTree {

  private Node root;
  
  
  public AVLTree(){
    this.root = null;
  
  }

  public int height(Node node){

    if(node == null){
      return 0;
    }
    return node.getHeight();
    
    
  }

  public int getBalance(Node node){
    if (node == null) {
      return 0;
    }
    return height(node.getLeft()) - height(node.getRight());
  }

  public void insert(int value){
    System.out.println("Node a Insertar");
    root = insertRec(root,value);
    
 
  }
  private Node insertRec(Node node, int value){
    if (node == null) {
      Node newNode = new Node(value);
      newNode.setHeight(1);
      System.out.println("Nodo Insetado "+ newNode+" Balance al Incertar = "+getBalance(newNode));
      return newNode;
    }

    if(value < node.getValue()){
      node.setLeft(insertRec(node.getLeft(), value));
    }else if(value > node.getValue()){ 
      node.setRight(insertRec(node.getRight(),value));
    }else{
      return node;
    }
    System.out.println("Nide actual" + node.getValue());
    // ACTUALIZAR LA ALTURA DE ESTE ANCESTRO NODE

    int altura = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    node.setHeight(altura);
    System.out.println("\tAltura = " + altura);


    int balance = getBalance(node);
    System.out.println("\tBalance = " + balance);

    // Caso Izquierda - Izquierda
    if (balance > 1 && value < node.getLeft().getValue()) {
      return rotateRight(node);
    }

// Caso Derecha - Derecha
    if (balance < -1 && value > node.getRight().getValue()) {
      return rotateLeft(node);
    }

// Caso Izquierda - Derecha
    if (balance > 1 && value > node.getLeft().getValue()) {
      node.setLeft(rotateLeft(node.getLeft()));
      return rotateRight(node);
    }

// Caso Derecha - Izquierda
    if (balance < -1 && value < node.getRight().getValue()) {
      node.setRight(rotateRight(node.getRight()));
      return rotateLeft(node);
    }
    

    return node;
  }

  private Node rotateRight(Node y) {
    Node x = y.getLeft();
    Node T2 = x.getRight();

    // Rotar
    x.setRight(y);
    y.setLeft(T2);

    // Actualizar alturas
    y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
    x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

    return x;
  }

  // Rotación simple a la izquierda (caso Derecha-Derecha)
  private Node rotateLeft(Node x) {
    Node y = x.getRight();
    Node T2 = y.getLeft();

    // Rotar
    y.setLeft(x);
    x.setRight(T2);

    // Actualizar alturas
    x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
    y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));

    return y;
  }
  public void balance() {
    root = balanceNode(root);
  }

  private Node balanceNode(Node node) {
    if (node == null) return null;

    int balance = getBalance(node);

    // Caso Izquierda - Izquierda
    if (balance > 1 && getBalance(node.getLeft()) >= 0)
      return rotateRight(node);

    // Caso Derecha - Derecha
    if (balance < -1 && getBalance(node.getRight()) <= 0)
      return rotateLeft(node);

    // Caso Izquierda - Derecha
    if (balance > 1 && getBalance(node.getLeft()) < 0) {
      node.setLeft(rotateLeft(node.getLeft()));
      return rotateRight(node);
    }

    // Caso Derecha - Izquierda
    if (balance < -1 && getBalance(node.getRight()) > 0) {
      node.setRight(rotateRight(node.getRight()));
      return rotateLeft(node);
    }

    return node;
  }
  public int getBalanceRoot() {
    return getBalance(root);
  }
}
