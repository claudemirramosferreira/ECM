package br.com.interactive.alfresco.model;

import java.io.Serializable;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 2013023643336275550L;
	
	private String userName; // "claudemirferreira"
	private boolean	enabled; // true
	private String firstName; // "Claudemir"
	private String lastName; // "Ferreira"
	private String jobtitle; // "Analista"
	private String organization; // "Fucapi"
	private String organizationId; // "fucapi"
	private String location;
	private String telephone;
	private String mobile;
	private String email;
	private String companyaddress1;
	private String companyaddress2;
	private String companyaddress3;
	private String companypostcode;
	private String companytelephone;
	private String companyfax;
	private String companyemail;
	private String skype;
	private String instantmsg;
	private String userStatus;
	private String googleusername;
	private boolean emailFeedDisabled;
	private String persondescription;
	private String ticket;
	private String senha;
	private List<GrupoAlfresco> groups;
	private Capabilities capabilities;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyaddress1() {
		return companyaddress1;
	}
	public void setCompanyaddress1(String companyaddress1) {
		this.companyaddress1 = companyaddress1;
	}
	public String getCompanyaddress2() {
		return companyaddress2;
	}
	public void setCompanyaddress2(String companyaddress2) {
		this.companyaddress2 = companyaddress2;
	}
	public String getCompanyaddress3() {
		return companyaddress3;
	}
	public void setCompanyaddress3(String companyaddress3) {
		this.companyaddress3 = companyaddress3;
	}
	public String getCompanypostcode() {
		return companypostcode;
	}
	public void setCompanypostcode(String companypostcode) {
		this.companypostcode = companypostcode;
	}
	public String getCompanytelephone() {
		return companytelephone;
	}
	public void setCompanytelephone(String companytelephone) {
		this.companytelephone = companytelephone;
	}
	public String getCompanyfax() {
		return companyfax;
	}
	public void setCompanyfax(String companyfax) {
		this.companyfax = companyfax;
	}
	public String getCompanyemail() {
		return companyemail;
	}
	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getInstantmsg() {
		return instantmsg;
	}
	public void setInstantmsg(String instantmsg) {
		this.instantmsg = instantmsg;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getGoogleusername() {
		return googleusername;
	}
	public void setGoogleusername(String googleusername) {
		this.googleusername = googleusername;
	}
	public boolean isEmailFeedDisabled() {
		return emailFeedDisabled;
	}
	public void setEmailFeedDisabled(boolean emailFeedDisabled) {
		this.emailFeedDisabled = emailFeedDisabled;
	}
	public String getPersondescription() {
		return persondescription;
	}
	public void setPersondescription(String persondescription) {
		this.persondescription = persondescription;
	}
	
	public String getTicket() {
		return this.ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<GrupoAlfresco> getGroups() {
		return groups;
	}
	public void setGroups(List<GrupoAlfresco> groups) {
		this.groups = groups;
	}
	
	public Capabilities getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}
	public static Usuario fromJsonToUsuario(String json) {
		return new JSONDeserializer<Usuario>().use(null, Usuario.class).use("capabilities", Capabilities.class).deserialize(json);
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
}
