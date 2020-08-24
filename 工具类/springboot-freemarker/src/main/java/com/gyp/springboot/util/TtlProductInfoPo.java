package com.gyp.springboot.util;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author kundy
 * @create 2019/2/15 11:22 PM
 *
 */
@Data
@Accessors(chain = true)
public class TtlProductInfoPo {

    private Long id;
    private String productName;


}
