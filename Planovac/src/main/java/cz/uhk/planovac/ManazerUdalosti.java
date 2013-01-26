package cz.uhk.planovac;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ManazerUdalosti {
	
	int vyhledavaniPo = 30*60 ;//časové úseky (s) po kterých se bude zkoušet shoda s termínem - větší číslo znamená méně možných termínů, ale je to rychlejší 
	
	public ArrayList<Udalost> najdiVhodnyCasProUdalost (Date minZacatek, Date maxKonec, int pozadovanaDelka, int odHodin, int doHodin, ArrayList<ArrayList<Udalost>> seznamPlanu)
	{
		ArrayList<Udalost> mozneTerminy = vytvorSeznamMoznychTerminu(minZacatek, maxKonec, pozadovanaDelka, odHodin, doHodin);
		ArrayList<Integer> poctyKonfliktu = vyhledaniPoctuKonfliktu(mozneTerminy, seznamPlanu);
		ArrayList<Udalost> vhodneTerminy = new ArrayList<Udalost>();
		
		int minimum = seznamPlanu.size();
		int minIndex = -1;
		for(int i=0;i<poctyKonfliktu.size();i++)
		{
			if(poctyKonfliktu.get(i)<minimum)
			{
				minimum = poctyKonfliktu.get(i);
				minIndex = i;
			}
		}
		if(minIndex>-1)
			vhodneTerminy.add(mozneTerminy.get(minIndex));//zatím vrací jen jeden - nejvhodnější a dává přednost dřívějšímu datu
		return vhodneTerminy;
	}
	
	
	public ArrayList<Integer> vyhledaniPoctuKonfliktu (ArrayList<Udalost> mozneTerminy, ArrayList<ArrayList<Udalost>> seznam)//předpokládá chronologické řazení událostí v seznamu!
	{
		ArrayList<Integer> poctyKonfliktu = new ArrayList<Integer>();
		int pocetKonfliktu = 0;
		for (int j=0;j<mozneTerminy.size();j++)
		{
			for (ArrayList<Udalost> udalosti : seznam) 
			{
				for(int i=0;i<udalosti.size();i++)
				{
					int x = konflikt(mozneTerminy.get(j), udalosti.get(i));
					if(x==0)
						pocetKonfliktu++;
					if(x<1)
						i=udalosti.size();
				}
			}
			poctyKonfliktu.add(pocetKonfliktu);
			pocetKonfliktu=0;
		}
		return poctyKonfliktu;
	}
	
	public int konfliktSeznamu (ArrayList<Udalost> udalosti1, ArrayList<Udalost> udalosti2)//předpokládá chronologické řazení událostí v seznamu!
	{
		int pocetKonfliktu = 0;
		for (Udalost udalost : udalosti1) {
			for(int i=0;i<udalosti2.size();i++)
			{
				int x = konflikt(udalost, udalosti2.get(i));
				if(x==0 && udalost.getIdUdalosti()!=udalosti2.get(i).getIdUdalosti())//vyžaduje idUdalosti (ostatní metody ne - kvůli prázdným událostem použitým jen na časové rozmezí) - aby nevyhodnotilo jako konflikt, když se 2 lidé účastní stejné události
					pocetKonfliktu++;
				if(x<1)
					i=udalosti2.size();
			}
		}
		return pocetKonfliktu;
	}
	
	public int konflikt (Udalost udalost1, Udalost udalost2)//předpokládá, že každá událost má začátek dříve než konec!
	{
		if(udalost1.getZacatek().after(udalost2.getKonec()))
			return -1;//událost 2 je před událostí 1
		if(udalost2.getZacatek().after(udalost1.getKonec()))
			return 1;//událost 1 je před událostí 2
		return 0;//existuje konflikt mezi událostmi
	}
	
	
	public ArrayList<Udalost> vytvorSeznamMoznychTerminu(Date minZacatek, Date maxKonec, int pozadovanaDelka, int odHodin, int doHodin)
	{
		ArrayList<Udalost> mozneTerminy = new ArrayList<Udalost>();
		Calendar cas1 = Calendar.getInstance();
		Calendar cas2 = Calendar.getInstance();
		
	    cas1.setTime(minZacatek);
	    while (cas2.getTime().after(maxKonec))
	    {
		    cas2.setTime(cas1.getTime()); 
		    cas2.add(Calendar.SECOND, pozadovanaDelka);
		    if(cas2.get(Calendar.HOUR_OF_DAY)<doHodin)
		    {
		    	mozneTerminy.add(new Udalost(cas1.getTime(),cas2.getTime()));
		    	cas1.add(Calendar.SECOND, vyhledavaniPo);
		    }
		    else
		    {
		    	cas1.add(Calendar.DAY_OF_MONTH, 1);
		    	cas1.set(Calendar.HOUR_OF_DAY, odHodin);
		    }
		}
	    
		return null;
	}
}
