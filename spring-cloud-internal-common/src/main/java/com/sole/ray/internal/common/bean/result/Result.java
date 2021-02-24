/**  
 * @Title: Result.java
 * @Package com.globalvillage.bg.baseUtils
 * @Description: TODO
 * @author likeke
 * @date 2016年5月17日
 */
package com.sole.ray.internal.common.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Result 
 * @Description: TODO
 * @author SoleRay
 * @date 2017年07月14日
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class Result<T> extends AbstractResult{

	/** 具体结果数据 */
	private T data;

	public Result(String code, String message) {
		this(code,message,null);
	}
//
	public Result(String code, String message,T data) {
		super(code, message);
		this.data = data;
	}

	private Result(ResultCode resultCode) {
		this(resultCode,null);
	}

	private Result(ResultCode resultCode, T data) {
		super(resultCode);
		this.data = data;
	}

	public static Result success(){
		return new Result(ResultCode.SUCCESS);
	}

	public static Result success(Object data){
		return new Result(ResultCode.SUCCESS,data);
	}

	public static Result failure(String code,String msg){
		return new Result(code,msg);
	}

	public static Result failure(String code,String msg,Object data){
		return new Result(code,msg,data);
	}

	public static Result failure(ResultCode resultCode){
		return new Result(resultCode);
	}

	public static Result failure(ResultCode resultCode,Object data){
		return new Result(resultCode,data);
	}

}
