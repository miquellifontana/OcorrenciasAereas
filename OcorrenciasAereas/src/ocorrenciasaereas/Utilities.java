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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilhermen
 */
public class Utilities {
    
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
    
}