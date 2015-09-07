package br.interactive.ecm.util;

import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

/**
 * Classe utilitária para operações com String.
 * 
 * @author robson.ramos
 */
public final class StringUtil {

    /**
     * Mascara qualquer string passada
     * 
     * @param pattern
     * @param value
     * @return String
     */
    public static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            if (value == null) {
                return null;
            } else {
                return mask.valueToString(value);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the list longfrom string.
     *
     * @param lista the lista
     * @return the list longfrom string
     */
    public static List<Long> getListLongfromString(String lista) {
        String[] arrayString = lista.split(",");
        List<Long> listLong = new ArrayList<Long>();
        for (int i = 0; i < arrayString.length; i++) {
            if(!arrayString[i].isEmpty() ){
                listLong.add(Long.parseLong(arrayString[i]));   
            }
        }
        return listLong;
    }

    /**
     * Valida se o objeto informado não é nulo.
     * 
     * @param obj
     * @return
     */
    public static boolean notEmpty(Object obj) {
        return obj != null;
    }

    /**
     * Valida se a string informada não está vazia.
     * 
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * Remove os acentos de uma string
     * 
     * @param str
     * @return String
     */
    public static String removerAcento(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    /** Previnir instanciação. */
    private StringUtil() {
    }

}
