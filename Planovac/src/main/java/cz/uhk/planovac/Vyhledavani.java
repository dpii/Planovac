package cz.uhk.planovac;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

public class Vyhledavani {


	private Integer odHodiny;
	private Integer doHodiny;
	private Date odData;
	private Date doData;
	private Skupina skupina;
	private Integer delka;
	private boolean jenVOsobnimPlanu;
	private Integer vyhledavaniPo;
	
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
	
	public Integer getVyhledavaniPo() {
		return vyhledavaniPo;
	}
	public void setVyhledavaniPo(Integer vyhledavaniPo) {
		this.vyhledavaniPo = vyhledavaniPo;
	}
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
		ManazerUdalosti manazerUdalosti = new ManazerUdalosti();
		this.odData = manazerUdalosti.zaokrouhli(odData,odHodiny);
	}
	public Date getDoData() {
		return doData;
	}
	public void setDoData(Date doData) {
		ManazerUdalosti manazerUdalosti = new ManazerUdalosti();
		this.doData = manazerUdalosti.zaokrouhli(doData,doHodiny);
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
		
		//aby nebylo možno vyhledávat po pøíliš malých èástech
		int rozdilDat = manazerUdalosti.rozdilDat(odData, doData);
		manazerUdalosti.setVyhledavaniPo(Math.max(vyhledavaniPo,rozdilDat/100));
		
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
