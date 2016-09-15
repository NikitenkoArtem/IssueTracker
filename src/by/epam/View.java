package by.epam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Price on 15.09.2016.
 */
public class View {
    public View() {
    }

    public StringBuffer displayTableRow(ArrayList<HashMap<String, Object>> list) {
        StringBuffer buffer = null;
        if (list != null) {
            String openTr = "<tr>";
            String closeTr = "</tr>";
            String openTd = "<td><a href='/status?statusName=";
            String closeTd = "</td>";
            Iterator<HashMap<String, Object>> iterator = list.iterator();
            buffer = new StringBuffer();
            while (iterator.hasNext()) {
                buffer.append(openTr);
                for (Object o : iterator.next().values()) {
                    final String value = o.toString();
                    buffer.append(openTd + value + "'>" + value + closeTd);
                }
                buffer.append(closeTr);
            }
        }
        return buffer;
    }

    public StringBuffer display(ArrayList<HashMap<String, Object>> list, String openTag) {
        StringBuffer buffer = null;
        if (list != null) {
            String whitespace = " ";
            // TODO: 15.09.2016 StringIndexOutOfBoundsException
            String closeTag = "</" + openTag.substring(1, openTag.lastIndexOf(whitespace)) + ">";
            Iterator<HashMap<String, Object>> iterator = list.iterator();
            buffer = new StringBuffer();
            while (iterator.hasNext()) {
                for (Object o : iterator.next().values()) {
                    final String value = o.toString();
                    buffer.append(openTag + value + closeTag);
                }
            }
        }
        return buffer;
    }

    public StringBuffer display(ArrayList<HashMap<String, Object>> list, String openTag, String id) {
        StringBuffer buffer = null;
        if (list != null) {
            String whitespace = " ";
            String closeTag = "</" + openTag.substring(1, openTag.lastIndexOf(whitespace)) + ">";
            Iterator<HashMap<String, Object>> iterator = list.iterator();
            buffer = new StringBuffer();
            while (iterator.hasNext()) {
                for (Object o : iterator.next().values()) {
                    final String value = o.toString();
                    if (value.equals(id)) {
                        buffer.append("<option name='manager' selected>" + value + closeTag);
                    } else {
                        buffer.append(openTag + value + closeTag);
                    }
                }
            }
        }
        return buffer;
    }
}
