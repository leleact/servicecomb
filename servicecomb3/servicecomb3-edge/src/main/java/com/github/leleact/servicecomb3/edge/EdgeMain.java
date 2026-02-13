package com.github.leleact.servicecomb3.edge;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

/**
 * Edge main.
 *
 * @author leleact
 * @since 2026-02-13
 */
public class EdgeMain {
    public static void main(String[] args) throws Exception {
        Log4jUtils.init();
        BeanUtils.init();
    }
}
