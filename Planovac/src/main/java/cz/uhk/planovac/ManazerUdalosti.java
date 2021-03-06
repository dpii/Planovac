package cz.uhk.planovac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ManazerUdalosti {
	
	int vyhledavaniPo = 30 ;//casove useky (min) po kterych se bude zkouset shoda se zadanim - vetsi cislo znamena vice moznych terminu, ale je to pomalejsi 
	
	public void setVyhledavaniPo(int vyhledavaniPo) {
		this.vyhledavaniPo = vyhledavaniPo;
	}


	public ArrayList<Udalost> najdiVhodnyCasProUdalost (Date minZacatek, Date maxKonec, int pozadovanaDelka, int odHodin, int doHodin, ArrayList<ArrayList<Udalost>> seznamPlanu)
	{
		ArrayList<Udalost> mozneTerminy = vytvorSeznamMoznychTerminu(minZacatek, maxKonec, pozadovanaDelka, odHodin, doHodin);
		ArrayList<Integer> poctyKonfliktu = vyhledaniPoctuKonfliktu(mozneTerminy, seznamPlanu);
		ArrayList<Udalost> vhodneTerminy = new ArrayList<Udalost>();
		
		int minimum = seznamPlanu.size();
		for(int i=0;i<poctyKonfliktu.size();i++)
		{
			if(poctyKonfliktu.get(i)<minimum)
			{
				minimum = poctyKonfliktu.get(i);
			}
		}
		for(int i=0;i<poctyKonfliktu.size();i++)
		{
			if(poctyKonfliktu.get(i)==minimum)
			{
				Udalost u = mozneTerminy.get(i);
				u.setNazev(""+poctyKonfliktu.get(i));
				vhodneTerminy.add(u);
			}
			mozneTerminy.get(i).setNazev(""+poctyKonfliktu.get(i));//testov�n�!!!
		}
		//return vhodneTerminy;
		return mozneTerminy;
	}
	
	
	public ArrayList<Integer> vyhledaniPoctuKonfliktu (ArrayList<Udalost> mozneTerminy, ArrayList<ArrayList<Udalost>> seznam)//predpoklada chronologicke razeni udalosti v seznamu!
	{
		ArrayList<Integer> poctyKonfliktu = new ArrayList<Integer>();
		int pocetKonfliktu;
		for (int j=0;j<mozneTerminy.size();j++)
		{
			pocetKonfliktu = 0;
			for (ArrayList<Udalost> udalosti : seznam) 
			{
				for(int i=0;i<udalosti.size();i++)
				{
					//String test2 = mozneTerminy.get(j).getZacatek().toString()+" a� "+mozneTerminy.get(j).getKonec().toString();//testovac�!!!
			    	//String test3 = udalosti.get(i).getZacatek().toString()+" a� "+udalosti.get(i).getKonec().toString();//testovac�!!!
					int x = konflikt(mozneTerminy.get(j), udalosti.get(i));
					if(x==0)
						pocetKonfliktu++;
					if(x>-1)
						i=udalosti.size();
				}
			}
			poctyKonfliktu.add(pocetKonfliktu);
		}
		return poctyKonfliktu;
	}
	
	public int konfliktSeznamu (ArrayList<Udalost> udalosti1, ArrayList<Udalost> udalosti2)//predpoklada chronologicke razeni udalosti v seznamu!
	{
		int pocetKonfliktu = 0;
		for (Udalost udalost : udalosti1) {
			for(int i=0;i<udalosti2.size();i++)
			{
				int x = konflikt(udalost, udalosti2.get(i));
				if(x==0 && udalost.getIdUdalosti()!=udalosti2.get(i).getIdUdalosti())//vyzaduje idUdalosti (ostatni metody ne - kvuli prazdnym udalostem pouzitym jen na casove rozmezi) - aby nevyhodnotilo jako konflikt, kdyz se 2 lide ucastni stejne udalosti
					pocetKonfliktu++;
				if(x<1)
					i=udalosti2.size();
			}
		}
		return pocetKonfliktu;
	}
	
	public int konflikt (Udalost udalost1, Udalost udalost2)//predpoklada, ze kazda udalost bude mit drive zacatek nez konec!
	{
		if(udalost1.getZacatek().after(udalost2.getKonec()))
			return -1;//udalost 2 je pred udalosti 1
		if(udalost2.getZacatek().after(udalost1.getKonec()))
			return 1;//udalost 1 je pred udalosti 2
		return 0;//existuje konflikt mezi udalostmi
	}
	
	
	public ArrayList<Udalost> vytvorSeznamMoznychTerminu(Date minZacatek, Date maxKonec, int pozadovanaDelka, int odHodin, int doHodin)
	{
		ArrayList<Udalost> mozneTerminy = new ArrayList<Udalost>();
		Calendar cas1 = Calendar.getInstance();
		Calendar cas2 = Calendar.getInstance();
		Calendar konec = Calendar.getInstance();
		
		konec.setTime(maxKonec);
		konec = zaokrouhli(konec);
		konec.add(Calendar.DAY_OF_MONTH, 1);
	    cas1.setTime(minZacatek);
	    cas1 = zaokrouhli(cas1);
	    
	    cas1.set(Calendar.HOUR_OF_DAY, odHodin);
	    cas2.setTime(cas1.getTime());
	    cas2.add(Calendar.MINUTE, pozadovanaDelka);
	    while (cas2.compareTo(konec)<=0)
	    {
	    	/*String test1 = cas1.getTime().toString();//testovac�!!!
	    	String test2 = cas2.getTime().toString();//testovac�!!!
	    	String test3 = konec.getTime().toString();//testovac�!!!
*/		    if(cas2.get(Calendar.HOUR_OF_DAY)<doHodin && cas2.get(Calendar.HOUR_OF_DAY)>=odHodin)
		    {
		    	mozneTerminy.add(new Udalost(cas1.getTime(),cas2.getTime()));
		    	cas1.add(Calendar.MINUTE, vyhledavaniPo);
		    }
		    else
		    {
		    	//if(cas2.get(Calendar.HOUR_OF_DAY)>doHodin)
		    		cas1.add(Calendar.DAY_OF_MONTH, 1);
		    	cas1.set(Calendar.HOUR_OF_DAY, odHodin);
		    }
		    cas2.setTime(cas1.getTime()); 
		    cas2.add(Calendar.MINUTE, pozadovanaDelka);
		}
	    
		return mozneTerminy;
	}
	
	public ArrayList<Udalost> seradUdalosti(Collection<Udalost> udalosti)
	{
		ArrayList<Udalost> serazenyList = new ArrayList<Udalost>(udalosti);
		Collections.sort(serazenyList, new UdalostComparator());
		return serazenyList;
	}
	
	public List<Udalost> getNAktualnichUdalosti(ArrayList<Udalost> udalosti, int pocet)
	{
		Calendar ted = Calendar.getInstance();
		for(int i = 0;i<udalosti.size();i++)
		{
			if(udalosti.get(i).getKonec().after(ted.getTime()))
				return udalosti.subList(i, Math.min(i+pocet, udalosti.size()));
		}
		return udalosti.subList(0,0);
	} 
	
	public Calendar zaokrouhli(Calendar cal)
	{
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public Date zaokrouhli(Date dat)
	{
		return zaokrouhli(dat, 0);
	}
	
	public Date zaokrouhli(Date dat, int hodina)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, hodina);
		dat = cal.getTime();
		return dat;
	}
	
	public int rozdilDat(Date cal1, Date cal2)
	{
		long rozdil = cal1.getTime() - cal2.getTime();
		int rozdilMin = (int)(rozdil / (60 * 1000)); 
		return Math.abs(rozdilMin);
	}
}
