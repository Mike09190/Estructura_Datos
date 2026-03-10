public class Adriancito {
    public static void main(String[] args) {
        Gerardozzz<String> pila = new Gerardozzz<>();

        pila.push("SalvaGoat");
        pila.push("Pinguino");
        pila.push("Antonio");

        pila.show();
        System.out.println("El tope es: " + pila.top());
        System.out.println("------------------");
        pila.pop();
        pila.show();
        pila.clear();
        System.out.println("------------ \n ");
        pila.show();
    }
}
