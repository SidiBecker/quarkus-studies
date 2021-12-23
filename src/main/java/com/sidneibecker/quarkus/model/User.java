package com.sidneibecker.quarkus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "tb_user")
@SequenceGenerator(name = "gen_user", sequenceName = "gen_user", allocationSize = 1)
public class User extends PanacheEntityBase {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_user")
	private Long id;

	@Column(name = "tx_name")
	private String name;

	@Column(name = "tx_login")
	private String login;

	@Column(name = "tx_password")
	private String password;

	@Column(name = "dt_create")
	private Date createDate;

	@Column(name = "dt_update")
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
