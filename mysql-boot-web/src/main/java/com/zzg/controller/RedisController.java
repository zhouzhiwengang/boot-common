package com.zzg.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzg.entity.Book;
import com.zzg.redis.util.RedisUtil;


@Controller
@RequestMapping
public class RedisController {
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping(value="/one", method=RequestMethod.GET)
	
	public void one(){
		Book user = new Book();
		user.setId(1L);
		
		user.setName("redis 单节点");
		
		
		// hash string 设置
		boolean one = redisUtil.set("1", user);
		System.out.println("是否添加成功:" + one);
		// hash string 获取
		Book two = (Book) redisUtil.get("1");
		System.out.println("redis 对象获取:" + two.toString());
		
		//hash list 设置
		Book three = new Book();
		three.setId(2L);
		three.setName("redis 单节点");
		
		Book four = new Book();
		four.setId(3L);
		four.setName("redis 单节点");
		
		List<Book> list = new ArrayList<Book>();
		list.add(three);
		list.add(four);
		boolean five = redisUtil.lSet("2",list);
		System.out.println("List是否添加成功:" + five);
		List<Object> six = redisUtil.lGet("2", 0, 1);
		for(Object obj : six){
			ArrayList arrayList = (ArrayList) obj;
			arrayList.stream().forEach(item ->{
				System.out.println("redis 对象获取:" + item.toString());
			});
		}
		
		// hash set 设置
		Book eight = new Book();
		eight.setId(4L);
		eight.setName("redis 单节点");
	
		Book nine = new Book();
		nine.setId(4L);
		nine.setName("redis 单节点");
		
		Set<Book> set = new HashSet<Book>();
		set.add(eight);
		set.add(nine);
		
		long ten = redisUtil.sSet("3",set);
		System.out.println("HashSet是否添加成功:" + ten);
		Set<Object> sets = redisUtil.sGet("3");
		sets.stream().forEach(item ->{
			HashSet hashSet = (HashSet) item;
			hashSet.stream().forEach(entity ->{
				System.out.println("redis 对象获取:" + entity.toString());
			});			
		});
	}  

}
