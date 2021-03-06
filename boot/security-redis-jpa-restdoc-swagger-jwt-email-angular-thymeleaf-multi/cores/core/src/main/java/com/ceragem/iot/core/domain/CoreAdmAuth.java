package com.ceragem.iot.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.domain.base.AdmAuthBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="T_ADM_AUTH")
public class CoreAdmAuth extends AdmAuthBase {
	@ManyToOne
	@JoinColumn(name="AUTH_ID" , referencedColumnName  = "AUTH_ID",   insertable = false, updatable = false)
	@JsonView({JsonViewFrontEnd.class})
	private CoreAuth auth;
}
