
package Genericos;


public class mierror extends Throwable{
    public mierror(String MsmError, Throwable e){
        super(MsmError, e);
    }
    
    public mierror(String MsmError, Throwable e,Integer n){
        super(MsmError,e);
        switch (n){
            case 1:
                //programar la accion a seguir
                break;
            case 2:
                //programar la accion a seguir
                break;
            case 3:
                //programar la accion a seguir
                break;
        }
    }
    
}
