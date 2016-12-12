/**
 * 初始化内部员工列表
 */
function initInsideProvider() {
	getInsideProvider(1);
	$("#insideProviderConfirmBtn").click(function() {
	});
}
/**
 * 初始化内部员工列表
 */
function getInsideProvider(currPage) {
	$
			.post(
					basePath + "/process/insideProviderList",
					{
						currentPage : parseInt(currPage) + 1,
					},
					function(data, status) {
						for (var i = 0; i < data.length; i++) {
							var record = "<tr>";
							record += "<td style='text-align:center;'><input type = 'radio' name='insideProviderId'></td>";
							record += "<td style='text-align:center;'>"
									+ data[i].username + "</td>";
							record += "<td style='text-align:center;'>"
									+ data[i].phone + "</td>";
							record += "</tr>";
							$("#insideProviderBody").append(record);
						}
					}, "json");
}