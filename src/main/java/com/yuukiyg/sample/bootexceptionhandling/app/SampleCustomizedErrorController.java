package com.yuukiyg.sample.bootexceptionhandling.app;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 拾いきれなかった例外をすべて拾う最後の砦。<br>
 * @author yagiyuk
 *
 */
@RestController
@RequestMapping("/error") // エラーページへのマッピング
public class SampleCustomizedErrorController implements ErrorController {

	/**
	 *
	 * 例外が発生して、それを誰も拾わずに DispatcherServlet まで到達した場合、<br>
	 * SpringBootでは、DispatcherServlet が /error に転送してくれる仕様しなっている。<br>
	 * そして、/error にリクエストが来た場合の動作を、このメソッドで定義する。<br>
	 * @return
	 */
	@RequestMapping
    public ResponseEntity<String> handleError() {
    	// 一律、501を返すようにする。（とりあえずの実装）
        return new ResponseEntity<String>("Unhandled error occurred... (>_<)", HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
