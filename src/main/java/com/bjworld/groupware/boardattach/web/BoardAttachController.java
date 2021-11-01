package com.bjworld.groupware.boardattach.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.boardattach.service.BoardAttachService;

@Controller
@RequestMapping("/boardattach")
public class BoardAttachController {
	Logger logger = LoggerFactory.getLogger(BoardAttachController.class);

    @Resource(name="boardattachService")
    private BoardAttachService boardattachService;
}
