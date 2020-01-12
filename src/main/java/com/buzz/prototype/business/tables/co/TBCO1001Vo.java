package com.buzz.prototype.business.tables.co;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//매퍼 xml 에서 사용할 별칭 지정
@Alias("TBCO1001Vo")
@Builder
@Getter
@Setter
public class TBCO1001Vo {
	private int seq;
	private int mnuId;
	private String mnuName;
	private int mnuOrd;
	
}
