package com.kirck.commen.exception;

public class BaseException extends AbstractNestedRuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8458544317507845657L;

    /**
     * 鍚勭郴缁熷唴閮ㄧ敤鐨勫紓甯竎ode
     */
    private String code;

    /**
     * 鐢ㄤ簬椤甸潰鏄剧ず鐨勫弸濂藉紓甯告彁绀轰俊鎭�
     */
    private String friendlyMessage = "";

    /**
     * code瀵瑰簲娑堟伅鐨勫弬鏁板璞℃暟缁�
     */
    private Object[] messageArgs;

    /**
     * 缂虹渷鍙嬪ソ寮傚父鎻愮ず淇℃伅
     */
    private String defaultFriendlyMessage;


    /**
     * 鏋勯�犳柟娉�
     */
    public BaseException() {

    }

    /**
     * 鐢ㄦ寚瀹氱殑cause寮傚父鏋勯�犱竴涓柊鐨凚aseException
     * 
     * @param cause the exception cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * 鐢ㄦ寚瀹氱殑寮傚父鏃ュ織 message鏋勯�犱竴涓狟aseException
     * 
     * @param logMsg the detail message
     */
    public BaseException(String logMsg) {
        super(logMsg);
    }

    /**
     * 鐢ㄦ寚瀹歝ode鍜宑ause寮傚父鏋勯�犱竴涓狟aseException
     * 
     * @param code the exception code
     * @param cause the exception cause
     * 
     */
    public BaseException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    /**
     * 
     * 鐢ㄦ寚瀹氱殑code鍜宑ause鏋勯�犱竴涓狟aseException,骞舵寚瀹歝ode瀵瑰簲message鐨勫弬鏁板��
     * 
     * @param code the exception code
     * @param cause the root cause
     * @param messageArgs the argument array of the message corresponding to code
     */
    public BaseException(String code, Throwable cause, Object[] messageArgs) {
        super(cause);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
    }

    /**
     * 鐢ㄦ寚瀹歝ode鍜屽紓甯告棩蹇� message鏋勯�犱竴涓狟aseException
     * 
     * @param code the exception code
     * @param logMsg the log message
     * 
     */
    public BaseException(String code, String logMsg) {
        super(logMsg);
        this.code = code;
    }

    /**
     * 鐢ㄦ寚瀹氱殑code鍜屽紓甯告棩蹇� message鏋勯�犱竴涓狟aseException,骞舵寚瀹歝ode瀵瑰簲message鐨勫弬鏁板��
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param messageArgs the argument array of the message corresponding to code
     * 
     */
    public BaseException(String code, String logMsg, Object[] messageArgs) {
        super(logMsg);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
    }

    /**
     * 鐢ㄦ寚瀹歝ode鍜屽紓甯告棩蹇� message浠ュ強寮傚父cause鏋勯�犱竴涓狟aseException,
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param cause the root cause
     */
    public BaseException(String code, String logMsg, Throwable cause) {
        super(logMsg, cause);
        this.code = code;
    }

    /**
     * 鐢ㄦ寚瀹氱殑寮傚父code鍜屽紓甯告棩蹇� message浠ュ強寮傚父cause鏋勯�犱竴涓狟aseException, 骞朵紶閫抍ode瀵瑰簲message鐨勫弬鏁板��
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param cause the root cause
     * @param messageArgs the argument array of the message corresponding to code
     */
    public BaseException(String code, String logMsg, Throwable cause, Object[] messageArgs) {
        super(logMsg, cause);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
    }

    /**
     * 鐢ㄦ寚瀹氱殑code鍜屽紓甯竎ause浠ュ強缂虹渷鐨勯〉闈㈠弸濂絤essage鏋勯�犱竴涓狟aseException
     * 
     * @param code the exception code
     * @param cause the root cause
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     */
    public BaseException(String code, Throwable cause, String defaultFriendlyMessage) {
        super(cause);
        this.code = code;
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 鐢ㄦ寚瀹氱殑code鍜屽紓甯竎ause鏋勯�犱竴涓狟aseException,浼犻�抍ode瀵瑰簲message鐨勫弬鏁板�硷紝 骞舵寚瀹氱己鐪佺殑椤甸潰鍙嬪ソmessage
     * 
     * @param code the exception code
     * @param cause the root cause
     * @param messageArgs the argument array of the message corresponding to code
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     * 
     */
    public BaseException(String code, Throwable cause, Object[] messageArgs, String defaultFriendlyMessage) {
        super(cause);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 鐢ㄦ寚瀹氱殑code鍜屽紓甯告棩蹇梞essage鏋勯�犱竴涓狟aseException,骞舵寚瀹氱己鐪佺殑椤甸潰鍙嬪ソmessage
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     * 
     */
    public BaseException(String code, String logMsg, String defaultFriendlyMessage) {
        super(logMsg);
        this.code = code;
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 鐢ㄦ寚瀹氬紓甯竎ode鍜屽紓甯告棩蹇梞essage鏋勯�犱竴涓狟aseException,浼犻�抍ode瀵瑰簲message鐨勫弬鏁板��, 骞舵寚瀹氱己鐪佺殑椤甸潰鍙嬪ソmessage
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param messageArgs the argument array of the message corresponding to code
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     * 
     */
    public BaseException(String code, String logMsg, Object[] messageArgs, String defaultFriendlyMessage) {
        super(logMsg);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 鐢ㄦ寚瀹氱殑code鍜屽紓甯告棩蹇梞essage浠ュ強寮傚父cause鏋勯�犱竴涓狟aseException, 骞舵寚瀹氱己鐪佺殑椤甸潰鍙嬪ソmessage
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param cause the root cause
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     * 
     */
    public BaseException(String code, String logMsg, Throwable cause, String defaultFriendlyMessage) {
        super(logMsg, cause);
        this.code = code;
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 鐢ㄦ寚瀹氬紓甯竎ode鍜屽紓甯告棩蹇梞essage浠ュ強寮傚父cause鏋勯�犱竴涓狟aseException,浼犻�抍ode瀵瑰簲message鐨勫弬鏁板��, 骞舵寚瀹氱己鐪佺殑椤甸潰鍙嬪ソmessage
     * 
     * @param code the exception code
     * @param logMsg the detail message
     * @param cause the root cause
     * @param messageArgs the argument array of the message corresponding to code
     * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
     *            exist.
     * 
     */
    public BaseException(String code, String logMsg, Throwable cause, Object[] messageArgs,
            String defaultFriendlyMessage) {
        super(logMsg, cause);
        this.code = code;
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * Return the detail message, including the message from the nested exception if there is one.
     */
    /**
     * @Override public String getMessage() { if(code!=null && code.trim().length()>0){ StringBuilder sb = new
     * StringBuilder(); sb.append("Code: ").append(code) .append("\rMessage: ").append(super.getMessage()); return
     * sb.toString(); } return super.getMessage(); }
     */

    /**
     * 杩斿洖friendlyMessage
     * 
     * @return the friendlyMessage
     */
    public String getFriendlyMessage() {
        return friendlyMessage;
    }

    /**
     * 璁剧疆鎻愮ず淇℃伅
     * 
     * @param friendlyMessage the friendlyMessage to set
     */
    public void setFriendlyMessage(String friendlyMessage) {
        this.friendlyMessage = friendlyMessage;
    }

    /**
     * 杩斿洖寮傚父淇℃伅鍙傛暟
     * 
     * @return the messageArgs
     */
    public Object[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * 璁剧疆寮傚父淇℃伅鍙傛暟
     * 
     * @param messageArgs the messageArgs to set
     */
    public void setMessageArgs(Object[] messageArgs) {
        if (null != messageArgs) {
            this.messageArgs = messageArgs.clone();
        }
    }

    /**
     * 杩斿洖榛樿鐨勬彁绀轰俊鎭�
     * 
     * @return the formattedMessage
     */
    public String getDefaultFriendlyMessage() {
        return defaultFriendlyMessage;
    }

    /**
     * 璁剧疆榛樿鐨勬彁绀轰俊鎭�
     * 
     * @param defaultFriendlyMessage the defaultFriendlyMessage to set
     */
    public void setDefaultFriendlyMessage(String defaultFriendlyMessage) {
        this.defaultFriendlyMessage = defaultFriendlyMessage;
    }

    /**
     * 杩斿洖绯荤粺鍐呴儴閿欒缂栫爜
     * 
     * @return 绯荤粺鍐呴儴閿欒缂栫爜
     */
    public String getCode() {
        return code;
    }

    /**
     * 璁剧疆绯荤粺鍐呴儴閿欒缂栫爜
     * 
     * @param code 绯荤粺鍐呴儴閿欒缂栫爜
     */
    public void setCode(String code) {
        this.code = code;
    }
}