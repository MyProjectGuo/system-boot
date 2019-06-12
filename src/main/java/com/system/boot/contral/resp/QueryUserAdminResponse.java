package com.system.boot.contral.resp;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import com.system.boot.model.User;
import com.system.boot.utils.PageInfo;

@Data
public class QueryUserAdminResponse implements Serializable {

	private static final long serialVersionUID = 3824425771141200893L;

	private List<User> list;

	private PageInfo page;

}
