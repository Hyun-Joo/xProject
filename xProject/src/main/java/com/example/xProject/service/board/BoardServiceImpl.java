package com.example.xProject.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.xProject.model.board.dao.BoardDAO;
import com.example.xProject.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDao;

	@Override
	public List<BoardDTO> getBoardList(String search_option, String keyword, int start, int end) throws Exception {
		return boardDao.getBoardList(keyword, keyword, start, end);
	}
	
	@Override
	public void getInsertBoard(BoardDTO boardDTO) throws Exception {
		boardDao.getInsertBoard(boardDTO);
	}
	
	@Override
	public BoardDTO getBoardContent(int bid, HttpSession session) throws Exception{
		long update_time=0;
		if(session.getAttribute("update_time"+bid)!=null) {
			update_time = (long)session.getAttribute("update_time"+bid);
		}
		long current_time = System.currentTimeMillis();
		if((current_time-update_time) > 10*1000) { //10초에 한 번 조회수 증가		
			boardDao.updateViewCnt(bid);
			session.setAttribute("update_time"+bid, current_time);
		}
		return boardDao.getBoardContent(bid);
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		boardDao.updateBoard(boardDTO);
	}
	
	@Override
	public void deleteBoard(int bid) throws Exception {
		 boardDao.deleteBoard(bid);
	}
	
	@Override
	public int countBoard(String search_option, String keyword) throws Exception {
		return boardDao.countBoard(search_option, keyword);
	}	
}
