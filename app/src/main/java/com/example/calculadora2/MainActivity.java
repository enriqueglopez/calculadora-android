package com.example.calculadora2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    /**
     * Variables globales:
     *
     * -float valor: Es el almacén temporal de datos. Almacena en float el input que vamos poniendo en la calculadora.
     * -String stringValor: La variable que almacena la operación COMPLETA en String Ej: 15+1/2x5 ...
     * -String stringIntermedio: Este string almacena cada BLOQUE de operación y luego se reincia cada vez que se pulsa un operador. Es actualizado desde el metodo cambiarCifra().
     * -String operador: Almacena el valor del operador que se pulsa.
     * -String ultimoPulsado: Almacena el valor del ultimo operador que se pulsó, para saber qué hacer con los siguientes números que se introduzcan.
     * -TextView textoSuperior: el input superior. Simplemente muestra las operaciones que se van haciendo. Siempre será string y no se hace nada más con el.
     * -TextView textoInferior: el resultado en TextView donde se muestra el resultado de las operaciones. No sale nada hasta pulsar =
     * -float resultado: El resultado en float de las operaciones. Es el valor que mostrará textoInferior cuando se pulse =.
     *
     * @author Enrique_Guajardo_López
     * @since 20-11-2020
     * @version 1.0
     *
     */

    float valor;
    String stringValor;
    String stringIntermedio;
    float resultado;

    String operador;
    
    String ultimoPulsado = ""; //Almacena el dato de qué operador fue el ultimo que se pulsó
    TextView textoSuperior; //El string temporal superior que guarda el historico
    TextView textoInferior; //El resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Enrique Guajardo López 2.2 DAM", Toast.LENGTH_SHORT).show();

        //Declaramos las variables que vamos a utilizar

        valor = 0; resultado = 0; operador = ""; stringValor = ""; stringIntermedio = "";

        textoSuperior = findViewById(R.id.textoSuperior);
        textoInferior = findViewById(R.id.resultado);

        //Declaramos los botones de operaciones

        Button botonAns = findViewById(R.id.botonInfo); Button botonSumar = findViewById(R.id.botonSumar); Button botonRestar = findViewById(R.id.botonRestar);
        Button botonDividir = findViewById(R.id.botonDividir); Button botonMultiplicar = findViewById(R.id.botonMultiplicar); Button botonModulo = findViewById(R.id.botonModulo);
        Button botonPunto = findViewById(R.id.botonPunto); Button botonResultado = findViewById(R.id.botonResultado); Button botonBorrar = findViewById(R.id.botonBorrar);

        //Declaramos los botones de numeros

        Button numero0 = findViewById(R.id.numero0); Button numero1 = findViewById(R.id.numero1); Button numero2 = findViewById(R.id.numero2); Button numero3 = findViewById(R.id.numero3);
        Button numero4 = findViewById(R.id.numero4); Button numero5 = findViewById(R.id.numero5); Button numero6 = findViewById(R.id.numero6); Button numero7 = findViewById(R.id.numero7);
        Button numero8 = findViewById(R.id.numero8); Button numero9 = findViewById(R.id.numero9);

        //Añadimos los listeners de los operadores

        /**
         * Saca el resultado de la operacion anterior si es que existe (si es equals 0 significará que aun no se ha realizado ninguna por lo que
         * ignoramos esta parte del código). Por el contrario, si hubo una, copiamos el valor del texto inferior al texto superior. Almacenamos en la variable float el valor de dicho texto,
         * y copiamos dentro del stringValor el valor del texto superior. Copiamos también el valor de stringValor a stringIntermedio para usarlo en las siguientes funciones.
         */
        botonAns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String res = (String) textoInferior.getText();
                //Si el texto del resultado es distinto de 0, es decir, no es el original
                if (!textoInferior.getText().equals("0")){
                    textoSuperior.setText(textoInferior.getText());
                    valor = Float.parseFloat((String) textoInferior.getText());
                    stringValor = (String) textoSuperior.getText();
                    stringIntermedio = stringValor;
                }
                /**
                 * Debug usado para comprobar cuantos bloques de operaciones hay en cada momento. Se puede descomentar para comprobar, imprime un toast con dicho numero.
                 * int debug = getStringSize();
                 * Toast.makeText(MainActivity.this, String.valueOf(debug), Toast.LENGTH_SHORT).show();
                 */

            }
        });

        botonSumar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonRestar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonDividir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonMultiplicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonModulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });


        botonBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                borrarTodo(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonResultado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacion(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        botonPunto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        //Añadimos los listeners de los numeros

        numero0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });

        numero9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarCifra(v); //Llama al metodo operacion y le pasa la view.
            }
        });
    }

    /**
     * Método que realiza la operación sacando el operador desde el atributo "tag" del botón.
     * Suma el operador al string completo de la operación, introduce el valor del stringIntermedio en float dentro de la variable valor y reinicia la variable stringIntermedio.
     * Se cambia el texto superior al string total que tenemos almacenado con todas las operaciones que se han ido realizando y llama al método realizarCuenta() pasandole como parametro
     * el view y el operador.
     *
     * En caso de pulsar un operador sin haber introducido ningún número arrojará la excepción que se contempla y no se hará nada hasta que el usuario meta un número primero.
     * @param view
     */
    public void operacion(View view){
        try {
            //Tomamos el valor en string del operador. Está contenido en el TAG del view recibido por parámetro.
            operador = (String) view.getTag();

            //Esto pinta el operador en nuestra variable StringValor.
            stringValor += operador;

            valor = Float.parseFloat(stringIntermedio); //Esto guarda en nuestra variable valor el input que vino en string
            stringIntermedio = ""; //Esto reinicia la variable intermedia

            //Se cambia el texto del input para añadirle el operador
            TextView texto = findViewById(R.id.textoSuperior);
            texto.setText(stringValor);

            //Llamamos a la funcion realizarCuenta
            realizarCuenta(view, operador);
        } catch (Exception e){
            Log.e("EXCEPTION ", String.valueOf(e));
        }
    }

    /**
     * En el método anterior hemos controlado que no pase nada si el usuario pulsa un operador sin haber pulsado antes un número por lo que no debemos comprobar eso de nuevo aquí.
     * Guarda en un entero el retorno de getStringSize() que devuelve la longitud de los bloques de la operación.
     * Crea una variable string para almacenar el selector, que puede ser o el pulsado anteriormente o el operador actual, según si la longitud de los bloques es 2 o más.
     * Si es 2, significará que es una operacion A operador B, por lo que usará el operador actual para operar.
     * Si es mayor de 2, significará que es una operacion A operador B operador C ... y usará el último operador pulsado para sacar el valor de la actual operación. A+B-C este código hará B-C.
     *
     * Se ejecuta un switch que realiza la operación del valor sobre el resultado final, con algunas comprobaciones comentadas dentro.
     *
     * @param view
     * @param operador
     */
    public void realizarCuenta(View view, String operador){
        int longitud = getStringSize(); //La longitud de la operacion
        String selector; //Esta variable almacena con que vamos a entrar en el switch. El operador actual o el ultimo que se pulsó
            /**
             * Si la longitud es igual a 2, significa que hay solamente un operador en la suma. Por ello, hacemos el switch con el operador actual en vez de con el ultimoPulsado. Esto evita que la aplicaciones
             * tenga fallos de calculo. Sin esto, 10+10-5 daria 5 porque estaria capturando 10-5 saltandose una. Cuando la operacion es mas larga este fallo no sucede.
             */
            if (longitud == 2)
                selector = operador;
            else
                selector = ultimoPulsado;

            switch(selector){
                case "+":
                    resultado+=valor;
                    ultimoPulsado = operador;
                    break;
                /**
                 * Con las restas, multiplicaciones y divisiones hay que comprobar que el resultado no sea 0 antes de nada, puesto que hacer una resta si el resultado es 0 hará que el numero A pase a ser negativo con el
                 * valor B en vez de A-B, multiplicar por 0 devuelve un 0 y no se puede dividir entre 0 tampoco
                 */
                case "-":
                    if (resultado != 0){
                        resultado-=valor;
                    }
                    else {
                        resultado = valor;
                    }
                    ultimoPulsado = operador;
                    break;
                case "x":
                    if (resultado != 0){
                        resultado*=valor;
                    }
                    else
                    {
                        resultado = valor;
                    }
                    ultimoPulsado = operador;
                    break;
                case "/":
                    if (resultado != 0){
                        resultado/=valor;
                    }
                    else
                    {
                        resultado = valor;
                    }
                    ultimoPulsado = operador;
                    break;
                case "%":
                    if(resultado>=valor){
                        resultado%=valor;
                    }
                    else {
                        resultado=valor;
                    }
                    ultimoPulsado = operador;
                    break;
                case "=":
                    /**
                     * Cuando se pulsa el = se deben reiniciar todos los valores a excepcion del resultado., que se debe calcular llamando a esta propia función de nuevo. Esto es porque nosotros al hacer
                     * A+B+C= aún no hemos calculado lo que vale C. Este código hace que se llame a la propia función y ejecute el último pulsado, que en este caso sería el + y entonces en el switch pasará
                     * por el case correspondiente, asignandole el resultado final. Fuera del switch se realiza la comprobación de si el operador actual es el = y de ser así, reinicia.
                     *
                     */
                    realizarCuenta(view, ultimoPulsado);
                    break;
            }
            /**
             * Si el operador es = cambiamos el texto superior a 0 para vaciarlo, asignamos el texto inferior al resultado de la operación y reiniciamos variables.
             */
            if (operador.equals("="))
            {
                    textoSuperior.setText("0");
                    textoInferior.setText(String.valueOf(resultado)); //Pintamos el resultado
                    valor = 0; resultado = 0; stringValor = "0"; stringIntermedio = ""; //Reiniciamos variables
            }
            valor = 0; //Cambiamos valor a 0
            //Log.i("StringTotal:",stringValor); //Debug
            //Log.i("Valor:",String.valueOf(valor)); //Debug
            //Log.i("Resultado:",String.valueOf(resultado)); //Debug
    }

    /**
     * Este método cambia la cifra cada vez que el usuario pulsa un botón. Almacena en String el atributo tag del botón que se pulsa.
     * Suma al stringIntermedio dicho numero y almacena el resultado en string el valor del texto superior.
     *
     * Si es igual a 0 el nuevo texto superior será igual al número que se ha insertado. Esto hace que por defecto la apliación tenga un 0 y si el usuario pulsa 5 se cambie por 5, en vez de tener 05.
     * Si no es igual a 0, el texto superior será el almacenado anteriormente y el nuevo.
     *
     * Se actualiza el valor de stringValor (la variable de texto total) por el valor del texto superior.
     * @param view
     */
    public void cambiarCifra(View view){
        String numeroStr = (String) view.getTag(); //Esto captura el valor del tag del boton (o el punto) cuando se pulse
        stringIntermedio += numeroStr; //Esto suma a nuestra variable intermedia el numero que se haya pulsado

        TextView texto = findViewById(R.id.textoSuperior);
        String res = (String) texto.getText(); //Del objeto texto, saca solamente la informacion que contiene

        //Si el numero era solamente 0, lo borra y escribe el nuevo.
        if (res.equals("0")){
            texto.setText(numeroStr);
        }
        else {
            texto.setText(res+numeroStr); //Si no, coge lo anterior y lo suma.
        }
        //En cualquier caso, guardamos el valor de Texto en nuestra variable stringValor.
        stringValor = (String) texto.getText(); //Guarda en la variable stringValor
    }

    /**
     * Reinicia todas las variables internas del programa y las visuales también.
     * @param view
     */
    public void borrarTodo(View view) {
        TextView textoInferior = findViewById(R.id.resultado);
        TextView textoSuperior = findViewById(R.id.textoSuperior);
        //Reiniciamos los inputs visuales
        textoInferior.setText("0"); textoSuperior.setText("0");

        //Reiniciamos las variables internas
        valor = 0; operador = ""; resultado = 0; stringValor = "0"; stringIntermedio = "";
    }

    /**
     * Retorna el tamaño del string, es decir, cuantos bloques de operacion hay. P. ej 5+4 = 2 operaciones. 3+3+2 = 3 operaciones.
     *
     * Crea un contador que empieza en uno puesto que cada vez que encontremos un operador en el string sumaremos uno. Esto supone que en una suma
     * 10+1, haya un operador y dos bloques de operacion. Por ello empieza en 1 en vez de en 0. Este método es utilizado para saber cómo tenemos que aplicar un operador
     * en el método realizarCuenta()
     * @return
     */
    public int getStringSize(){
        /**

         */
        int contador = 1;
        try {
            TextView textoSup = findViewById(R.id.textoSuperior); //Sacamos un textview de nuestro texto superior
            String texto = (String) textoSup.getText(); //Del objeto texto, saca solamente la informacion que contiene
            
            for (int i=0; i<texto.length(); i++){
                //Creamos un switch con variable del indice actual del string
                switch (texto.charAt(i)){
                    case '+':
                    case '-':
                    case 'x':
                    case '/':
                    case '%':
                        contador++; //Si el indice es cualquiera de estos operadores se uma uno, puesto que cada uno divide un bloque
                        break;
                    default:
                        //No hace nada porque en este bloque no sumamos nada, es un numero normal.
                        break;
                }
            }
            //Toast.makeText(this, String.valueOf(contador), Toast.LENGTH_SHORT).show(); DEBUG
        } catch (Exception e){

        }
        return contador;
    }
}