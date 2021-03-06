package com.toutiao.dao;

import com.toutiao.pojo.NewsInfo;
import com.toutiao.pojo.NewsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int countByExample(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int deleteByExample(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int insert(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int insertSelective(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    List<NewsInfo> selectByExample(NewsInfoExample example);
    
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    NewsInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int updateByExampleSelective(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int updateByExample(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int updateByPrimaryKeySelective(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated Mon Feb 26 11:37:33 CST 2018
     */
    int updateByPrimaryKey(NewsInfo record);

	List<NewsInfo> queryNewsBySingleCondition(NewsInfo news);

	Integer queryTotalNumOfTag(String tag);

	Integer querystartNumOfTag(String tag);

	List<NewsInfo> queryNewsByPage(@Param("tag") String tag,@Param("start") Integer start, @Param("pageShow") Integer pageShow);
	
	
}