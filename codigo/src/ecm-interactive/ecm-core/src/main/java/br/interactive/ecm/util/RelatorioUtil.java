package br.interactive.ecm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe utilitária obter propriedades do relatório.
 * @author josimar.alencar
 *  */
public class RelatorioUtil {

	/** Property com as configurações do relatório (src/main/resources/report.properties). */
	private static final String EHS_CONFIG_FILE = "report.properties";
	
	/** Leitor de properties. */
    private static PropertiesUtil propertiesUtil;
    
    /** Instance. */
    private static RelatorioUtil instance;
    
    /**
     * Enumerado com as propriedades da configuração do relatório
     */
    public static enum RelatorioProperty {
    	LOGO_PATH("logo_path"),
    	LOGO_EXTENSION("logo_extension"),
    	LOGO_HEIGHT("logo_height"),
    	LOGO_WIDTH("logo_width");
    	
    	private String property;

		private RelatorioProperty(String property) {
			this.property = property;
		}

		@Override
		public String toString() {
			return property;
		}
		
    }
    
    
    public static synchronized RelatorioUtil getInstance() {
    	if (instance == null)
    		instance = new RelatorioUtil();
    	return instance;
    }
    

	/** To prevent instance. */
    public RelatorioUtil() {
    	propertiesUtil = new PropertiesUtil(EHS_CONFIG_FILE);
    }

    public byte[] imageToByte() throws IOException {
        InputStream is = null;
        byte[] buffer = null;
        String caminho = propertiesUtil.getProperty(RelatorioProperty.LOGO_PATH.toString()).trim();
        is = new FileInputStream(caminho);
        buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        return buffer;
    }

}
