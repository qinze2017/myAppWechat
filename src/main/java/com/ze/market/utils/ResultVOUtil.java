package com.ze.market.utils;

import com.ze.market.VO.ResultVO;

/**
 * @program: market
 * @description: result print
 * @author: Ze QIN
 * @create: 2020-05-23 16:51
 **/
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);

        return resultVO;
    }
}
