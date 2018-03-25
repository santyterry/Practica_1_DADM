package es.dadm.umh.santiago.practica_1_dadm;

import java.util.ArrayList;

public class Cuestionario {
    private String nombre;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private String respuesta5;
    private ArrayList<String> respuesta6 =new ArrayList<String>();
    private String respuesta7;
    private String respuesta8;
    private String respuesta9;
    private String respuesta10;



    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public String getRespuesta5() {
        return respuesta5;
    }

    public void setRespuesta5(String respuesta5) {
        this.respuesta5 = respuesta5;
    }


    public String getRespuesta7() {
        return respuesta7;
    }

    public void setRespuesta7(String respuesta7) {
        this.respuesta7 = respuesta7;
    }

    public String getRespuesta8() {
        return respuesta8;
    }

    public void setRespuesta8(String respuesta8) {
        this.respuesta8 = respuesta8;
    }

    public String getRespuesta9() {
        return respuesta9;
    }

    public void setRespuesta9(String respuesta9) {
        this.respuesta9 = respuesta9;
    }

    public String getRespuesta10() {
        return respuesta10;
    }

    public void setRespuesta10(String respuesta10) {
        this.respuesta10 = respuesta10;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getRespuesta6() {
        return respuesta6;
    }

    public void setRespuesta6(ArrayList<String> respuesta6) {
        this.respuesta6 = respuesta6;
    }

    public int comprobarRespuestas(){
        int resultado=0;
        if(this.respuesta1.equals("Cupcake")){
            resultado++;
        }
        if (this.respuesta2.equals("2005")){
            resultado++;
        }
        if (this.respuesta3.equals("HTC Dream")){
            resultado++;
        }
        if (this.respuesta4.equals("Sony")){
            resultado++;
        }
        if (this.respuesta5.equals("Android Wear")){
            resultado++;
        }

        if (respuesta6.size()==2){
            if (respuesta6.get(0).equals("Chupachup")&&respuesta6.get(1).equals("Lime Cake")) {
                resultado++;
            }
        }

        if (this.respuesta7.equals("8")){
            resultado++;
        }

        if (this.respuesta8.equals("Ice Cream Sandwich")){
            resultado++;
        }

        if (this.respuesta9.equals("Verdadero")){
            resultado++;
        }
        if (this.respuesta10.equals("2008")){
            resultado++;
        }

        return resultado;
    }
}
