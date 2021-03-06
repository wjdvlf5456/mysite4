<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/includes/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
					<c:if test="${authUser!=null }">
						<button id="btnImgUpload" type="submit">이미지올리기</button>
					</c:if>

					<div class="clear"></div>


					<ul id="viewArea">
						<!-- 이미지반복영역 -->
						<li><c:forEach items="${imgList}" var="galleryVo">
								<div class="view" id="t" ${galleryVo.no}>
									<img class="imgItem" src="${pageContext.request.contextPath }/upload/${galleryVo.saveName}">
									<div class="imgWriter">
										작성자: <strong>${galleryVo.name}</strong>
									</div>
								</div>
							</c:forEach></li>
						<!-- 이미지반복영역 -->


					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form action="${pageContext.request.contextPath }/gallery/upload" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							 <input id="addModalContent" type="text" name="content" value="">
							  <input type="hidden" name="userNo" value="${authUser.no}">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="" name="saveName">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="${pageContext.request.contextPath }/gallery/delete" action="post">
				<input id="authUserNo" type="hidden" value="${authUser.no}">
				<input id="delNo" type="hidden" name="no">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>
<script type="text/javascript">
	/*=================== 업로드 창 띄우기 ===================*/
	$("#btnImgUpload").on("click", function() {
		console.log("이미지 업로드");
		$("#addModal").modal("show");
	});

	/*=================== 이미지보기 창 띄우기 ===================*/
	$("#viewArea").on("click", ".imgItem",function() {

					var $this = $(this);

					console.log($this);

					//모달창에 사진,content 띄우기
					var saveName = $this.attr("src");

					var save = saveName;

					var authUser = parseInt($("#authUserNo").val());

					console.log(saveName);
					console.log(save);

					var galleryVo = {
						saveName : save, 
						userNo :authUser
					};
					console.log(galleryVo);
					$.ajax({
								url : "${pageContext.request.contextPath}/gallery/getImageInfo",
								type : "post",
								contentType : "application/json",
								data : JSON.stringify(galleryVo),

								dataType : "json",
								success : function(gVo) {
									//성공시 출력할 코드
									console.log(gVo);
									console.log(gVo.sameUser);

									$('[name="no"]').val(gVo.no);
									if (gVo.sameUser == true) {
										$("#btnDel").show();
									} else if(gVo.sameUser == false){
										$("#btnDel").hide();
									}

									$('#viewModelContent').val(gVo.content);
									$('#viewModelImg').attr(
											"src",
											"${pageContext.request.contextPath}/upload/"
													+ gVo.saveName);

									//모달창 띄우기
									$("#viewModal").modal("show");

								},
								error : function(XHR, status, error) {
									console.error(status + " : " + error);
								}
							});
					// ajax 

				});

	/* 모달창 삭제버튼 클릭할 때 */
	$("#viewModal").on("click", "#btnDel", function() {
		console.log("모달창 삭제버튼 클릭");

		//데이터 모으기
		var $this = $(this);
		
		var no = $("#delNo").val();

		var galleryVo = {
			no : no
		};

		console.log(galleryVo);

		$.ajax({
			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(galleryVo),

			dataType : "json",
			success : function(result) {
				//성공시 출력할 코드
				console.log(result);

				if (result == "true") {
					$("#t" + no).remove();
					$("#viewModal").modal("hide");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		// ajax 

	});
</script>


</html>

