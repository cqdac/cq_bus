
$(function(){
	
	loaddataforresourcetable();
    
});

var oTableresource;
function loaddataforresourcetable(){
	//开始加载数据
    oTableresource = $('#sample_1').dataTable({
		"bServerSide": true,
//		"sAjaxDataProp" : "aData",
//		"bProcessing": true,
		"bRetrieve": true,
		"bFilter":false,
		"bAutoWidth":false,
		"sAjaxSource": 'getAllAdResourcesByUserId.action?userId='+$("#userid").val()+'&rolesId='+$("#roleId").val()+'&institutionId='+$("#institutionId").val(),
		"aaSorting": [[ 1, "desc" ]],
		"aoColumns" : [ 
						{
							  "sTitle": "代号",
							   "mDataProp" : "shwoIcon",
							   "sName": "shwoIcon",
							   "bSortable": false,
						}, {
		            	   "sTitle": "线路区间",
		            	   "mDataProp" : "name",
		            	   "sName": "name",
		            	   "bSortable": true,
		               }, {
		            	   "sTitle": "发车地点",
		            	   "mDataProp" : "resourceId",
		            	   "sName": "resourceId",
		            	   "bSortable": true,
		               }, {
		            	   "sTitle": "圈(上)1",
		            	   "mDataProp" : "type",
		            	   "sName": "type",
		            	   "bSortable": true,
		               },  {
		            	   "sTitle": "圈(下)1",
		            	   "mDataProp" : "userName",
		            	   "sName": "userName",
		            	   "bSortable": true,
		               }, {
		            	   "sTitle": "圈(上)2",
		            	   "mDataProp" : "institutionName",
		            	   "sName": "institutionName",
		            	   "bSortable": true,
		               }, {
		            	   "sTitle": "圈(下)2",
		            	   "mDataProp" : "size",
		            	   "sName": "size",
		            	   "bSortable": true
		               },{
		            	   "sTitle": "圈(上)3",
		            	   "mDataProp" : "active",
		            	   "sName": "active",
		            	   "bSortable": false
		               },{
		            	   "sTitle": "圈(下)3",
		            	   "mDataProp" : "modify",
		            	   "sName": "modify",
		            	   "bSortable": false
		               }, {
		            	   "sTitle": "圈(上)4",
		            	   "mDataProp" : "disable",
		            	   "sName": "disable",
		            	   "bSortable": false
		               },{
							"sTitle": "圈(下)4",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(上)5",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(下)5",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(上)6",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(下)6",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(上)7",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						},{
							"sTitle": "圈(下)7",
							"mDataProp" : "disable",
							"sName": "disable",
							"bSortable": false
						}
		               
		  ],
		"oLanguage": {
            "sProcessing": "正在加载中......",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "对不起，查询不到相关数据！",
            "sEmptyTable": "表中无数据存在！",
            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "sInfoEmpty" : "显示0到0条记录",
            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
            "sSearch": "搜索",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            }
		},
		"fnInitComplete": function(oSettings, json) {
		     
		 },
		 "fnStateLoad": function (oSettings) {
			 
		 },
		 "fnPreDrawCallback":function(){
				//开始加载数据
				//  var modelel  = $("#set_resourcereload").parents(".portlet");
				// App.blockUI(modelel);
		 },
		 "fnDrawCallback":function(data){
			 //加载完成
			//  var modelel  = $("#set_resourcereload").parents(".portlet");
			// App.unblockUI(modelel);
		 }
	});
	
    $("tfoot  input").keyup( function () {
        /* Filter on the column (the index) of this element */
    	oTableresource.fnFilter( this.value, $("tfoot  input").index(this) );
    } );
    
     
    /*
     * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
     * the footer
     */
    $("tfoot  input").each( function (i) {
        asInitVals[i] = this.value;
    } );
     
    $("tfoot  input").focus( function () {
        if ( this.className == "search_init" )
        {
            this.className = "";
            this.value = "";
        }
    } );
     
    $("tfoot  input").blur( function (i) {
        if ( this.value == "" )
        {
            this.className = "search_init";
            this.value = asInitVals[$("tfoot  input").index(this)];
        }
    } );
}