package com.example.xProject.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.xProject.model.board.dto.BoardDTO;

public interface BoardService {

	public List<BoardDTO> getBoardList(String search_option, String keyword, int start, int end) throws Exception;
	public void getInsertBoard(BoardDTO dto) throws Exception;
	public BoardDTO getBoardContent(int bid, HttpSession session) throws Exception;
	public void updateBoard(BoardDTO boardDTO) throws Exception;	
	public void deleteBoard(int bid) throws Exception;
	public int countBoard(String search_option, String keyword) throws Exception;
	
}
