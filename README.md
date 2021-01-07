# spring-boot-exception-handling
sample code for exception handling.

## 動作確認方法
1. アプリを起動する。ポート番号8081 で起動するようになっている。
2. http://your_ip_address:8081/test-exception にGETリクエストを投げると、アプリ内部でArithmeticExceptionが発生する。
  - このException をハンドリングする人がいないので、最終的に DispatcherServlet が /errorにリクエストを転送する。
  - /error にリクエストが来ると、SampleCustomizedErrorController で例外ハンドリングが行われ、501エラーが返ってくるはず。
3. http://your_ip_address:8081/error400 にGETリクエストを投げると、アプリ内部でSampleBusinessException が発生する。
  - このExceptionは SampleGlobalExceptionHandler で例外ハンドリングが行われ、400エラーが返ってくるはず。
4. http://your_ip_address:8081/error500 にGETリクエストを投げると、アプリ内部でSampleSystemException が発生する。
  - このExceptionは SampleGlobalExceptionHandler で例外ハンドリングが行われ、500エラーが返ってくるはず。
5. 存在しないパス、たとえば http://your_ip_address:8081/aaaaa にGETリクエストを投げると、リクエストをハンドリングする先が存在しないため、DispatcherServlet が /errorにリクエストを転送する。
  - /error にリクエストが来ると、SampleCustomizedErrorController で例外ハンドリングが行われ、501エラーが返ってくるはず。

## ソースの説明
### 独自の例外クラス
独自の例外クラスを2つ作った。
* com.yuukiyg.sample.bootexceptionhandling.exception.SampleBusinessException
* com.yuukiyg.sample.bootexceptionhandling.exception.SampleSystemException

### 独自の例外クラスをハンドリングする例外ハンドラ
*  com.yuukiyg.sample.bootexceptionhandling.app.SampleGlobalExceptionHandler

### 誰もハンドリングしていない例外をすべてハンドリングする例外ハンドラ
* com.yuukiyg.sample.bootexceptionhandling.app.SampleCustomizedErrorController
