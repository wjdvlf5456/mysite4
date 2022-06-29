<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<%-- <form action="${pageContext.request.contextPath}/api/guestbook/add" method="get"> --%>
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name" value=""></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password" value=""></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button type="submit" id="btnSubmit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->

					<!-- </form> -->

					<button id="btnTest" class="btn btn-primary">모달창</button>

					<!-- 리스트 영역 -->
					<div id="listArea"></div>


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<!-- ======================================================================= -->
	<!-- 삭제모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- ======================================================================= -->

</body>
<script type="text/javascript">
<!-- 준비가 끝났을 때 -->
	$(document).ready(function() {
		console.log("jquery로 data만 받는 요청");
		fetchList()
	});

	$("#btnSubmit").on("click", function() {
		console.log("저장버튼 클릭");

		// 데이터 수집
		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name=content]").val();

		var guestVo = {
			name : name,
			password : password,
			content : content
		};

		$.ajax({
			//url : "${pageContext.request.contextPath}/api/guestbook/add?name="+ name + "&password="+ password +"&content="+content,
			url : "${pageContext.request.contextPath}/api/guestbook/add",
			type : "post",
			//contentType: "application/json",
			data : guestVo,

			dataType : "json",
			success : function(gVo) {
				render(gVo, "up");

				//입력폼 초기화
				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});

	});
	$("btnTest").on("click", function(){
		console.log("테스트버튼 클릭")
	});
	
	
	
	// 리스트 요청
	function fetchList() {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			//contentType: "application/json",
			//data : {name: ”홍길동"},

			dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);
				//화면에 data + html을 그린다.
				for (var i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		})

	}

	function render(guestbookVo, opt) {
		console.log("render()");

		var str = '';
		str += '<table class="guestRead">';
		str += '   <colgroup>';
		str += '      <col style="width: 10%;">';
		str += '      <col style="width: 40%;">';
		str += '      <col style="width: 40%;">';
		str += '      <col style="width: 10%;">';
		str += '   </colgroup>';
		str += '   <tr>';
		str += '      <td>' + guestbookVo.no + '</td>';
		str += '      <td>' + guestbookVo.name + '</td>';
		str += '      <td>' + guestbookVo.regDate + '</td>';
		str += '      <td><a href="">[삭제]</a></td>';
		str += '   </tr>';
		str += '   <tr>';
		str += '      <td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '   </tr>';
		str += '</table>';

		if (opt == "down") {
			$("#listArea").append(str);

		} else if (opt == "up") {
			$("#listArea").prepend(str);

		} else {
			console.log("opt오류");
		}

	}
</script>

</html>