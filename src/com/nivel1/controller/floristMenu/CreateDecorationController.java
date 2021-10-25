package com.nivel1.controller.floristMenu;

import com.nivel1.controller.Controller;
import com.nivel1.model.domain.ActiveFlorist;
import com.nivel1.model.domain.Decoration;
import com.nivel1.model.domain.Material;
import com.nivel1.model.persistence.FloristRepository;
import com.nivel1.view.MessageView;
import com.nivel1.view.ReadInfoWindow;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class CreateDecorationController extends Controller {
	private FloristRepository floristRep;

	public CreateDecorationController(Session session) {
		super(session);
		this.floristRep = FloristRepository.instance();
	}

	public void control() {
		Decoration decoration = this.createDecoration();
		if (decoration != null){
			this.floristRep.add(decoration, ActiveFlorist.instance().getId());
			ShowInfoWindow.showInfo("DECORACION AÑADIDA AL STOCK");
		}
	}

	private Decoration createDecoration() {
		Decoration decoration = null;
		String name = ReadInfoWindow.readString(MessageView.NAME_PRODUCT);
		double price = 0;
		String material = "";
		if (name != null){
			price = ReadInfoWindow.readDouble(MessageView.PRICE_PRODUCT);
			if (price != 0){
				material = ReadInfoWindow.readString(MessageView.MATERIAL_DECORATION);
				if (material != null){
					if (Material.exists(material)){
						decoration = new Decoration(name, price, material);
					} else {
						ShowInfoWindow.showInfo("PRODUCTO NO CREADO. MATERIAL INEXISTENTE");
					}
				}
			}
		}
		return decoration;
	}
}
