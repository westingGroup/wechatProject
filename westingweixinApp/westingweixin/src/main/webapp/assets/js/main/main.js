var arryData = [{"menuId":10,"id":10,"type":0,"text":"注册","pic":"register.png","active":0},
                {"menuId":10,"id":101,"text":"注册信息","src":"/process/registers","active":0},
                
                {"menuId":11,"id":11,"type":0,"text":"需求","pic":"demander.png","active":0},
                {"menuId":11,"id":112,"text":"需求信息","src":"/process/demanders","active":0},
                ];
/**初始化菜单*/
function addMenu(){
	var t = "";
	for(var i = 0; i < arryData.length; i++){
		if(arryData[i].type == 0){
			t = t + "<div class='menu_head' id='menu_head"+arryData[i].id+"' onclick='javascript:changeMenuParent("+arryData[i].id+")'>";
			t = t + "	<table><tr>";
			t = t + "	<td width=20px style='padding-bottom: 10px;'><img class='arrowimg arrowimgright' id='arrowimgright"+arryData[i].id+
				"' src='"+basePath+"/assets/img/arrowright.png' height='10' width='10'>" +
				"<img class='arrowimg arrowimgdown' id='arrowimgdown"+arryData[i].id+
					"' src='"+basePath+"/assets/img/arrowdown.png' height='10' width='10'>"
						"</td>";
			t = t + "	<td width=40px><img src='"+basePath+"/assets/img/"+arryData[i].pic+"' height='30' width='30'></td>";
			t = t + "	<td nowrap>" ;
			t = t + "</td>";
			t = t + "	</tr></table>";
			t = t + "</div>";
			t = t + genMenuChild(arryData,arryData[i].id);
		}
	}
	
	$("#firstpane").append(t);
	$(".arrowimgdown").hide();
}

function genMenuChild(list,id){
	var t = "";
	t = t + "<div class='menu_body' id='menu_body"+id+"'>";
	for(var i = 0; i < list.length; i++){
		if(list[i].type != 0 && list[i].menuId == id){
			if(list[i].active==0){
				t = t + "    <div class='menu_body_a' id='menu_body_a"+list[i].id+
					"' onclick='javascript:changeMenuChild("+list[i].id+")'>　　　"+list[i].text+"</div>";
				
			}else{
				t = t + "    <div class='menu_body_a_disable' id='menu_body_a"+list[i].id+
				"' >　　　"+list[i].text+"</div>";
			}
		}
	}
	t = t + "</div>";
	return t;
}

function changeMenuParent(id){
    $("#menu_body"+id).slideToggle(500);
    
    $("#arrowimgright"+id).fadeToggle(800);
    $("#arrowimgdown"+id).fadeToggle(800);
}

/**更换主菜单时修改二级菜单*/
function changeMenu(menuId){
	$(".page_left").remove();
	$(".page_right").remove();
	$("#iframeMain").remove();
	$(".page_content").append("<div class=\"page_left\"><div class=\"menuLeft\" id=\"menuLeft\"></div></div><div class=\"page_right\" style=\"font-size: 0;\"><iframe frameborder=\"0\" height=100% width=100%  style=\"margin-right: -3px;\"  id=\"iframeMain\"></iframe></div>");
	$("#menuLeft").empty();
	var k = 0;
	for(var i = 0; i < arryData.length; i++){
		if(arryData[i].type == 0)
			$("#menuId"+arryData[i].menuId).removeClass("current");
		if(arryData[i].menuId != menuId || arryData[i].type == 0)
			continue;
		k++;
		$("#menuLeft").append("<a href='javascript:;' onclick='turnToPage(\""+arryData[i].src+"\","+arryData[i].id+")' id='leftMenu"+arryData[i].id+"'>" +
				"<div><img src='"+basePath+"/img/main/1.png'/>"+arryData[i].text+"</div></a>");
		if(k == 1)
			turnToPage(arryData[i].src, arryData[i].id);
	}
	$("#menuId"+menuId).addClass("current");
}

function changeMenuChild(menuId){
    $(".menu_body_a").removeClass("menu_head_select");
    $("#menu_body_a"+menuId).addClass("menu_head_select");
    var menusrc="";
    for(var i = 0; i < arryData.length; i++){
		if(arryData[i].id == menuId)
			menusrc = arryData[i].src;
	}
    if(menusrc==""){
    	$("#iframeMain").attr("src",basePath+"/building.htm");
    }else{
    	$("#iframeMain").attr("src",basePath+"/"+menusrc);
    }
}

/**转向主页面*/
function turnToPage(src,leftMenuId){
	var leftMenuArr = $("#menuLeft").children("a");
	for(var i = 0; i < leftMenuArr.length; i++){
		$(leftMenuArr.get(i)).removeClass("current");
	}
	$("#leftMenu"+leftMenuId).addClass("current");
	$("#iframeMain").attr("src",basePath+"/"+src);
}
function quit(){
	location.href = basePath + "/inside/logout";
}