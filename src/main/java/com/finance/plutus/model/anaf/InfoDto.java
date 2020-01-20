package com.finance.plutus.model.anaf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Getter
@Setter
public class InfoDto {

	private Long cui;
	private String data;
	private String denumire;
	private String adresa;
	private Boolean scpTVA;
	@JsonProperty("data_inceput_ScpTVA")
	private String dataInceputScpTVA;
	@JsonProperty("data_sfarsit_ScpTVA")
	private String dataSfarsitScpTVA;
	@JsonProperty("data_anul_imp_ScpTVA")
	private String dataAnulImpScpTVA;
	@JsonProperty("mesaj_ScpTVA")
	private String mesajScpTVA;
	private String dataInceputTvaInc;
	private String dataSfarsitTvaInc;
	private String dataActualizareTvaInc;
	private String dataPublicareTvaInc;
	private String tipActTvaInc;
	private Boolean statusTvaIncasare;
	private String dataInactivare;
	private String dataReactivare;
	private String dataPublicare;
	private String dataRadiere;
	private Boolean statusInactivi;
	private String dataInceputSplitTVA;
	private String dataAnulareSplitTVA;
	private Boolean statusSplitTVA;
	private String iban;

}
