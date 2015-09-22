package br.interactive.ecm.util;

public class PropertiesEHS {

    /** Property com as configurações do relatório (src/main/resources/report.properties). */
    private static final String EHS_CONFIG_FILE = "import.properties";

    /** Instance. */
    private static PropertiesEHS instance;
    
    /** Leitor de properties. */
    private static PropertiesUtil propertiesUtil  = new PropertiesUtil(EHS_CONFIG_FILE);

    /**
     * Enumerado com as propriedades da configuração do relatório
     */
    public static enum ImportProperty {
        COD_UND_ORG_RAIZ("COD_UND_ORG_RAIZ", propertiesUtil.getProperty("COD_UND_ORG_RAIZ")),
        ENDERECO("ENDERECO", propertiesUtil.getProperty("ENDERECO")),
        BAIRRO("BAIRRO", propertiesUtil.getProperty("BAIRRO")),
        CIDADE("CIDADE", propertiesUtil.getProperty("CIDADE")),
        UF("UF", propertiesUtil.getProperty("UF")),
        TELEFONE("TELEFONE", propertiesUtil.getProperty("TELEFONE")),
        MEDICO_COORD_PCMSO_NOME("MEDICO_COORD_PCMSO_NOME", propertiesUtil.getProperty("MEDICO_COORD_PCMSO_NOME")), 
        EMPRESA("EMPRESA", propertiesUtil.getProperty("EMPRESA")), 
        MEDICO_COORD_PCMSO_CRM("MEDICO_COORD_PCMSO_CRM", propertiesUtil.getProperty("MEDICO_COORD_PCMSO_CRM")), 
        MEDICO_COORD_PCMSO_TELEFONE("MEDICO_COORD_PCMSO_TELEFONE", propertiesUtil.getProperty("MEDICO_COORD_PCMSO_TELEFONE"));

        private String property;
        private String valor;
        
        private ImportProperty(String property, String valor) {
            this.property = property;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return property;
        }
        
        public String getValor() {
            return valor;
        }

    }

    public static synchronized PropertiesEHS getInstance() {
        if (instance == null)
            instance = new PropertiesEHS();
        return instance;
    }
}
