package pi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(max = 100, message = "Tamanho até 100 caracteres")
	private String nome;
	@Size(max = 12, message = "Tamanho até 12 caracteres")
	private String cpf;
	@Size(max = 12, message = "Tamanho até 12 caracteres")
	private String rg;
	@Size(max = 60, message = "Tamanho até 60 caracteres")
	private String eMail;
	@Size(max = 300, message = "Tamanho até 300 caracteres")
	private String idImagem;
	@Size(max = 300)
	private String endereco;
	@Size(max = 10)
	private String cep;
	@Size(max = 10)
	private String tipoLogradouro;
	@Size(max = 10)
	private String numero;
	@Size(max = 100)
	private String bairro;
	@Size(max = 100)
	private String cidade;
	@Size(max = 2)
	private String estado;
	@Size(max = 100)
	private String pais;
	@Size(max = 20)
	private String telefone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(String idImagem) {
		this.idImagem = idImagem;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", eMail=" + eMail
				+ ", idImagem=" + idImagem + ", endereco=" + endereco + ", cep=" + cep + ", tipoLogradouro="
				+ tipoLogradouro + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", pais=" + pais + ", telefone=" + telefone + "]";
	}

}