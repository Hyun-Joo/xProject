package com.example.xProject.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.xProject.model.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> getBoardList(String search_option, String keyword, int start, int end) 
			throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword","%"+keyword+"%");
		map.put("start", start); //맵에 자료 저장
		map.put("end", end);
		return sqlSession.selectList("board.getBoardList",map);
	}
	
	@Override
	public BoardDTO getBoardContent(int bid) throws Exception {
		return sqlSession.selectOne("board.getBoardContent", bid);
	}
	
	@Override
	public void getInsertBoard(BoardDTO boardDTO) throws Exception {
		sqlSession.insert("board.getInsertBoard", boardDTO);		
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		sqlSession.update("board.updateBoard", boardDTO);
	}
	
	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSession.insert("board.deleteBoard", bid);
	}
	
	@Override
	public int updateViewCnt(int bid) throws Exception {
		return sqlSession.update("board.updateViewCnt", bid);
	}
	
	@Override
	public int countBoard(String search_option, String keyword) throws Exception {
		Map<String,String> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		return sqlSession.selectOne("board.countBoard",map);
	}
}
