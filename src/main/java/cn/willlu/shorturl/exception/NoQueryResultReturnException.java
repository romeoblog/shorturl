package cn.willlu.shorturl.exception;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
public class NoQueryResultReturnException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public NoQueryResultReturnException(Throwable e) {
        super(e.getMessage(), e);
    }

    public NoQueryResultReturnException(String message) {
        super(message);
    }
}