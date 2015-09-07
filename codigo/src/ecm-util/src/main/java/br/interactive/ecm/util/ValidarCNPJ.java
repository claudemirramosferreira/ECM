package br.interactive.ecm.util;

/**
 *
 * @author anderson.monteiro
 */
public class ValidarCNPJ {
    
    /** The Constant pesoCNPJ. */
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    /**
     * Validar CNPJ da empresa terceira.
     *
     * @param cnpj
     *            the cnpj
     * @return true, if successful
     */
    public Boolean cnpjIsValid(String cnpj) {

    	if(cnpj == null || cnpj.isEmpty() ){
    		return false;
    	}else if (cnpj.length() != 14) {
        	return false;
        }else{
	        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
	        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1,
	                pesoCNPJ);
	        String cnpjCalc = cnpj.substring(0, 12) + digito1.toString() + digito2.toString();
	        if (!cnpj.equals(cnpjCalc)) {
	        	return false;
	        }
        }
        
        return true;
    }
    
    /**
     * Calcular digito verificador.
     *
     * @param str
     *            the str.
     * @param peso
     *            the peso.
     * @return the int.
     */
    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
}
