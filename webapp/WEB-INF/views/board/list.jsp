<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
		
		<c:import url="/WEB-INF/views/includes/boardAside.jsp"></c:import>
		<!-- //aside -->

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

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/board/list?keyword=${pMap.keyword}" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="${pMap.keyword}">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
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
							<c:forEach items="${pMap.boardList}" var="boardVo" varStatus="status">
							<tbody>
								<tr>
									<%-- <td>${status.count }</td> --%>
									<td>${boardVo.no}</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/read/${boardVo.no}">${boardVo.title }</a></td>
									<td>${boardVo.name}</td>
									<td>${boardVo.hit }</td>
									<td>${boardVo.regDate }</td>
									<c:if test="${authUser.no==boardVo.userNo }">
									<td><a href="${pageContext.request.contextPath}/board/delete/${boardVo.no}">[삭제]</a></td>
									</c:if>
								</tr>
							</tbody>
							</c:forEach>
						</table>

						<div id="paging">
							<ul>
								<c:if test="${pMap.prev }">
									<li><a href="${pageContext.request.contextPath}/board/list?crtPage=${pMap.startPageBtnNo-pMap.pageBtnCount}&keyword=${pMap.keyword}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
									
									<c:choose>
										<c:when test="${param.crtPage == page}">
										<li class="active"><a href="${pageContext.request.contextPath}/board/list?crtPage=${page}&keyword=${pMap.keyword}">${page}</a></li>
										</c:when>
										<c:otherwise >
										<li><a href="${pageContext.request.contextPath}/board/list?crtPage=${page}&keyword=${pMap.keyword}">${page}</a></li>
										</c:otherwise>
									</c:choose>
									<!-- choose -->
									
								</c:forEach>
								
								<c:if test="${pMap.next}">
									<li><a href="${pageContext.request.contextPath}/board/list?crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
								</c:if>
							</ul>
							
							<div class="clear"></div>
						</div>
						<c:if test="${authUser!=null }">
						<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
						</c:if>

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
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

</html>

