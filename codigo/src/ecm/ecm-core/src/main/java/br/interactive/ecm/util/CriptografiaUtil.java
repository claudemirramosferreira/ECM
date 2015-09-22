package br.interactive.ecm.util;

import java.security.MessageDigest;

import br.interactive.ecm.exception.BusinessException;
import br.interactive.ecm.message.ErrorMessage;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Classe utilitária para trabalhar com criptografia.
 *
 * @author robson.ramos
 */
@SuppressWarnings("restriction")
public class CriptografiaUtil {

    /**
     * Algoritmo MD5.
     */
    public static final String MD5 = "MD5";

    /**
     * Criptgrafa o conteúdo utilizando o algoritimo MD5.
     *
     * @param str
     * @return
     */
    public static String criptografar(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance(MD5);
            BigInteger hash = new BigInteger(1, md.digest(str.getBytes()));  
            str = hash.toString(16);
            
        } catch (Exception e) {
            String msg = "Falha ao criptografar: " + str + " \t utilizando: " + MD5;
            throw new BusinessException(new ErrorMessage(msg));
        }

        return str;

    }

}
