package model;

public class JavaBeans {
private String idcon;
private String nome;
private String CPF;
private String contribuicao;

public JavaBeans() {
	super();
  
}

public JavaBeans(String idcon, String nome, String cPF, String contribuicao) {
	super();
	this.idcon = idcon;
	this.nome = nome;
	CPF = cPF;
	this.contribuicao = contribuicao;
}

public String getIdcon() {
	return idcon;
}
public void setIdcon(String idcon) {
	this.idcon = idcon;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCPF() {
	return CPF;
}
public void setCPF(String cPF) {
	CPF = cPF;
}
public String getContribuicao() {
	return contribuicao;
}
public void setContribuicao(String contribuicao) {
	this.contribuicao = contribuicao;
}

}
