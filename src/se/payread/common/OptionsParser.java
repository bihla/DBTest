/*
$Id: OptionsParser.java,v 1.1.2.9.2.1 2017/05/30 15:10:08 bihla Exp $ 
$Header: /usr/local/cvsroot/PayReadJavaEngine/WEB-INF/src/se/payread/common/Attic/OptionsParser.java,v 1.1.2.9.2.1 2017/05/30 15:10:08 bihla Exp $ 
$Log: OptionsParser.java,v $
Revision 1.1.2.9.2.1  2017/05/30 15:10:08  bihla
added gteKeys

Revision 1.1.2.9  2017/03/28 13:29:09  bihla
moved import

Revision 1.1.2.8  2017/03/28 11:00:17  bihla
setOptions => addOptions

Revision 1.1.2.7  2017/03/20 16:26:41  bihla
added split char

Revision 1.1.2.6  2017/02/27 16:14:26  bihla
The latest

Revision 1.1.2.5  2017/01/19 17:56:22  bihla
done

Revision 1.1.2.4  2017/01/18 16:23:37  bihla
trim key and value

Revision 1.1.2.3  2017/01/18 08:01:49  bihla
String "false" is boolean false - avoid hitting "NumberFormatException"

Revision 1.1.2.2  2016/11/21 12:07:32  bihla
OptionsParser is the primary parser for options

Revision 1.1.2.1  2016/11/18 15:59:01  bihla
Added OptionsParser - genuine and well tested ;-)

Revision 1.1.8.1  2011/04/05 11:54:59  bihla
CVS tag header added.

*/
package se.payread.common;

import java.beans.XMLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * @author krishna
 * 
 */
public class OptionsParser {
	private static final transient Logger logger = Logger.getLogger(OptionsParser.class);

	private String separatorChar=",";
	private String lastKey = null;
	private HashMap<String, String> hashMap = new HashMap<String, String>();

	public OptionsParser() {
	}

	public OptionsParser(String options) {
		addOptions(options);
	}

	public OptionsParser(String options, String separatorChar) {
		setSeparatorChar(separatorChar);
		addOptions(options);
	}

	public String getSeparatorChar() {
		return separatorChar;
	}

	public void setSeparatorChar(String separatorChar) {
		this.separatorChar = separatorChar;
	}

	public String getOptions() {
		String out = "";
		Map<String, String> treeMap = new TreeMap<String, String>(hashMap);
		for (String key : treeMap.keySet()) {
			out += (key + "=" + hashMap.get(key) + separatorChar);
		}
		return out.length() >= 1 ? out.substring(0, out.length() - 1) : "";
	}

	public boolean hasOptions() {
		return hashMap.size() >= 1;
	}

	public void addOptions(String options) {
		if (options != null && options.length() >= 3) {
			for (String node : Arrays.asList(options.split(separatorChar))) {
				String[] item = node.split("=", 2);
				if (item.length == 2) {
					String lkey = item[0].trim();
					String lval = item[1].trim();
					if (lkey.length() > 0 && lval.length() > 0)
						hashMap.put(lkey, lval);
				}
			}
		}
	}

	public void setOptions(String options) {
		hashMap.clear();
		addOptions(options);
	}

	public HashMap<String, String> getHashMap() {
		return hashMap;
	}
	
	public List<String> getKeys() {
		List<String> list = new ArrayList<String>(hashMap.keySet());
		Collections.sort(list);
		return list;
	}

	public boolean hasKey(String key) {
		if (key != null && key.trim().length() > 0)
			return hashMap.containsKey(lastKey = key.trim());
		return false;
	}

	public String getValue(String key) {
		if (hasKey(key))
			return hashMap.get(key.trim());
		return null;
	}

	public String getValueIfInArray(String key, String[] array) {
		String value = getValue(key);
		if (value != null) {
			for (String s : array) {
				if (value.equalsIgnoreCase(s))
					return s;
			}
		}
		return null;
	}

	public String getValue() {
		return getValue(lastKey);
	}

	public Integer getValueAsInteger(String key) {
		logger.debug("getValueAsInteger("+key+")...");
		try {
			String value=getValue(key);
			if (value==null)
				return null;
			if (value.trim().length()==0)
				return null;
			return new Integer(value);
		} catch (NumberFormatException e) {
			logger.debug("NumberFormatException:["+key+"]["+getValue(key)+"] (returning null)" + e.getMessage());
		}
		return null;
	}

	public Integer getValueAsInteger() {
		return getValueAsInteger(lastKey);
	}

	public boolean getValueAsBoolean(String key) {
		String lval = getValue(key);
		if (lval != null) {
			lval = lval.trim();
			if (lval.equalsIgnoreCase("true"))
				return true;
			if (lval.equalsIgnoreCase("false"))
				return false;
			if (lval.equals("0"))
				return false;
			if (lval.equals("1"))
				return true;
			Integer i = getValueAsInteger(key);
			if (i != null && i.intValue() >= 1)
				return true;
		}
		return false;
	}

	public boolean getValueAsBoolean() {
		return getValueAsBoolean(lastKey);
	}

	public String set(String key, String value) {
		if (key != null && key.length() > 0)
			if (value != null && value.trim().length() >= 0)
				hashMap.put(key, value.trim());
		return getOptions();
	}

	public String set(String key, long value) {
		if (key != null && key.length() > 0)
			hashMap.put(key, new Long(value).toString());
		return getOptions();
	}

	public String remove(String key) {
		if (key != null && key.length() > 0)
			hashMap.remove(key);
		return getOptions();
	}

	/**/
	public static String set(String options, String key, String value) {
		return new OptionsParser(options).set(key, value);
	}

	public static String remove(String options, String key) {
		return new OptionsParser(options).remove(key);
	}

	public static String getKey(String options, String key) {
		return new OptionsParser(options).getValue(key);
	}

	public static boolean hasKey(String options, String key) {
		return new OptionsParser(options).hasKey(key);
	}

	public static String getValue(String options, String key) {
		return new OptionsParser(options).getValue(key);
	}

	public static boolean getValueAsBoolean(String options, String key) {
		return new OptionsParser(options).getValueAsBoolean(key);
	}

	public static HashMap<String, String> getHashMap(String options) {
		return new OptionsParser(options).getHashMap();
	}

	public static String generateOptionsString(String options) {
		return new OptionsParser(options).getOptions();
	}
	
	public String toString() {
		return "[OptionsParser]"
				+":"+lastKey
				+"="+getValue(lastKey)
				+" ("+getValueAsBoolean(lastKey)+" as boolean)"
				+" ("+getValueAsInteger(lastKey)+" as integer)";
	}

}
