//打印自动分页JS
AutoPage={
	header : null,//页面顶部显示的信息
	content: null,//页面正文部分
	footer : null,//页面底部
	pageSize : 10,//每页显示记录数
	pageBreak: null,//分页class属性
	pageNumClass : null,//分页样式
	totalRecord:0,//总记录数
	currentPage:1,//当前页
	totalPage : 0,//总页数
	init : function(header,content,footer,pageBreak,pageNumClass,pageSize){
		AutoPage.header = header;
		AutoPage.content = content;
		AutoPage.footer = footer;
		AutoPage.pageSize = pageSize;
		AutoPage.pageBreak = pageBreak;
		AutoPage.pageNumClass = pageNumClass;
		//初始化分页信息
		AutoPage.initPageInfo();
		//隐藏原来的数据
		AutoPage.hidenContent();
		//开始分页
		AutoPage.beginPage();
		
	},
	//初始化页面信息
	initPageInfo:function(){
		AutoPage.totalRecord = $("#"+AutoPage.content).find("tbody tr").length;//初始化总记录数
		AutoPage.totalPage =  Math.ceil(AutoPage.totalRecord/AutoPage.pageSize);//初始化总页数
		AutoPage.currentPage = 1;
	},
	//隐藏原来的数据
	hidenContent:function(){
		$("#"+AutoPage.header).hide();
		$("#"+AutoPage.content).hide();
		$("#"+AutoPage.footer).hide();
	},
	//开始分页
	beginPage: function(){
		AutoPage.$header = $("#"+AutoPage.header).clone();
		AutoPage.$content = $("#"+AutoPage.content).clone().find("tbody").remove().end();
		AutoPage.$footer = $("#"+AutoPage.footer).clone();
		var startLine = 1;//开始行号
		var offsetLine = 0;//偏移行号
		for(var i = AutoPage.totalPage; i >= 1 ;i-- ){
			AutoPage.currentPage = i;
			startLine = AutoPage.pageSize* (AutoPage.currentPage - 1);
			offsetLine = (startLine + AutoPage.pageSize) >AutoPage.totalRecord ? AutoPage.totalRecord :startLine + AutoPage.pageSize;
			AutoPage.createPage(startLine,offsetLine);
		};
	},
    //创建新的一页
	createPage:function(startLine,offsetLine){
		var $pageHeader = AutoPage.$header.clone().show();
		var $pageContent = AutoPage.$content.clone().append(AutoPage.getTrRecord(startLine,offsetLine)).show();
		var $pageFooter = AutoPage.$footer.clone().show();
		$pageFooter.find("."+AutoPage.pageNumClass).text("第 "+AutoPage.currentPage+" 页       共 "+AutoPage.totalPage+" 页");//页码显示格式
		if(offsetLine == AutoPage.totalRecord){
			$("#"+AutoPage.content).after($pageFooter).after($pageContent).after($pageHeader);
		}else
			$("#"+AutoPage.content).after(AutoPage.addPageBreak()).after($pageFooter).after($pageContent).after($pageHeader);
	},
	//添加分页符
	addPageBreak: function(){
		return "<div class='"+AutoPage.pageBreak+"'></div>";
	},
	//获取记录
	getTrRecord:function(startLine,offsetLine){
		var trStr ="";
		$("#"+AutoPage.content).find("tbody tr").slice(startLine,offsetLine).each(function(i){
			trStr += "<tr>"+$(this).html()+"</tr>";
		});
		return trStr;
	}
};
$(function(){
	/**
	* headerInfo 头部信息ID
	* tabContent 表格内容ID
	* footerInfo 底部信息ID
	* pageBreak 分页样式 class
	*　pageNum　页码样式　class
	×　10　每页显示多少条记录
	*/
	AutoPage.init("headerInfo", "tabContent", "footerInfo","pageBreak","pageNum",10);
});
