package com.example.xProject.model.board.dao;

import java.util.List;

import com.example.xProject.model.board.dto.BoardDTO;


public interface BoardDAO {

public List<BoardDTO> getBoardList(String search_option, String keyword, int start, int end) throws Exception;
public BoardDTO getBoardContent(int bid) throws Exception;
public void getInsertBoard(BoardDTO dto) throws Exception; //글쓰기
public void updateBoard(BoardDTO boardDTO) throws Exception;	
public int deleteBoard(int bid) throws Exception;
public int updateViewCnt(int bid) throws Exception;
public int countBoard(String search_option, String keyword) throws Exception;
}
