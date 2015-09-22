package br.interactive.ecm.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe criada com o objetivo de evitar números mágicos.
 * 
 * @author roberto.sousa
 */
public final class NumeroUtil {

    /***/
    public static final int ZERO = 0;

    /***/
    public static final double ZERO_PONTO_CINCO = 0.5;

    /***/
    public static final float ZERO_PONTO_CINQUENTA_CINCO = 0.55f;

    /***/
    public static final float ZERO_PONTO_DOIS = 0.2f;

    /***/
    public static final float ZERO_PONTO_SETE_FLOAT = 0.7f;

    /***/
    public static final double ZERO_PONTO_SETE = 0.7;

    /***/
    public static final float ZERO_PONTO_OITO = 0.8f;

    /***/
    public static final float ZERO_PONTO_OITENTA_TRES = 0.83f;

    /***/
    public static final int UM = 1;

    /***/
    public static final float UM_PONTO_DOIS = 1.2f;

    /***/
    public static final float UM_PONTO_CINCO = 1.5f;

    /***/
    public static final float UM_PONTO_QUARENTA_CINCO = 1.45f;

    /***/
    public static final float UM_PONTO_NOVENTA_CINCO = 1.95f;

    /***/
    public static final int DOIS = 2;

    /***/
    public static final float DOIS_PONTO_ZERO = 2.0f;

    /***/
    public static final float DOIS_PONTO_UM = 2.1f;

    /***/
    public static final float DOIS_PONTO_OITO = 2.8f;

    /***/
    public static final float TRES_PONTO_ZERO = 3.0f;

    /***/
    public static final int TRES = 3;

    /***/
    public static final float TRES_PONTO_UM = 3.1f;

    /***/
    public static final int QUATRO = 4;

    /***/
    public static final int CINCO = 5;

    /***/
    public static final float CINCO_PONTO_ZERO = 5.0f;

    /***/
    public static final float SEIS_PONTO_ZERO = 6.0f;

    /***/
    public static final int SEIS = 6;

    /***/
    public static final int SETE = 7;

    /***/
    public static final int OITO = 8;

    /***/
    public static final int NOVE = 9;

    /***/
    public static final int DEZ = 10;

    /***/
    public static final int ONZE = 11;

    /***/
    public static final int DOZE = 12;

    /***/
    public static final int TREZE = 13;

    /***/
    public static final int CATORZE = 14;

    /***/
    public static final int QUINZE = 15;

    /***/
    public static final int DEZESSEIS = 16;

    /***/
    public static final int DEZESSETE = 17;

    /***/
    public static final float DEZOITO_PONTO_CINCO = 18.5f;

    /***/
    public static final int DEZOITO = 18;

    /***/
    public static final int DEZENOVE = 19;

    /***/
    public static final int VINTE = 20;

    /***/
    public static final int VINTE_E_UM = 21;

    /***/
    public static final int VINTE_E_DOIS = 22;

    /***/
    public static final int VINTE_E_TRES = 23;

    /***/
    public static final int VINTE_E_QUATRO = 24;

    /***/
    public static final int VINTE_E_CINCO = 25;

    /***/
    public static final int VINTE_E_SEIS = 26;

    /***/
    public static final int VINTE_E_SETE = 27;

    /***/
    public static final int VINTE_E_OITO = 28;

    /***/
    public static final int VINTE_E_NOVE = 29;

    /***/
    public static final float VINTE_E_NOVE_PONTO_NOVE = 29.9f;

    /***/
    public static final int TRINTA = 30;

    /***/
    public static final int TRINTA_E_UM = 31;

    /***/
    public static final int TRINTA_E_DOIS = 32;

    /***/
    public static final int TRINTA_E_TRES = 33;

    /***/
    public static final int TRINTA_E_QUATRO = 34;

    /***/
    public static final float TRINTA_E_QUATRO_PONTO_NOVE = 34.9f;

    /***/
    public static final int TRINTA_E_CINCO = 35;

    /***/
    public static final float TRINTA_CINCO_PONTO_SEIS = 35.6f;

    /***/
    public static final int TRINTA_E_SEIS = 36;

    /***/
    public static final int TRINTA_E_SETE = 37;

    /***/
    public static final int TRINTA_E_OITO = 38;

    /***/
    public static final int TRINTA_E_NOVE = 39;

    /***/
    public static final float TRINTA_E_NOVE_PONTO_NOVE = 39.9f;

