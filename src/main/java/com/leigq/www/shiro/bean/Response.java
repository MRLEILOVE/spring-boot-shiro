package com.leigq.www.shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 响应对象。包含处理结果（Meta）和返回数据（Data）两部分，在 Controller 处理完请求后将此对象转换成 json 返回给前台。注意：
 * <ul>
 * <li>处理成功一般返回处理结果和返回数据，失败只返回处理结果。具体返回什么需看接口文档。</li>
 * <li>处理成功结果码一般是200，失败码具体看出了什么错，对照 HTTP 响应码填。</li>
 * <li>默认处理方法慎用，前台最想要拿到的还是具体的结果码和信息。</li>
 * </ul>
 * <p>
 * @author ：leigq <br>
 * 创建时间：2017年10月9日 下午3:26:17 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Component
@Scope("prototype")
@SuppressWarnings(value = "all")
public class Response {

    /**
     * 默认成功响应码
     */
    private static final Integer DEAFAULT_SUCCESS_CODE = HttpStatus.OK.value();
    /**
     * 默认成功响应信息
     */
    private static final String DEAFAULT_SUCCESS_MSG = "请求/处理成功！";
    /**
     * 默认失败响应码
     */
    private static final Integer DEAFAULT_FAILURE_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
    /**
     * 默认失败响应信息
     */
    private static final String DEAFAULT_FAILURE_MSG = "请求/处理失败！";

    @Getter
    private Meta meta;

    @Getter
    private Object data;


    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓成功↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:25 <br>
     */
    public Response success(String msg) {
        this.meta = new Meta(DEAFAULT_SUCCESS_CODE, msg);
        this.data = null;
        return this;
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:25 <br>
     */
    public Response success(Object data) {
        this.meta = new Meta(DEAFAULT_SUCCESS_CODE, DEAFAULT_SUCCESS_MSG);
        this.data = data;
        return this;
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:25 <br>
     */
    public Response success(String msg, Object data) {
        this.meta = new Meta(DEAFAULT_SUCCESS_CODE, msg);
        this.data = data;
        return this;
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param httpStatus HTTP 响应码
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:25 <br>
     */
    public Response success(HttpStatus httpStatus, String msg, Object data) {
        this.meta = new Meta(httpStatus.value(), msg);
        this.data = data;
        return this;
    }


    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓失败↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param msg 处理结果信息
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:22 <br>
     */
    public Response failure(String msg) {
        this.meta = new Meta(DEAFAULT_FAILURE_CODE, msg);
        this.data = null;
        return this;
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:22 <br>
     */
    public Response failure(Object data) {
        this.meta = new Meta(DEAFAULT_FAILURE_CODE, DEAFAULT_FAILURE_MSG);
        this.data = data;
        return this;
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:22 <br>
     */
    public Response failure(String msg, Object data) {
        this.meta = new Meta(DEAFAULT_FAILURE_CODE, msg);
        this.data = data;
        return this;
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param httpStatus HTTP 响应码
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author ：LeiGQ <br>
     * @date ：2019-05-20 15:22 <br>
     */
    public Response failure(HttpStatus httpStatus, String msg, Object data) {
        this.meta = new Meta(httpStatus.value(), msg);
        this.data = data;
        return this;
    }

    /**
     * 元数据，包含响应码和信息。
     * <p>
     * 创建人：袁炜林 <br>
     * 创建时间：2017年10月9日 下午3:31:17 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     */
    @Data
    @AllArgsConstructor
    private class Meta {

        /**
         * 处理结果代码，与 HTTP 状态响应码对应
         */
        private Integer code;

        /**
         * 处理结果信息
         */
        private String msg;
    }

}
