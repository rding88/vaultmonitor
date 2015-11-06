package com.intelliwaves.vaultmonitor;

public class Supervisor implements Vaultmsg {
	private int id;
	
	public Supervisor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String showmsg() {
		return "Supervisor " + this.getId() + ", someone has walked into the vault";		
	}
}
