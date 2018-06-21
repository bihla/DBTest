package se.payread.common;

import java.math.BigDecimal;

public class CurrencyUtil {
	public static double round(double d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}
