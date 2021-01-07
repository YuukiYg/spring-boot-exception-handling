package com.yuukiyg.sample.bootexceptionhandling.app;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yuukiyg.sample.bootexceptionhandling.exception.SampleBusinessException;
import com.yuukiyg.sample.bootexceptionhandling.exception.SampleSystemException;

import lombok.extern.slf4j.Slf4j;

/**
 * 発生した例外ごとに、明示的に例外ハンドリングを行うクラス。<br>
 * ここで拾わなかった例外は、ErrorController(最後の砦)が拾ってくれる。<br>
 * @author yagiyuk
 *
 */
@RestControllerAdvice
@Slf4j
public class SampleGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * SampleBusinessException が発生すると、このメソッドが実行される。 <br>
	 * そして、エラーコード400でメッセージを返す。
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SampleBusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleBusinessException(SampleBusinessException e) {
		log.info("Catched SampleBusinessException at exception handler, and will return it as a GYOMU-ERROR.");
		return makeMessageBody("GYOMU-ERROR", "SampleBusinessException occurred... (;_;)");
	}

	/**
	 * SampleSystemException が発生すると、このメソッドが実行される。 <br>
	 * そして、エラーコード500でメッセージを返す。
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SampleSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleSystemException(SampleSystemException e) {
		log.info("Catched SampleSystemException at exception handler, and will return it as a HOSHIKI-ERROR.");
		return makeMessageBody("HOSHIKI-ERROR", "SampleSystemException occurred... (T_T)");
	}

	private Map<String, Object> makeMessageBody(String code, String message) {
		final String codeKey = "code";
		final String messageKey = "message";
		final String timeKey = "time";

		Map<String, Object> responseBody = new HashMap<String, Object>();
		responseBody.put(codeKey, code);
		responseBody.put(messageKey, message);
		responseBody.put(timeKey, LocalDateTime.now().toString());

		return responseBody;
	}

}
