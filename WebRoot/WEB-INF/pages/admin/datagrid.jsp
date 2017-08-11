<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complex DataGrid - jQuery EasyUI Demo</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/demo.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script>

	//用于控制进入Add方法，还是update方法。
	var flag;

	//用于生产表格
	$(function() {
		$('#test').datagrid({
			title : 'My DataGrid',
			iconCls : 'icon-save',
			width : '100%',
			height : 450,
			nowrap : false,
			striped : true,
			collapsible : true,
			url : 'userAction_queryAll',
			sortName : 'code',
			sortOrder : 'desc',
			remoteSort : false,
			idField : 'id',
			frozenColumns : [ [
				{
					field : 'ck',
					checkbox : true
				},
				{
					title : 'code',
					field : 'id',
					width : 80,
					hidden : true,
					sortable : true
				}
			] ],
			columns : [ [
				{
					title : 'Base Information',
					colspan : 3
				},
				{
					field : 'id',
					title : 'Operation',
					width : 100,
					align : 'center',
					rowspan : 2,
					formatter : function(value, rec) {
						return "<span style='color:red'>"
							+ "<a onclick='javascript:editUser(\"" + value + "\");return false;' href='#'>edit</a>" + "    "
							+ "<a onclick='javascript:deleteUser(\"" + value + "\");return false;' href='#'>delete</a>"
						'</span>';
					}
				}
			], [
				{
					field : 'username',
					title : '姓名',
					width : 120
				},
				/* {field:'salary',title:'工资',width:220,rowspan:2,sortable:true,
					sorter:function(a,b){
						return (a>b?1:-1);
					}
				} ,*/
				{
					field : 'formateBirthday',
					title : '生日',
					width : 150,
					rowspan : 2
				},
				{
					field : 'sex',
					title : '性别',
					width : 150,
					rowspan : 2
				}
			] ],
			pagination : true,
			rownumbers : true,
			//取消点击行选中
			onClickRow : function(rowIndex, rowData) {
				$(this).datagrid('unselectRow', rowIndex);
				return false;
			},
			toolbar : [ {
				id : 'btnadd',
				text : 'Add',
				iconCls : 'icon-add',
				handler : function() {
					flag = "增加";
					formClear();
					$('#dd').dialog('open');
				}
			},  {
				id : 'btncut',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var ids = getSelections();
					$("#deleteId").val(ids);
					$("#deleteConfirm").dialog("open");
				}
			}, '-', {
				id : 'btnsave',
				text : 'Save',
				disabled : true,
				iconCls : 'icon-save',
				handler : function() {
					$('#btnsave').linkbutton('disable');
					alert('save')
				}
			} ]
		});
		var p = $('#test').datagrid('getPager');
		$(p).pagination({
			onBeforeRefresh : function() {
				alert('before refresh');
			}
		});
	});


	//弹出框的操作
	$(function() {
		//增加及修改的弹框
		$('#dd').dialog({
			buttons : [ {
				text : 'Ok',
				iconCls : 'icon-ok',
				handler : function() {
					$.ajax({
						url : flag == 'add' ? "userAction_add" : "userAction_update",
						type : "post",
						data : $("#ff").serialize(),
						success : function(data) {
							alert("操作成功！");
							$("#test").datagrid("reload", { });
							$("#dd").dialog("close");
							formClear();
						},
						error : function() {
							alert("操作失败！");
							formClear();
							$("#dd").dialog("close");
						}
					});
				}
			}, {
				text : 'Cancel',
				handler : function() {
					$('#dd').dialog('close');
					formClear();
				}
			} ]
		});
		$('#dd').dialog('close');
		//确认删除的弹框
		$('#deleteConfirm').dialog({
			buttons : [ {
				text : 'Ok',
				iconCls : 'icon-ok',
				handler : function() {
					$.ajax({
						url : "userAction_deleteUser",
						type : "post",
						data : "ids=" + $("#deleteId").val() + "",
						success : function(data) {
							$("#test").datagrid("reload", { });
							$("#deleteConfirm").dialog("close");
							$.messager.show({
								title : "提示信息",
								msg : '删除成功',
								timeout : 5000
							});
						},
						error : function() {
							alert("操作失败！");
							$("#deleteConfirm").dialog("close");
						}
					});
				}
			}, {
				text : 'Cancel',
				handler : function() {
					$('#deleteConfirm').dialog('close');
				}
			} ]
		});
		$('#deleteConfirm').dialog('close');
	});
	//编辑User的Ajax
	function editUser(id) {
		formClear();
		$.ajax({
			url : 'userAction_queryById',
			type : 'post',
			data : 'id=' + id,
			dataType : 'json',
			success : function(model) {
				falg = "update";
				for (var item in model) {
					if (item != 'sex' && item != 'birthday')
						$("input[name='" + item + "']").val(model[item]);
				}
				$("input[name='birthday']").val(model['formateBirthday'])
				$("input#sex" + model['sex']).attr("checked", 'checked');
				$("textarea[name='msg']").val(model['msg']);
				$('#dd').dialog('open');
			},
			error : function() {
				alert(id);
			}
		});
	}
	//删除用户
	function deleteUser(id) {
		$("#deleteId").val(id);
		$('#deleteConfirm').dialog('open');
	}
	//清除form 的信息，防止再添加信息时会错误添加用户
	function formClear() {
		$(':input', '#ff')
			.not(':button, :submit, :reset ,:radio')
			.val(null);
		$(':input', '#ff').removeAttr('checked');
	}
	function resize() {
		$('#test').datagrid('resize', {
			width : 700,
			height : 400
		});
	}
	function getSelected() {
		var selected = $('#test').datagrid('getSelected');
		if (selected) {
			alert(selected.code + ":" + selected.name + ":" + selected.addr + ":" + selected.col4);
		}
	}
	function getSelections() {
		var ids = [];
		var rows = $('#test').datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids.join(':');
	}
	function clearSelections() {
		$('#test').datagrid('clearSelections');
	}
	function selectRow() {
		$('#test').datagrid('selectRow', 2);
	}
	function selectRecord() {
		$('#test').datagrid('selectRecord', '002');
	}
	function unselectRow() {
		$('#test').datagrid('unselectRow', 2);
	}
	function mergeCells() {
		$('#test').datagrid('mergeCells', {
			index : 2,
			field : 'addr',
			rowspan : 2,
			colspan : 2
		});
	}
