package br.interactive.ecm.util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Classse utilit�ria para envio de email.
 * @author robson.ramos
 */
public final class SendMail {

	private static final Logger LOGGER = Logger.getLogger(EmailUtil.class.getName());
	
	
    /** Property to send email. */
    private String to;

    /** Property to send email. */
    private String from;

    /** Property to send email. */
    private String smtpServer;

    /** Property to send email. */
    private String smtpPort;

    /** Property to send email. */
    private String smtpUser;

    /** Property to send email. */
    private String user;

    /** Property to send email. */
    private String password;

    /** Property to send email. */
    private String subject;

    /** Property to send email. */
    private boolean debug;
    
    /** Property to send email. */
    private String message;

    /** Indica se deve ou n�o usar SSL. */
	private boolean ssl;

	private boolean tls;

    public SendMail( String to, String from, String smtpServer, String smtpPort, String smtpUser, String user,
                     String password, String subject, String message, boolean debug, boolean useSsl, boolean tls ) {
        this.to = to;
        this.from = from;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.smtpUser = smtpUser;
        this.user = user;
        this.password = password;
        this.subject = subject;
        this.message = message;
		this.debug = debug;
		this.ssl = useSsl;
		this.tls = tls;
    }
    
    /**
     * Envia o email.
     * @return
     */
    public boolean execute() {

        logProperties();

        boolean sucess = false;

        try {
        	
        	Authenticator auth = null;
            if ( StringUtil.notEmpty(user) && StringUtil.notEmpty(password) ) {
            	 auth = new SMTPAuthenticator( this.user, this.password );
            }
        	
        	// Configure the SSLContext with a TrustManager
            SSLContext ctx = SSLContext.getInstance( "TLS" );
            ctx.init( new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom() );
            SSLContext.setDefault( ctx );

            Properties props = createProperties();
            Session session = Session.getInstance(props, auth);
            session.setDebug(debug);
            
            Message msg = new MimeMessage( session );

            msg.setFrom( new InternetAddress( this.from ) );
            InternetAddress[] address = { new InternetAddress( this.to ) };
            msg.setRecipients( Message.RecipientType.TO, address );
            msg.setSubject( this.subject );
            msg.setSentDate( new Date() );

            msg.setText( this.message );
            msg.setContent( this.message, "text/plain" );

            Transport.send( msg );

            sucess = true;

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return sucess;
    }

    
	private void logProperties() {
		if ( debug ) {
			LOGGER.info( "\t.::: Dados :::" );
			LOGGER.info( "\t. ServerStmp \t\t" + smtpServer );
			LOGGER.info( "\t. ServerPort \t\t" + smtpPort );
			LOGGER.info( "\t. User Smtp  \t\t" + smtpUser );
			LOGGER.info( "\t. User \t\t\t\t" + user );
//			LOGGER.info( "\t. Password  \t\t" + password );
			LOGGER.info( "\t. to  \t\t\t\t" + to );
			LOGGER.info( "\t. from  \t\t\t" + from );
			LOGGER.info( "\t. USAR SSL  \t\t" + ssl );
			LOGGER.info( "\t. SUBJECT \t\t\t" + subject );
			LOGGER.info( "\t. MSG \t\t\t\t" + message );
			LOGGER.info( "\t. TLS \t\t\t\t" + tls );
		}
	}

    /**
     * Cria e define as propriedades da configura��o de envio de e-mail.
     * @param usarSSL
     * @return
     */
	private Properties createProperties() {
		Properties props = new Properties();

		// -------- novas prop..
		
		// ssl config
		String auth = "mail.smtps.auth";
		String portProp = "mail.smtps.port";
		String port = "465";
		
		if (tls) {
			props.put( "mail.smtp.starttls.enable", true );
		}
		
		if (!ssl) {
			// non ssl
//			auth = "mail.smtp.auth";
			portProp = "mail.smtp.port";
			port = "25";
		}
		props.put( auth, true );

		props.put( "mail.smtp.auth", true );	// sem essa propriedade nao funciona pro gmail
		
		// ----------
		props.put( portProp, port );
		
		
        props.put( "mail.smtp.host", this.smtpServer );
        props.put( "mail.debug", debug );
        props.put( "mail.smtp.user", this.smtpUser );
        props.put( "mail.transport.protocol", "smtp" );



        // Bypass the SSL authentication
        props.put( "mail.smtp.ssl.enable", false );

        props.setProperty( "mail.smtp.ssl.trust", smtpServer );

        if ( ssl ) {
            props.put( "mail.smtp.starttls.enable", "true" );
            props.put( "mail.smtp.socketFactory.port", this.smtpPort );
            props.put( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
            props.put( "mail.smtp.socketFactory.fallback", "false" );
        }
		return props;
	}

	/**
	 * X509TrustManager implementation.
	 * @author robson.ramos
	 *
	 */
    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted( X509Certificate[] xcs, String string ) throws CertificateException {
        }

        @Override
        public void checkServerTrusted( X509Certificate[] xcs, String string ) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    /**
     * {@link Authenticator} implementation.
     * 
     * @author robson.ramos
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        /**
         * User and password.
         */
        private String user, password;

        public SMTPAuthenticator( String user, String password ) {
            this.user = user;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication( this.user, this.password );
        }
    }

    
    
    /*
     * test.
     * @param args
     
    public static void main( String[] args ) {

        System.out.println( " \n====== Teste Email ============== \n" );
        
//         SendMail( String to, String from, String smtpServer, String smtpPort, 
//          String smtpUser, String user, String password, String subject, String message ) 
        
        String to = "robsonrf@gmail.com";
        String pass = "safira123qwe";	// modificar dados para teste
        String usuario = "ehs.email.testefpf@gmail.com";
        String smtpServer = "smtp.gmail.com";
        String smtpPort = "465";		// 587
        String subject = "[EHS] Teste de envio de e-mail";
        String message = "[EHS] Envio de mensagens pelo smtp GMAIL. \n \n";
        
		SendMail send = new SendMail( to, usuario, smtpServer, smtpPort,
									usuario, usuario, pass, subject, message, true, true, false);
        
        if ( send.execute() ) {
            System.out.println( "E-mail enviado com sucesso usando ssl." );
        } else {
            System.out.println( "Erro ao enviar usando ssl." );
        }
        System.out.println( "\nx-x--x--x--x-x-x--x-x--x-xx--- \n" );

    }
    */
    

    /* -------------- Get/Set ------------------- */

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
    

	
    public boolean getDebug() {
		return debug;
	}

	public boolean isTls() {
		return tls;
	}

}
