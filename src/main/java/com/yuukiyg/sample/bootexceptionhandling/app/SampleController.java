package com.yuukiyg.sample.bootexceptionhandling.app;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuukiyg.sample.bootexceptionhandling.exception.SampleBusinessException;
import com.yuukiyg.sample.bootexceptionhandling.exception.SampleSystemException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {

	private static final String ENDPOINT1 = "/test-exception";
	private static final String ENDPOINT2 = "/error400";
	private static final String ENDPOINT3 = "/error500";

	/**
	 * 例外をわざと発生させるエンドポイント
	 * @return
	 */
	@RequestMapping(value = ENDPOINT1, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResult produceException() {
		log.info("\""+ ENDPOINT1 + "\" called.");

		int num = 100 / 0; // ここで例外が発生するので、これより下のコードは実行されない。

		ControllerResult result = new ControllerResult(ENDPOINT1+" called.", LocalDateTime.now().toString());
		return result;
	}

	/**
	 * SampleBusinessExceptionをわざと発生させるエンドポイント<br>
	 * @return
	 */
	@RequestMapping(value = ENDPOINT2, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResult produce400() {
		log.info("\""+ ENDPOINT2 + "\" called.");

		throwSampleBusinessException(); // ここで例外が発生するので、これより下のコードは実行されない。

		ControllerResult result = new ControllerResult(ENDPOINT2+" called.", LocalDateTime.now().toString());
		return result;
	}

	/**
	 * SampleSystemExceptionをわざと発生させるエンドポイント<br>
	 * @return
	 */
	@RequestMapping(value = ENDPOINT3, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResult produce500() {
		log.info("\""+ ENDPOINT3 + "\" called.");

		throwSampleSystemException(); // ここで例外が発生するので、これより下のコードは実行されない。

		ControllerResult result = new ControllerResult(ENDPOINT3+" called.", LocalDateTime.now().toString());
		return result;
	}

	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResult sample() {
		log.info("\"/\" called.");
		final String message = "It works!";
		ControllerResult result = new ControllerResult(message, LocalDateTime.now().toString());
		return result;
	}

	private void throwSampleBusinessException() {
		throw new SampleBusinessException("わざと発生させた SampleBusinessException です！");
	}

	private void throwSampleSystemException() {
		throw new SampleSystemException("わざと発生させた SampleSystemException です！");
	}

	@Data
	@AllArgsConstructor
	private class ControllerResult{
		private String message;
		private String time;
	}

}
