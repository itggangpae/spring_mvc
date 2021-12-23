package kr.co.adamsoft.service;

import java.util.List;

import kr.co.adamsoft.domain.Item;

public interface ItemService {
	public List<Item> getAll(); 
	public Item getItem(Integer itemid);

}