    /***/
    public static final int QUARENTA = 40;

    /***/
    public static final int QUARENTA_E_UM = 41;

    /***/
    public static final int QUARENTA_E_DOIS = 42;

    /***/
    public static final float QUARENTA_DOIS_PONTO_SEIS = 42.6f;

    /***/
    public static final int QUARENTA_E_TRES = 43;

    /***/
    public static final int QUARENTA_E_QUATRO = 44;

    /***/
    public static final int QUARENTA_E_CINCO = 45;

    /***/
    public static final int QUARENTA_E_SEIS = 46;

    /***/
    public static final int QUARENTA_E_SETE = 47;

    /***/
    public static final int QUARENTA_E_OITO = 48;

    /***/
    public static final int QUARENTA_E_NOVE = 49;

    /***/
    public static final int CINQUENTA = 50;

    /***/
    public static final int CINQUENTA_E_SEIS = 56;

    /***/
    public static final float CINQUENTA_SEIS_PONTO_OITO = 56.8f;

    /***/
    public static final int CINQUENTA_E_SETE = 57;

    /***/
    public static final int CINQUENTA_E_OITO = 58;

    /***/
    public static final int CINQUENTA_E_NOVE = 59;

    /***/
    public static final int SESSENTA = 60;

    /***/
    public static final int SETENTA = 70;

    /***/
    public static final int SETENTA_E_DOIS = 70;

    /***/
    public static final int SETENTA_E_CINCO = 70;

    /***/
    public static final int OITENTA = 80;

    /***/
    public static final int CEM = 100;

    /***/
    public static final int CENTO_E_CINQUENTA = 150;

    /***/
    public static final int CENTO_E_VINTE_OITO = 128;

    /***/
    public static final int CENTO_E_OITENTA = 180;

    /***/
    public static final int CENTO_E_NOVENTA_E_NOVE = 199;

    /***/
    public static final int DUZENTOS = 200;

    /***/
    public static final int DUZENTOS_E_VINTE = 220;

    /***/
    public static final int DUZENTOS_E_VINTE_E_CINCO = 225;

    /***/
    public static final int DUZENTOS_E_VINTE_E_OITO = 228;

    /***/
    public static final int DUZENTOS_E_TRINTA = 230;

    /***/
    public static final int DUZENTOS_E_CINQUENTA = 250;

    /** The Constant DUZENTOS_E_CINQUENTA_SEIS. */
    public static final int DUZENTOS_E_CINQUENTA_SEIS = 256;
    
    /***/
    public static final int DUZENTOS_E_OITENTA = 280;

    /***/
    public static final int DUZENTOS_E_CINQUENTA_E_CINCO = 255;

    /***/
    public static final int TREZENTOS = 300;

    /***/
    public static final int TREZENTOS_E_CINQUENTA = 350;

    /***/
    public static final int TREZENTOS_E_SESSENTA = 360;

    /***/
    public static final int QUATROCENTOS = 400;

    /***/
    public static final int QUATROCENTOS_CINQUENTA = 450;

    /***/
    public static final int QUATROCENTOS_E_OITENTA = 480;

    /***/
    public static final int QUATROCENTOS_E_NOVENTA_E_NOVE = 499;

    /***/
    public static final int QUINHENTOS = 500;

    /***/
    public static final int QUINHENTOS_E_SESSENTA = 560;

    /***/
    public static final int SEISCENTOS = 600;

    /***/
    public static final int SEISCENTOS_E_QUARENTA = 640;

    /***/
    public static final int SEISCENTOS_E_CINQUENTA = 650;

    /***/
    public static final int SETECENTOS = 700;

    /***/
    public static final int SETECENTOS_E_SESSENTA = 750;

    /***/
    public static final int OITOCENTOS = 800;

    /***/
    public static final int OITOCENTOS_E_OITENTA = 880;

    /***/
    public static final int NOVECENTOS = 900;

    /***/
    public static final int UM_MIL = 1000;

    /***/
    public static final int UM_MIL_E_DOIS = 1002;

    /***/
    public static final int UM_MIL_E_DUZENTOS = 1200;

    /***/
    public static final int UM_MIL_E_TREZENTOS = 1300;

    /***/
    public static final int UM_MIL_E_QUINHENTOS = 1500;

    /***/
    public static final int UM_MIL_E_SEISCENTOS = 1600;

