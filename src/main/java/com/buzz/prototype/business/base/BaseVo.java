package com.buzz.prototype.business.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/***********************************************************************************************************************
 * VO 에서 공통적으로 포함하는 주요 필드 기술
 ***********************************************************************************************************************/
@Data
@AllArgsConstructor
public class BaseVo {
	private String loginId = null;
	private String authKey = null;
}
