package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

import java.util.*;

@Mapper
@Repository
public interface FoodMapper {
	/*
	 * <select id="foodListData" resultType="com.sist.web.vo.FoodVO" parameterType="hashmap">
			SELECT no,poster,name,score,theme,type
			FROM food
			
			<if test="search!=null and search!=''">
				WHERE address LIKE CONCAT('%',#{search},'%')
			</if>
			
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
	*/
	public List<FoodVO> foodListData(Map map);
	
	/*
		<select id="foodListTotalPage" resultType="int" parameterType="string">
			SELECT count(*)
			FROM food
			<if test="search!=null and search!=''">
				WHERE address LIKE CONCAT('%',#{search},'%')
			</if>
		</select>
	 */
	public int foodListTotalPage(String search);
	
	/*
	 * 	<select id="foodDetailData" resultType="com.sist.web.vo.FoodVO" parameterType="int">
			SELECT * FROM food
			WHERE no=#{no}
		</select>
	 */
	public FoodVO foodDetailData(int no);
}
