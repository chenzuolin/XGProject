layui.use(['laydate', 'form', 'jquery', 'layer'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;

	//执行一个laydate实例
	laydate.render({
		elem: '#hetongBegin' //指定元素，开始时间
	});
	laydate.render({
		elem: '#hetongFinish' //指定元素，结束时间
	});

	//form监听提交,当单机【立即提交的时候调用】
	form.on('submit(formDemo)', function(data) {
		var obj = data.field; //获取到输入框中的对应的值
		//判断登录名是否为空
		if(obj.loginName == "" || obj.loginName == null || obj.loginName == undefined) {
			layer.alert("登录名不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断登录密码是否为空
		if(obj.loginPwd == "" || obj.loginPwd == null || obj.loginPwd == undefined) {
			layer.alert("登录密码不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		// 密码验证，有数字、字母下划线组成并且6-12位
		var reg_password = /^[a-zA-Z0-9_]{6,12}$/;
		if(reg_password.test(obj.loginPwd) == false) {
			layer.alert("密码输入有误，只能是字母数字下划线6-12位", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false
		}
		//判断登录密码和确认密码是否一致
		if(obj.querenPwd != obj.loginPwd) {
			layer.alert("登录密码和确认密码不一致！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//邮箱判断是否为空
		if(obj.email == "" || obj.email == null || obj.email == undefined) {
			layer.alert("邮箱不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//邮箱正则表达式验证
		var reg_mail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
		if(reg_mail.test(obj.email) == false) {
			layer.alert("邮箱输入有误！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断联系电话是否为空
		if(obj.tel == null || obj.tel == "" || obj.tel == undefined) {
			layer.alert("联系电话不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		// 手机号验证
		var reg_phone = /^1\d{10}$/;
		if(reg_phone.test(obj.tel) == false) {
			layer.alert("联系电话输入有误！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//负责人判断是否为空
		if(obj.fuzeren == "" || obj.fuzeren == null || obj.fuzeren == undefined) {
			layer.alert("负责人不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断合同起始日期是否为空
		if(obj.hetongBegin == "" || obj.hetongBegin == null || obj.hetongBegin == undefined) {
			layer.alert("合同起始日期不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断结束日期是否为空
		if(obj.hetongFinish == "" || obj.hetongFinish == null || obj.hetongFinish == undefined) {
			layer.alert("合同结束日期不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断单位名称是否为空
		if(obj.mingcheng == "" || obj.mingcheng == null || obj.mingcheng == undefined) {
			layer.alert("单位名称不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		//判断单位简称是否为空
		if(obj.jiancheng == "" || obj.jiancheng == null || obj.jiancheng == undefined) {
			layer.alert("单位简称不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//判断单位助记符是否为空
		if(obj.zhujifu == "" || obj.zhujifu == null || obj.zhujifu == undefined) {
			layer.alert("单位助记符不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//判断合同号是否为空
		if(obj.hetonghao == "" || obj.hetonghao == null || obj.hetonghao == undefined) {
			layer.alert("合同号不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//判断折扣是否为空
		if(obj.zhekou == "" || obj.zhekou == null || obj.zhekou == undefined) {
			layer.alert("客户折扣不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//判断折扣是否为空
		if(obj.xinyong == "" || obj.xinyong == null || obj.xinyong == undefined) {
			layer.alert("信用度不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		//判断单位地址是否为空
		if(obj.addres == "" || obj.addres == null || obj.addres == undefined) {
			layer.alert("单位地址不能为空！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}

		layer.confirm("确定注册吗？", {
			icon: 3,
			closeBtn: 2,
			anim: 4,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function(index) {
			//用ajax的方式提交注册
			$.ajax({
				type: "post",
				url: "/XGProject/client.do?flag=addClient",
				async: false,
				data: {
					"clientLoginName": obj.loginName,
					"clientPassword": obj.loginPwd,
					"clientEmail": obj.email,
					"clientTel": obj.tel,
					"clientHuman": obj.fuzeren,
					"clientFirmName": obj.mingcheng,
					"clientAbbreviation": obj.jiancheng,
					"clientSign": obj.zhujifu,
					"clientContract": obj.hetonghao,
					"clientStartTime": obj.hetongBegin,
					"clientFinishTime": obj.hetongFinish,
					"clientAgio": obj.zhekou,
					"clientAddress": obj.addres,
					"clientCredit":obj.xinyong
				},
				dataType: "text",
				success: function(data) {
					var ok = data.indexOf("注册成功");
					if(ok != -1) {
						layer.alert("注册成功！", {
							icon: 1,
							closeBtn: 2,
							anim: 4,
							title: ['系统提示', 'font-size:16px;'],
			                skin: 'layui-layer-blue',
						}, function(index) {
							document.location.href = "/XGProject/xxcc-sl/page/client/keHuXinXi.jsp";
							layer.close(index);
						});
					} else {
						if(data.indexOf("用户已存在") != -1) {
							layer.alert("该登录名已存在，请重新输入！", {
								icon: 5,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
				                skin: 'layui-layer-blue',
							});
						} else {
							layer.alert("注册失败！", {
								icon: 2,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
				                skin: 'layui-layer-blue',
							});
						}
					}
				},
				error: function() {
					layer.alert("数据上传错误，注册失败！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
				}
			});
		});
		return false;
	});
});