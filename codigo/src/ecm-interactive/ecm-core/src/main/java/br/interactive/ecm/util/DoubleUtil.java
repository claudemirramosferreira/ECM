package br.interactive.ecm.util;

import java.text.NumberFormat;
import java.util.Locale;





/**
 * Classe utilitária para realizar operações sobre valores doubles.
 * 
 * @author robson.ramos
 */
public class DoubleUtil {

    /** Formatador para valores booleanos. no formato 1,01 */
    private static NumberFormat numberFormat;

    private DoubleUtil() {
    }

    /**
     * Formata o valor informado.
     * 
     * @param obj
     * @return String
     */
    public static String format( Object obj ) {
        DoubleUtil.numberFormat = NumberFormat.getInstance( Locale.getDefault() );
        DoubleUtil.numberFormat.setMinimumFractionDigits( IntegerConstant.ZERO );
        DoubleUtil.numberFormat.setMaximumFractionDigits( IntegerConstant.DOIS );
        DoubleUtil.numberFormat.setMinimumIntegerDigits( IntegerConstant.UM );
        return DoubleUtil.numberFormat.format( obj );
    }

    /**
     * @author thiago.matos
     * @param value
     * @return
     */
    public static double truncarParaDuasCasas( double value ) {
        DoubleUtil.numberFormat = NumberFormat.getInstance( Locale.US );
        DoubleUtil.numberFormat.setMinimumFractionDigits( IntegerConstant.ZERO );
        DoubleUtil.numberFormat.setMaximumFractionDigits( IntegerConstant.DOIS );
        DoubleUtil.numberFormat.setMinimumIntegerDigits( IntegerConstant.UM );
        return Double.valueOf( DoubleUtil.numberFormat.format( value ) );
    }

}
