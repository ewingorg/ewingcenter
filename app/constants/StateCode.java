package constants;

import apifw.enums.CommonCode;

/**
 * API状态码定义
 * 类/接口注释
 * 
 * @author wenxy@ucweb.com
 * @createDate 2014年8月28日
 *
 */
public class StateCode extends CommonCode{
	

	
	/**
	 * 请求参数不合法
	 */
	public static final int ILLEGAL_PARAM = 5000002;
	
	/**
	 * 消息内容不合法
	 */
	public static final int MESSAGE_CONTENT_FORBID = 5000020;
	
	
	/**
	 * ID不存在
	 */
	public static final int ID_NOT_EXIST = 5010001;
	
    /**
     * 数据已经存在
     */
    public static final int DATA_ALREADY_EXIST = 5020001;
    
    
    public static final int DATA_INSERT_FAIL = 5030001;
    
    /**
     * app不存在或者app状态为下线状态
     */
    public static final int APP_CONTENT_ILLEGAL = 5040001;
    
    
    /**
     * app的分类不对应
     */
    public static final int CATEGORY_CONTENT_ILLEGAL = 5050001;
    
    
    /**
     * appId重复
     */
    public static final int APPID_MULTIPLE = 5060001;
    
    
    public static final int EMPTY_DATA = 5070001;
    
    public static final int EMPTY_CALLER = 5080001;
    
}
