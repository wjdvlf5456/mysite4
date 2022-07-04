<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>
</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">

		<c:import url="/WEB-INF/views/includes/boardAside.jsp"></c:import>
		
			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="rboard">
					<div id="list">
						<form action="${pageContext.request.contextPath}/rboard/list" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						
						<div id="listArea"></div>
<%--						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
 							<c:forEach items="${rboardList}" var="rboardVo" varStatus="status">
							<tbody>
								<tr>
									<td>${status.count }</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/rboard/read/${rboardVo.no}">${rboardVo.title }</a></td>
									<td>${rboardVo.name}</td>
									<td>${rboardVo.hit }</td>
									<td>${rboardVo.regDate }</td>
									<c:if test="${authUser.no==rboardVo.userNo }">
									<td><a href="${pageContext.request.contextPath}/rboard/delete/${rboardVo.no}">[삭제]</a></td>
									</c:if>
								</tr>
							</tbody>
							</c:forEach> 
						</table>
							--%>

						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>


							<div class="clear"></div>
						</div>
						<c:if test="${authUser!=null }">
						<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm">글쓰기</a>
						</c:if>

					</div>
					<!-- //list -->
				</div>
				<!-- //rboard -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
$(document).ready(function() {
	console.log("jquery로 데이터 받기");
	fetchList();
	
});

// 리스트 요청
function fetchList() {
	$.ajax({
		url : "${pageContext.request.contextPath}/api/board/getlist",
		type : "post",
		//contentType: "application/json",
		//data : {name: ”홍길동"},

		dataType : "json",
		success : function(rboardList) {
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			//화면에 data + html을 그린다.
			for (var i = 0; i < rboardList.length; i++) {
				render(rboardList[i], "down");
			}

		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}

	})

}

function render() {
	
	
};



</script>

</html>

