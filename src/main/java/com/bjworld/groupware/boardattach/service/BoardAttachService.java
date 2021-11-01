package com.bjworld.groupware.boardattach.service;

import java.util.List;

import com.bjworld.groupware.boardattach.service.impl.BoardAttachVO;


public interface BoardAttachService {

	List<BoardAttachVO> selectBoardAttachList(BoardAttachVO paramVO) throws Exception;

    Integer selectBoardAttachListTotCnt(BoardAttachVO paramVO) throws Exception;

    BoardAttachVO selectBoardAttach(BoardAttachVO paramVO) throws Exception;

    void mergeBoardAttach(BoardAttachVO paramVO) throws Exception;

    void insertBoardAttach(BoardAttachVO paramVO) throws Exception;

    void updateBoardAttach(BoardAttachVO paramVO) throws Exception;

    void deleteBoardAttach(BoardAttachVO paramVO) throws Exception;

	void deleteBoardAttachByBoardSeq(BoardAttachVO paramVO) throws Exception;
}
