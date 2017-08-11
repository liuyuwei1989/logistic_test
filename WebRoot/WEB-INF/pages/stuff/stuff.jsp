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
			title : '员工表',
			iconCls : 'icon-save',
			width : '100%',
			height : 450,
			nowrap : false,
			striped : true,
			collapsible : true,
			url : 'staffAction_queryAll',
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
					colspan : 6
				},
				{
					field : 'id',
					title : 'Operation',
					width : 100,
					align : 'center',
					rowspan : 2,
					formatter : function(value, rec) {
						return "<span style='color:red'>"
							+ "<a onclick='javascript:editStuff(\"" + value + "\");return false;' href='#'>edit</a>" + "    "
							+ "<a onclick='javascript:deleteStuff(\"" + value + "\");return false;' href='#'>delete</a>"
						'</span>';
					}
				}
			], [
				{
					field : 'name',
					title : '姓名',
					width : 150
				},
				/* {field:'salary',title:'工资',width:220,rowspan:2,sortable:true,
					sorter:function(a,b){
						return (a>b?1:-1);
					}
				} ,*/
				{
					field : 'telephone',
					title : '电话',
					width : 150,
					rowspan : 2
				},
				{
					field : 'haspda',
					title : 'PDA',
					width : 150,
					rowspan : 2,
					formatter:function(value,rec){
						return value==1?"有":"无";
					}
				},
				{
					field : 'deltag',
					title : '作废标记',
					width : 150,
					rowspan : 2,
					formatter:function(value,rec){
						return value==1?"有效":"作废";
					}
				},
				{
					field : 'station',
					title : '位置',
					width : 150,
					rowspan : 2
				},
				{
					field : 'standard',
					title : '派送标准',
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
					flag = "add";
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
						url : flag == 'add' ? "staffAction_add" : "staffAction_update",
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
						url : "staffAction_deleteStaff",
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
	function editStuff(id) {
		formClear();
		$.ajax({
			url : 'staffAction_queryById',
			type : 'post',
			data : 'id=' + id,
			dataType : 'json',
			success : function(model) {
				flag = "update";
				for (var item in model) {
					if (item != 'haspda' && item != 'deltag')
						$("input[name='" + item + "']").val(model[item]);
				}
				$("input#haspda" + model['haspda']).attr("checked", 'checked');
				$("input#deltag" + model['deltag']).attr("checked", 'checked');
				$('#dd').dialog('open');
			},
			error : function() {
				alert(id);
			}
		});
	}
	//删除用户
	function deleteStuff(id) {
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
					<input type="hidden" name="id" /> <label for="subject">姓名:</label>
					<br> <input class="easyui-validatebox" type="text"
						name="name" required="true"></input>
				</div>
				<div>
					<label for="subject">电话:</label> <br> <input
						class="easyui-validatebox" type="text" name="telephone"></input>
				</div>
				<div>
					<label for="subject">PDA:</label> <br> 
					<s:radio list="#{0:'无',1:'有'}" name="haspda"></s:radio>
				</div>
				<div>
					<label for="subject">作废标记:</label> <br>
					<s:radio list="#{0:'作废',1:'正常'}" name="deltag"></s:radio>
				</div>
				<div>
					<label for="subject">位置:</label> <br> <input 
						name="station"></input>
				</div>
				<div>
					<label for="subject">派送标准:</label> <br> <input
						 name="telphone"></input>
				</div>
			</form>
		</div>
	</div>
	<!-- 删除确认框 -->
	<div id="deleteConfirm" icon="icon-save"
		style="padding:5px;width:400px;height:200px;">
		<input id="deleteId" type="hidden" />
		<p>确认删除用户？</p>
	</div>
</body>



</html>