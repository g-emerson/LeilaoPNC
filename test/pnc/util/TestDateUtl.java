/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vinicius
 */
public class TestDateUtl {

    public TestDateUtl() {
    }

    @Test
    public void test_GetCalendarFromDateAndTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

            Date data = dateFormat.parse("12/03/2001");
            Date hora = timeFormat.parse("11:23:42");

            Calendar cal = DateUtil.getCalendarFromDateAndTime(data, hora);

            assertEquals(12, cal.get(Calendar.DAY_OF_MONTH));
            assertEquals(Calendar.MARCH, cal.get(Calendar.MONTH));
            assertEquals(2001, cal.get(Calendar.YEAR));
            assertEquals(11, cal.get(Calendar.HOUR));
            assertEquals(23, cal.get(Calendar.MINUTE));
            assertEquals(42, cal.get(Calendar.SECOND));
        } catch (ParseException ex) {
            Logger.getLogger(TestDateUtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
