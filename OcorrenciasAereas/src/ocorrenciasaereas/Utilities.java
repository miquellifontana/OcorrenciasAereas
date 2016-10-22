/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocorrenciasaereas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Utilitária com métodos Estáticos para serem utilizados no projeto.
 */
public class Utilities {
    
    /**
     * Retorna um {@link Date} convertido a partir da data contida na String informada.
     *
     * @param dateString
     *   String contendo uma data no formato "yyyy-MM-dd".
     *
     * @return 
     *   Uma nova {@link Date} -
     *     se a String pode ser convertida para um Date.
     *   null -
     *     se a String não pode ser convertida para um Date
     */
    public static Date convertStringToDate(String dateString) {

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormatter.parse(dateString);
        } catch (ParseException ex) {
            date = new Date();
        }
        return date;
    }

    /**
     * Retorna um {@link Integer} convertido a partir da String informada.
     * 
     * O método retorna o valor inteiro contido na String, retornando zero caso 
     * o conteúdo da String seja "NULL"
     *
     * @param string
     *   String com o valor a ser convertido para Integer.
     *
     * @return 
     *   Um novo {@link Integer} com o valor da String.
     */
    public static Integer convertStringToInteger(String string){
        return string.equals("NULL") ? 0 : Integer.parseInt(string);
    }
}
