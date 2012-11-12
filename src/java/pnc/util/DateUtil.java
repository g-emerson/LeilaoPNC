/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe para operações utilitárias com Datas e horários
 * @author giovani
 */
public class DateUtil {
    static public Calendar getCalendarFromDateAndTime(Date date, Date time) {
        Calendar calDate = Calendar.getInstance();
        Calendar calTime = Calendar.getInstance();

        calDate.setTime(date);
        calTime.setTime(time);

        calTime.set(Calendar.YEAR, calDate.get(Calendar.YEAR));
        calTime.set(Calendar.MONTH, calDate.get(Calendar.MONTH));
        calTime.set(Calendar.DAY_OF_MONTH, calDate.get(Calendar.DAY_OF_MONTH));

        return calTime;
    }
}
