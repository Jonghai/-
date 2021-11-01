package com.bjworld.groupware.board.service;

import java.util.List;

import com.bjworld.groupware.board.service.impl.BoardVO;


public interface BoardService {
	List<BoardVO> selectBoardList(BoardVO paramVO) throws Exception;

    Integer selectBoardListTotCnt(BoardVO paramVO) throws Exception;

    BoardVO selectBoard(BoardVO paramVO) throws Exception;

    void mergeBoard(BoardVO paramVO) throws Exception;

    void insertBoard(BoardVO paramVO) throws Exception;

    void updateBoard(BoardVO paramVO) throws Exception;

    void deleteBoard(BoardVO paramVO) throws Exception;

	void saveBoard(BoardVO paramVO) throws Exception;

	void updateBoardItemReadCount(BoardVO paramVO);

	BoardVO selectNextPrevBoard(BoardVO paramVO);
}
