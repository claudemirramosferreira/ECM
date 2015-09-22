package br.interactive.ecm.internationalization;

import java.util.Locale;

/**
 * Classe que prov� servi�os de idioma.
 * @author robson.ramos
 */
public final class LocaleBuilder {

	/** private para previnir instancia��o. */
	private LocaleBuilder() { }

    /**
     * Linguagem/locale atendidas pelo sistema.
     */
    public static enum Language {
    	PORTUGUES("pt", "BR"),
    	INGLES("en", "US");
    	
    	private final String language;
    	private final String country;

		private Language(String lang, String country) {
			this.language = lang;
			this.country = country;
		}

		@Override
		public String toString() {
			return language + "_" + country;
		}
		
		public String getLanguage() {
			return language;
		}
		public String getCountry() {
			return country;
		}
    }

	/**
	 * Cria um {@link Locale} com os par�metros informados.
	 * @param language
	 * @param country
	 * @return
	 */
	public static Locale createLocale(String language, String country ) {
		return new Locale.Builder().setLanguage(language).setRegion(country).build();
	}

	/**
	 * Cria um {@link Locale} com os par�metros informados.
	 * @param language
	 * @return
	 */
	public static Locale createLocale( Language language ) {
		return new Locale.Builder().setLanguage(language.getLanguage()).setRegion(language.getCountry()).build();
	}
	
    /**
     * Obtem a linguagem atual do sistema.
     * @return
     */
    public static Language getCurrentLanguage() {
    	if (Language.PORTUGUES.getLanguage().equals(getDefaultLocale().getLanguage())) {
    		return Language.PORTUGUES;
    	}
    	return Language.INGLES;
    }

	public static Locale getDefaultLocale() {
		return java.util.Locale.getDefault();
	}
	
}
