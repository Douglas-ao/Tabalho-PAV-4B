package br.unipar.ambientes.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exemplo implements Serializable {

	private static final long serialVersionUID = 8604763334871572969L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String exemplo = "Informação de exemplo";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExemplo() {
		return exemplo;
	}

	public void setExemplo(String exemplo) {
		this.exemplo = exemplo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exemplo == null) ? 0 : exemplo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exemplo other = (Exemplo) obj;
		if (exemplo == null) {
			if (other.exemplo != null)
				return false;
		} else if (!exemplo.equals(other.exemplo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exemplo [id=" + id + ", exemplo=" + exemplo + "]";
	}

}
