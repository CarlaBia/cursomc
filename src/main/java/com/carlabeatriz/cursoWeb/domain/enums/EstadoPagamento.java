package com.carlabeatriz.cursoWeb.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "pendente"), QUITADO(2, "quitado"), CANCELADO(3, "cancelado");

	public Integer cod;
	public String descricao;

	private EstadoPagamento(Integer cod, String descrcao) {
		this.cod = cod;
		this.descricao = descrcao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) {
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id invalido: " + cod);
	}

}