</script>
</head>
<body>


	<table id="test"></table>


	<!--弹出框-->
	<div id="dd" icon="icon-save"
		style="padding:5px;width:400px;height:400px;">
		<div style="background:#fafafa;padding:10px;width:300px;height:300px;">
			<form id="ff" method="post" novalidate>
				<div>
					<input type="hidden" name="id" /> <label for="name">Name:</label>
					<br> <input class="easyui-validatebox" type="text"
						name="username" required="true"></input>
				</div>
				<div>
					<label for="email">Birthday:</label> <br> <input
						class="easyui-validatebox" type="text" name="birthday"></input>
				</div>
				<div>
					<label for="subject">password:</label> <br> <input
						class="easyui-validatebox" required="true" validType="password"
						type="password" name="password"></input>
				</div>
				<div>
					<label for="redio">sex:</label> <br>
					<s:radio list="#{'男':'男','女':'女'}" name="sex"></s:radio>
				</div>
				<div>
					<label for="salary">salary:</label> <br> <input type="number"
						name="salary"></input>
				</div>
				<div>
					<label for="telphone">telphone:</label> <br> <input
						type="number" name="telphone"
						oninput="if(value.length>11)value=value.slice(0,11)"></input>
				</div>
				<div>
					<label for="company">company:</label> <br> <input type="text"
						name="company"></input>
				</div>
				<div>
					<label for="message">Message:</label><br>
					<textarea name="msg" style="height:60px;"></textarea>
				</div>
			</form>
		</div>
	</div>
	<!-- 删除确认框 -->
	<div id="deleteConfirm" icon="icon-save"
		style="padding:5px;width:400px;height:200px;">
		<input ids="deleteId" name="id" type="hidden" />
		<p>确认删除用户？</p>
	</div>
</body>



</html>