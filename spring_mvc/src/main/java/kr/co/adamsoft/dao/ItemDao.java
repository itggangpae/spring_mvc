package kr.co.adamsoft.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.adamsoft.domain.Item;

@Repository
public class ItemDao {
	/*
	@Autowired
	private SqlSession sqlSession;

	public List<Item> getAll() {
		return sqlSession.selectList("itemmapper.allItem");
	}
	
	public Item getItem(Integer itemid) {
		return sqlSession.selectOne("itemmapper.getItem", itemid);
	}
	*/
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Item> getAll() {
		CriteriaQuery<Item> criteriaQuery = sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(Item.class);
		criteriaQuery.from(Item.class);
		List<Item> list = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return list;
	}

	public Item getItem(Integer itemid) {
		Item item = (Item)sessionFactory.getCurrentSession().get(Item.class, itemid);
		return item;
	}


}