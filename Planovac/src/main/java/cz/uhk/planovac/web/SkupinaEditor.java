package cz.uhk.planovac.web;

import java.beans.PropertyEditorSupport;

import cz.uhk.planovac.Planovac;
import cz.uhk.planovac.Skupina;


public class SkupinaEditor extends PropertyEditorSupport {

	private final Planovac planovac;


	public SkupinaEditor(Planovac planovac) {
		this.planovac = planovac;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		for (Skupina skupina : this.planovac.vemSkupiny()) {
			if (skupina.getNazev().equals(text)) {
				setValue(skupina);
			}
		}
	}

}
