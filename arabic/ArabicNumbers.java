package arabic;

import java.awt.font.NumericShaper;

public class ArabicNumbers {
	/**
	 * represent Arabic number Sepher
	 */
	public final static char ARABIC_ZIRO = 1632;
	/**
	 * represent Arabic number Wahed
	 */
	public final static char ARABIC_ONE = 1633;
	/**
	 * represent Arabic number Ethnin
	 */
	public final static char ARABIC_TWO = 1634;
	/**
	 * represent Arabic number Thalathah
	 */
	public final static char ARABIC_THREE = 1635;
	/**
	 * represent Arabic number Arbaah
	 */
	public final static char ARABIC_FOUR = 1636;
	/**
	 * represent Arabic number khamsah
	 */
	public final static char ARABIC_FIVE = 1637;
	/**
	 * represent Arabic number Setah
	 */
	public final static char ARABIC_SIX = 1638;
	/**
	 * represent Arabic number Sabaah
	 */
	public final static char ARABIC_SEVEN = 1639;
	/**
	 * represent Arabic number Thaminah
	 */
	public final static char ARABIC_EIGHT = 1640;
	/**
	 * represent Arabic number Tesaah
	 */
	public final static char ARABIC_NINE = 1641;
	/**
	 * convert English numbers string to Arabic number string
	 * note: Arabic floating point is , not . 
	 * @param englishText English string to convert
	 * @return Arabic value
	 */
	public static String convertEnglishToArabic(String englishText) {
		if (englishText.isEmpty())
			return englishText;// check on text

		NumericShaper shaper = NumericShaper.getShaper(NumericShaper.ARABIC);// for Arabic numbers
		char[] text = englishText.toCharArray();// convert text to char array
		shaper.shape(text, 0, text.length);

		return String.valueOf(text).replace('.', ',');// convert char array to text

	}
	/**
	 * convert double value to Arabic number string
	 * note: Arabic floating point is , not . 
	 * @param number string to convert
	 * @return Arabic value
	 */
	public static String convertDoubleToArabic(double number) {
		return convertEnglishToArabic(String.valueOf(number));

	}
	/**
	 * convert Arabic number string to English number string
	 * note: Arabic floating point is , not . 
	 * @param arabicText Arabic string to convert
	 * @return English value
	 * @throws InvalidArabicNumber when string contains invalid Arabic characters
	 */
	public static String convertArabicToEnglish(String arabicText) {

		if (arabicText.isEmpty()) {
			return arabicText;
		}

		char[] values = new char[arabicText.length()];

		char ch;

		for (int i = 0; i < values.length; i++) {
			ch = arabicText.charAt(i);

			values[i] = getEnglishDigitPr(ch, i);
		}

		return String.valueOf(values);
	}
	/**
	 * convert Arabic number string to double value
	 * note: Arabic floating point is , not . 
	 * @param arabicText Arabic string to convert
	 * @return double value
	 * @throws InvalidArabicNumber when string contains invalid Arabic characters
	 */
	public static double convertArabicToDouble(String arabicText) {

		if (arabicText.length()==0) {
			throw new InvalidArabicNumber("empty string");
		}

		char[] values = new char[arabicText.length()];

		char ch;

		for (int i = 0; i < values.length; i++) {
			ch = arabicText.trim().charAt(i);

			values[i] = getEnglishDigitPr(ch, i);
		}

		return Double.parseDouble(String.valueOf(values));
	}
	
	private static char getEnglishDigitPr(char ch,int i) {
		char value;
		if (ch == ARABIC_ZIRO) {
			value = '0';
		} else if (ch == ARABIC_ONE) {
			value = '1';
		} else if (ch == ARABIC_TWO) {
			value = '2';
		} else if (ch == ARABIC_THREE) {
			value = '3';
		} else if (ch == ARABIC_FOUR) {
			value = '4';
		} else if (ch == ARABIC_FIVE) {
			value = '5';
		} else if (ch == ARABIC_SIX) {
			value = '6';
		} else if (ch == ARABIC_SEVEN) {
			value = '7';
		} else if (ch == ARABIC_EIGHT) {
			value = '8';
		} else if (ch == ARABIC_NINE) {
			value = '9';
		} else if(ch == ','){
			value = '.';
		} else{
			throw new InvalidArabicNumber("invalid character "+ch+" in "+i);
		}
		return value;
	}
	/**
	 * convert Arabic digit character to English digit character
	 * @param arabicText Arabic string to convert
	 * @return English value
	 * @throws InvalidArabicNumber when char is invalid Arabic characters
	 */
	public static char getEnglishDigit(char ch) {
		char value;
		if (ch == ARABIC_ZIRO) {
			value = '0';
		} else if (ch == ARABIC_ONE) {
			value = '1';
		} else if (ch == ARABIC_TWO) {
			value = '2';
		} else if (ch == ARABIC_THREE) {
			value = '3';
		} else if (ch == ARABIC_FOUR) {
			value = '4';
		} else if (ch == ARABIC_FIVE) {
			value = '5';
		} else if (ch == ARABIC_SIX) {
			value = '6';
		} else if (ch == ARABIC_SEVEN) {
			value = '7';
		} else if (ch == ARABIC_EIGHT) {
			value = '8';
		} else if (ch == ARABIC_NINE) {
			value = '9';
		} else{
			throw new InvalidArabicNumber("invalid character");
		}
		return value;
	}
	
	/**
	 * verify character represent valid Arabic digit 
	 * @param arabicCh character to verify
	 * @return if arabicCh is Arabic digit true else false 
	 */
	public static boolean isArabicDigit(char arabicCh) {
		return arabicCh>=ARABIC_ZIRO&&arabicCh<=ARABIC_NINE;
	}
	/**
	 * verify string represent valid Arabic double number
	 * valid form examples:
	 * "1" "1.2" ".2"
	 * invalid form examples:
	 * "" "." "1."
	 * do not forget we are takeing about Arabic numbers
	 * @param arabicNumber String to verify
	 * @return if arabicNumber represent Arabic double number true else false 
	 */
	public static boolean isDoubleArabicNumber(String arabicNumber) {
		
		try{
		    arabicNumber=convertArabicToEnglish(arabicNumber);
		    Double.parseDouble(arabicNumber);
			return true;
		}catch(Exception e){
		    return false;
		}
	}
	/**
	 * verify string represent valid Arabic integer number
	 * @param arabicNumber String to verify
	 * @return if arabicNumber represent Arabic integer number true else false 
	 */
	public static boolean isIntegerArabicNumber(String arabicNumber) {
		try{
		    arabicNumber=convertArabicToEnglish(arabicNumber);
		    Integer.parseInt(arabicNumber);
			return true;
		}catch(Exception e){
		    return false;
		}
	}

	public static void main(String[] args) {
		String string=convertEnglishToArabic("20.0");
		System.out.println(string);
		System.out.println(convertArabicToEnglish(string));
		System.out.println(isDoubleArabicNumber(string));
		System.out.println(isIntegerArabicNumber(string.substring(0,2)));
		System.out.println(isArabicDigit(string.charAt(0)));
	}

}
