package pi.entity;

import java.io.File;
import java.util.ArrayList;

public class Azure {

	// Atributos
	private String codAzure;
	private ArrayList<File> fotos;

	// Construtores
	public Azure() {

	}

	public Azure(String codAzure) {
		this.codAzure = codAzure;
	}

	// Métodos Get e Set
	public String getCodAzure() {
		return codAzure;
	}

	public void setCodAzure(String codAzure) {
		this.codAzure = codAzure;
	}

	public ArrayList<File> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<File> fotos) {
		this.fotos = fotos;
	}

	// Método ToString
	@Override
	public String toString() {
		return "Azure [codAzure=" + codAzure + ", fotos=" + fotos + "]";
	}

}
