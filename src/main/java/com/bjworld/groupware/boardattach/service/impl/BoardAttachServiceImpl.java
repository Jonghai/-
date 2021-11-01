package com.bjworld.groupware.boardattach.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.bjworld.groupware.boardattach.service.BoardAttachService;

@Service("boardattachService")
public class BoardAttachServiceImpl implements BoardAttachService{
	@Resource(name="boardattachMapper")
    private BoardAttachMapper boardattachMapper;

    @Override
    public List<BoardAttachVO> selectBoardAttachList(BoardAttachVO paramVO) throws Exception {
        return boardattachMapper.selectBoardAttachList(paramVO);
    }

    @Override
    public Integer selectBoardAttachListTotCnt(BoardAttachVO paramVO) throws Exception {
        return boardattachMapper.selectBoardAttachListTotCnt(paramVO);
    }

    @Override
    public BoardAttachVO selectBoardAttach(BoardAttachVO paramVO) throws Exception {
        return boardattachMapper.selectBoardAttach(paramVO);
    }

    @Override
    public void mergeBoardAttach(BoardAttachVO paramVO) throws Exception {
        boardattachMapper.mergeBoardAttach(paramVO);
    }

    @Override
    public void insertBoardAttach(BoardAttachVO paramVO) throws Exception {
        boardattachMapper.insertBoardAttach(paramVO);
    }

    @Override
    public void updateBoardAttach(BoardAttachVO paramVO) throws Exception {
        boardattachMapper.updateBoardAttach(paramVO);
    }

    @Override
    public void deleteBoardAttach(BoardAttachVO paramVO) throws Exception {
        boardattachMapper.deleteBoardAttach(paramVO);
    }

	@Override
	public void deleteBoardAttachByBoardSeq(BoardAttachVO paramVO) throws Exception {
		boardattachMapper.deleteBoardAttachByBoardSeq(paramVO);
	}
}
