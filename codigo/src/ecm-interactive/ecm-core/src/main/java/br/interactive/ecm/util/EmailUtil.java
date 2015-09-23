package br.interactive.ecm.util;

import java.util.logging.Logger;

/**
 * Classe utilit�ria para envio de email.
 *
 * @author robson.ramos
 */
public class EmailUtil {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(EmailUtil.class.getName());

    /**
     * Leitor de properties.
     */
    private static PropertiesUtil propertiesUtil;

    /**
     * Instance.
     */
    private static EmailUtil instance;

    /**
     * Enumerado com as propriedades da configura��o do email
     */
    public static enum EmailProperty {

        HOST("host"),
        PORT("port"),
        DEBUG("debug"),
        USERNAME("username"),
        PASSWORD("password"),
        SSL("ssl"),
        TLS("tls");

        private String property;

        private EmailProperty(String property) {
            this.property = property;
        }

        @Override
        public String toString() {
            return property;
        }

    }

    public static synchronized EmailUtil getInstance() {
        if (instance == null) {
            instance = new EmailUtil();
        }
        return instance;
    }

    /**
     * To prevent instance.
     */
    private EmailUtil() {
    }

    /**
     * Faz o envio de e-mail com os par�metros informados.
     *
     * @param emailTo
     * @param subject
     * @param txMensagem
     * @return
     */
    public synchronized boolean sendMail(String emailTo, String subject, String txMensagem) {

        String userName = propertiesUtil.getProperty(EmailProperty.USERNAME.toString()).trim();
        String smtpUser = userName;
        String user = userName;
        String smtpServer = propertiesUtil.getProperty(EmailProperty.HOST.toString()).trim();
        String smtpPort = propertiesUtil.getProperty(EmailProperty.PORT.toString()).trim();
        String password = propertiesUtil.getProperty(EmailProperty.PASSWORD.toString()).trim();
        boolean ssl = Boolean.parseBoolean(propertiesUtil.getProperty(EmailProperty.SSL.toString()).trim());
        boolean tls = Boolean.parseBoolean(propertiesUtil.getProperty(EmailProperty.TLS.toString()).trim());
        boolean debug = Boolean.parseBoolean(propertiesUtil.getProperty(EmailProperty.DEBUG.toString()).trim());

        SendMail send = new SendMail(emailTo, user, smtpServer, smtpPort,
                smtpUser, user, password, subject, txMensagem, debug, ssl, tls);

        boolean ok = send.execute();
        String msg = "E-mail enviado com sucesso.";
        if (!ok) {
            msg = "Erro ao enviar e-mail.";
        }
        LOGGER.info(msg);
        return ok;
    }

}