    /***/
    public static final int UM_MIL_E_QUATROCENTOS = 1400;

    /***/
    public static final int UM_MIL_E_NOVECENTOS = 1900;

    /***/
    public static final int DOIS_MIL = 2000;

    /***/
    public static final int DOIS_MIL_QUINHENTOS = 2500;

    /***/
    public static final int DOIS_MIL_E_DEZ = 2010;

    /***/
    public static final int DOIS_MIL_E_ONZE = 2011;

    /***/
    public static final int DOIS_MIL_E_DOZE = 2012;

    /***/
    public static final int TRES_MIL = 3000;

    /***/
    public static final int TRES_MIL_E_QUINHENTOS = 3500;

    /***/
    public static final int QUATRO_MIL = 4000;

    /***/
    public static final int QUATRO_MIL_SETECENTOS = 4700;

    /***/
    public static final int QUATRO_MIL_E_CINQUENTA = 4050;

    /***/
    public static final int CINCO_MIL = 5000;

    /***/
    public static final int CINCO_MIL_E_QUATROCENTOS = 5400;

    /***/
    public static final int SEIS_MIL = 6000;

    /***/
    public static final int SEIS_MIL_E_QUATROCENTOS = 6400;

    /***/
    public static final int SEIS_MIL_E_QUINHENTOS = 6500;

    /***/
    public static final int SETE_MIL = 7000;

    /***/
    public static final int OITO_MIL = 8000;

    /***/
    public static final int OITO_MIL_E_QUATROCENTOS = 8400;

    /***/
    public static final int OITO_MIL_E_QUINHENTOS = 8500;

    /***/
    public static final int NOVE_MIL = 9000;

    /***/
    public static final int DEZ_MIL = 10000;

    /***/
    public static final int DOZE_MIL = 12000;

    /***/
    public static final int DOZE_MIL_TREZENTOS = 12300;

    /***/
    public static final int DUZENTOS_MIL = 200000;

    /***/
    public static final int UM_MILHAO = 1000000;

    /***/
    public static final int OITENTA_E_SEIS_MILHOES_QUATROCENTOS_MIL = 86400000;

    /***/
    public static final int NOVENTA_E_NOVE_MILHOES_NOVECENTOS_E_NOVENTA_MIL_CENTO_E_UM = 99990101;

    /***/
    public static final int MENOS_SEIS = -6;

    /***/
    public static final int MENOS_DOZE = -12;

    /***/
    public static final int MENOS_UM = -1;

    /***/
    public static final Integer MIL_NOVECENTOS_OITENTA_CINCO = 1985;

    private NumeroUtil() {
        super();
    }

    /*
     * Re-implementar sem o uso do seam public static String formataNumber( String valor ) {
     * 
     * if ( LocaleUtil.getLocale().getLanguage().equals( LocaleUtil.PT ) ) { valor = valor.replace( ".", "," ); }
     * 
     * return valor; }
     */

    public static Integer converteObjetoParaInteger(Object valor) {
        if (valor instanceof BigDecimal) {
            return ((BigDecimal) valor).intValue();
        } else if (valor instanceof Integer) {
            return ((Integer) valor);
        }
        return null;
    }

    /**
     * Verifica se a string passada e um número
     * 
     * @param texto
     * @return boolean
     */
    public static boolean isNumeroRegexp(String texto) {
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(texto);
        return mat.matches();
    }

    /**
     * Verifica se o Inteiro passado é diferente de nulo e maior que zero.
     * 
     * @param Integer
     * @return boolean
     */
    public static boolean notEmpty(Integer pInteger) {
        return pInteger != null && pInteger > 0;
    }

    /**
     * Verifica se o Long passado é diferente de nulo e maior que zero.
     * 
     * @param Long
     * @return boolean
     */
    public static boolean notEmpty(Long pLong) {
        return pLong != null && pLong > 0;
    }

    /**
     * Verifica se o Float passado é diferente de nulo e maior que zero.
     * 
     * @param Float
     * @return boolean
     */
    public static boolean notEmpty(Float pFloat) {
        return pFloat != null && pFloat > 0;
    }

    /**
     * Verifica se o BigDecimal passado é diferente de nulo e maior que zero.
     * 
     * @param BigDecimal
     * @return boolean
     */
    public static boolean notEmpty(BigDecimal pBigDecimal) {
        return pBigDecimal != null && pBigDecimal.intValue() > 0;
    }
}
