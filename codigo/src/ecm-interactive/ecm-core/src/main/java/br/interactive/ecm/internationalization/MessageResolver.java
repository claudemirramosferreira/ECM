package br.interactive.ecm.internationalization;

import br.interactive.ecm.internationalization.LocaleBuilder.Language;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Classe utilitária para manipulação de mensagem.
 * @author robson.ramos
 */
public final class MessageResolver {
	
	// FIXME verificar se os properties podem ficar nos seus projetos (ehs_seguranca) inves de estarem no core.

	/** Current Locale. */
	private static Locale currentLocale;
	
	/** Utilitary class. */
	private MessageResolver() { }

	/**
	 * Default initialization.
	 */
	static {
		final String language = Locale.getDefault().getLanguage();
		final String country = Locale.getDefault().getCountry();
		currentLocale = LocaleBuilder.createLocale(language, country); 
	}
	
	/* FIXME: atualizar o currentLocale quando modificar a linguagen na aplica��o 
	 * (quando implementar o botao de mudan�a de idioma no home). */
	
	/**
	 * Modifica o {@link Locale} a ser utilizado na aplicação.
	 * @param locale
	 */
	public static void changeLocale(final Locale locale) {
		currentLocale = locale;
	}

	/**
	 * Modifica o {@link Locale} a ser utilizado na aplicação.
	 * @param language
	 */
	public static void changeLocale(final Language language) {
		currentLocale = LocaleBuilder.createLocale( language );
	}
	
	/**
	 * Modifica o {@link Locale} a ser utilizado na aplicação.
	 * @param language
	 * @param country
	 */
	public static void changeLocale(final String language, final String country) {
		currentLocale = LocaleBuilder.createLocale(language, country);
	}
	
	/**
	 * Traduz o property no idioma da aplicação.
	 * @param msgBundle String of message: baseName.propertyName
	 * @return
	 */
	public static String interpolate(final String msgBundle) {
		return getMessage(msgBundle);
	}

	/**
	 * Traduz o property no idioma da aplicação.
	 * @param msgBundle String of message: baseName.propertyName
	 * @param params parametros adicionais para substituir na mensagem
	 * @return
	 */
	public static String interpolate(final String msgBundle, final String ... params ) {
		final String message = getMessage(msgBundle);
		return applyParams(message, params);
	}

	private static String applyParams(final String message, final String ... params) {
		String replacedMessage = message;
		if ( params != null ) {
			for (int idx = 0; idx < params.length; idx++) {
				final String paramOrder = "{" + idx + "}";	// convention: {0}, {1}, ..., {n}
				replacedMessage = replacedMessage.replace(paramOrder, params[idx]);
			}
		}
		return replacedMessage;
	}
	

	/**
	 * Retorna a propriedade do message informado.
	 * @param msgBundle
	 * @return
	 */
	private static String getMessage(final String msgBundle) {
		final PropertyBundle propertyBundle = extractProperty(msgBundle);
		final ResourceBundle bundle = ResourceBundle.getBundle(propertyBundle.getBundleName(), currentLocale);
		
		if ( bundle == null ) {
			// FIXME carregar o property do locale default default (pt_BR)
			throw new RuntimeException("PropertyNotFoundException! Implementar default...");	// FIXME Implementar default
		}
		
		return bundle.getString(propertyBundle.getPropertyName());
	}
	
	/**
	 * faz o split pra recuperar o message e o property a ser traduzido.
	 * @param msg
	 * @return {@link PropertyBundle}
	 */
	private static PropertyBundle extractProperty(final String msg) {
		return new PropertyBundle(msg.split("\\.") );
	}
	
	/**
	 * Representation for a property: bundle.property.
	 * @author robson.ramos
	 */
	private static final class PropertyBundle {
		
		private final String bundleName;
		
		private final String propertyName;

		public PropertyBundle(final String[] resourceBundle) {
			this.bundleName = resourceBundle[0];
			this.propertyName = resourceBundle[1];
		}

		public String getBundleName() {
			return bundleName;
		}

		public String getPropertyName() {
			return propertyName;
		}
		
	}
	
}


