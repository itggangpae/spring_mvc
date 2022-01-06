package kr.co.adamsoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import kr.co.adamsoft.domain.Item;

@Repository
public interface ItemMapper {
	@Select("select * from item")
	public List<Item> getAll();
	
	@Select("select * from item where itemid=#{itemid}")
	public Item getItem(Integer itemid);
}
