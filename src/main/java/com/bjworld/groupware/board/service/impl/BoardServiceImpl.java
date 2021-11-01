package com.bjworld.groupware.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bjworld.groupware.board.service.BoardService;
import com.bjworld.groupware.boardattach.service.BoardAttachService;
import com.bjworld.groupware.boardattach.service.impl.BoardAttachVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	@Resource(name="boardattachService")
    private BoardAttachService boardAttachService;
	
	@Override
    public List<BoardVO> selectBoardList(BoardVO paramVO) throws Exception {
        return boardMapper.selectBoardList(paramVO);
    }

    @Override
    public Integer selectBoardListTotCnt(BoardVO paramVO) throws Exception {
        return boardMapper.selectBoardListTotCnt(paramVO);
    }

    @Override
    public BoardVO selectBoard(BoardVO paramVO) throws Exception {
        return boardMapper.selectBoard(paramVO);
    }

    @Override
    public void mergeBoard(BoardVO paramVO) throws Exception {
        boardMapper.mergeBoard(paramVO);
    }

    @Override
    public void insertBoard(BoardVO paramVO) throws Exception {
        boardMapper.insertBoard(paramVO);
    }

    @Override
    public void updateBoard(BoardVO paramVO) throws Exception {
        boardMapper.updateBoard(paramVO);
    }

    @Override
    public void deleteBoard(BoardVO paramVO) throws Exception {
        boardMapper.deleteBoard(paramVO);
    }

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void saveBoard(BoardVO paramVO) throws Exception {
		
		String boardSeq = paramVO.getSeq();
		
		mergeBoard(paramVO);
		
		if(StringUtils.isEmpty(boardSeq)) {
			boardSeq = paramVO.getSeq();
		}
	
		if(!StringUtils.isEmpty(boardSeq) 
				&& paramVO.getAttachOriFileName() != null 
				&& paramVO.getAttachFileSize().length > 0) {
			
			for (int i = 0; i < paramVO.getAttachOriFileName().length; i++) {
				BoardAttachVO attachVO = new BoardAttachVO();
				attachVO.setBoardSeq(boardSeq);
				attachVO.setMenuSeq(paramVO.getMenuSeq());
				attachVO.setBoardCode(paramVO.getBoardCode());
				attachVO.setOriFileName(paramVO.getAttachOriFileName()[i]);
				attachVO.setSaveFileName(paramVO.getAttachSaveFileName()[i]);
				attachVO.setFileSize(paramVO.getAttachFileSize()[i]);
				
				boardAttachService.insertBoardAttach(attachVO);
			}
		}
	}

	@Override
	public void updateBoardItemReadCount(BoardVO paramVO) {
		// TODO Auto-generated method stub
		boardMapper.updateBoardItemReadCount(paramVO);
	}

	@Override
	public BoardVO selectNextPrevBoard(BoardVO paramVO) {
		// TODO Auto-generated method stub
		return boardMapper.selectNextPrevBoard(paramVO);
	}
}
