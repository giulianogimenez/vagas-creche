package br.edu.fatecsjc.creche.dto;

import lombok.Data;

public @Data class OpcaoInstituicaoDTO {
	private Long instituicaoId;
	private Integer posicao;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpcaoInstituicaoDTO other = (OpcaoInstituicaoDTO) obj;
		if (instituicaoId == null) {
			if (other.instituicaoId != null)
				return false;
		} else if (!instituicaoId.equals(other.instituicaoId))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instituicaoId == null) ? 0 : instituicaoId.hashCode());
		return result;
	}
}
