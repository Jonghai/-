package com.bjworld.groupware.common.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bjworld.groupware.common.util.EgovBasicLogger;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception { 

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		String responseMessage = "<script>alert('처리중 오류가 발생하였습니다.'); history.back(); </script>";
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.write(responseMessage);
		out.flush();
		out.close();
	}
}
