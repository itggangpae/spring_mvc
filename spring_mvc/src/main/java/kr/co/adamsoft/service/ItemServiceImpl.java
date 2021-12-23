package kr.co.adamsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.adamsoft.dao.ItemDao;
import kr.co.adamsoft.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;
	
	@Override
	@Transactional
	public List<Item> getAll() {
		return itemDao.getAll();
	}
	
	@Override
	@Transactional
	public Item getItem(Integer itemid) {
		return itemDao.getItem(itemid);
	}

}