public class Balanceo{
    public static int evaluacionPost(String args){
        Stack<Integer> pila = new Stack<>();

        String[] tokens = args.split(" ");

        for(String token : tokens){
            if(esOperador(token)){
                int b = pila.pop();
                int a = pila.pop();

                int resultado = operar(a,b,token);
                pila.push(resultado);
            }else{
                pila.push(Integer.parseInt(token));
            }

            
        }
        return pila.pop();

    }

    private static boolean esOperador(String operador){
        return operador.equals("+") ||
        operador.equals("-") || operador.equals("*") || operador.equals("/"); 
    }

    private static int operar(int a, int b, String token){
        switch (token) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;   
            default:
                return 0;
        }
    }
    private static boolean estaBalanceado(String operador){
        Stack<Character> pila = new Stack<>();

        for(int i=0; i<operador.length(); i++){
            char balanceo = operador.charAt(i);
            if(balanceo == '(' || balanceo == '[' || balanceo == '{'){
                pila.push(balanceo);
            }else if(balanceo == ')' || balanceo == ']' || balanceo == '}'){
                if(pila.isEmpty()){
                    return false;
                }
                char actual = pila.pop();
                if(!pareja(actual, balanceo)){
                    return false;
                }
            }
            
        }
        return pila.isEmpty();
    }

    private static boolean pareja(char inicio, char cierre){
        return (inicio == '(' && cierre == ')') ||
               (inicio == '[' && cierre == ']') ||
               (inicio == '{' && cierre == '}');
    }




}