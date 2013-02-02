package cz.uhk.planovac;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

public class Vyhledavani {

	private Date odData;
	private Date doData;
	private Integer odHodiny;
	private Integer doHodiny;
	private Skupina skupina;
	private Integer delka;
	private boolean jenVOsobnimPlanu;
	
	
	public boolean isJenVOsobnimPlanu() {
		return jenVOsobnimPlanu;
	}
	public void setJenVOsobnimPlanu(boolean jenVOsobnimPlanu) {
		this.jenVOsobnimPlanu = jenVOsobnimPlanu;
	}
	public Date getOdData() {
		return odData;
	}
	public void setOdData(Date odData) {
		this.odData = odData;
	}
	public Date getDoData() {
		return doData;
	}
	public void setDoData(Date doData) {
		this.doData = doData;
	}
	public Integer getOdHodiny() {
		return odHodiny;
	}
	public void setOdHodiny(Integer odHodiny) {
		this.odHodiny = odHodiny;
	}
	public Integer getDoHodiny() {
		return doHodiny;
	}
	public void setDoHodiny(Integer doHodiny) {
		this.doHodiny = doHodiny;
	}
	public Skupina getSkupina() {
		return skupina;
	}
	public void setSkupina(Skupina skupina) {
		this.skupina = skupina;
	}
	public Integer getDelka() {
		return delka;
	}
	public void setDelka(Integer delka) {
		this.delka = delka;
	}
	
	public ArrayList<Udalost> provedHledani( String login, Planovac planovac)
	{
		ManazerUdalosti manazerUdalosti = new ManazerUdalosti();
		ArrayList<ArrayList<Udalost>> seznam = new ArrayList<ArrayList<Udalost>>();
		if(skupina==null || jenVOsobnimPlanu)
		{
			Uzivatel uzivatel = planovac.nactiUzivatelePodleLoginu(SecurityContextHolder.getContext().getAuthentication().getName());
			seznam.add(uzivatel.getSeznamUdalostiSerazene());
		}
		else
		{
			seznam = planovac.nactiVsechnyUdalostiClenuSkupiny(skupina);
		}
		ArrayList<Udalost> udalosti = manazerUdalosti.najdiVhodnyCasProUdalost(odData, doData, 
				delka,odHodiny, doHodiny, seznam);
		return udalosti;
	}
	
}
