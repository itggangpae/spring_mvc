package kr.co.adamsoft.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "itemid", "itemname", "price", "description", "pictureurl" })


@Data
public class Item {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;
}
