package model;

public class PosicaoModel {
	private Float hRelevo;
	private Float lineOfSight;
	private Float supFresnel;
	private Float infFresnel;
	
	public void setDados(Float hRelevo, Float lineOfSight, Float supFresnel, Float infFresnel) {
		this.hRelevo = hRelevo;
		this.lineOfSight = lineOfSight;
		this.supFresnel = supFresnel;
		this.infFresnel = infFresnel;
	}
		
}
