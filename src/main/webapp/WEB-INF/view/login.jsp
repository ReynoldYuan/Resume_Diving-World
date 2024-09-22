<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${webName}-登入</title>

<jsp:include page="${component}/common_imports.jsp" />

<body class="vh-100">

	<main class="form-signin m-5 text-center ">
		<form action="${root}/login.do" method="post">
			<h1 class="h3 mb-3 fw-normal">會員登入</h1>
			<div class="form-floating">
				<input type="email" class="form-control" placeholder="請輸入Email帳號"
					id="emailInput" name="mem_account" required> <label>帳號</label>
			</div>
			<div class="form-floating">
				<input type="text" class="form-control" id="passwordInput"
					placeholder="請輸入密碼" name="mem_password" required> <label>密碼</label>
			</div>
			<div class="checkbox my-3">
				<label> <input type="checkbox" name="remeberMe"> 記住我
				</label>
			</div>
			<div class="mb-2">
				<button class="w-25 btn btn-lg btn-primary">登入</button>
			</div>
			<div>

				<button class="w-25 btn btn-lg btn-outline-dark" id="cancelBTN">取消</button>

			</div>
			<div class="mt-5" style="color: red">${message}</div>
			${message = "" }
		</form>
	</main>

	<div class="text-center m-5">
		快速登入:
		<button class="btn btn-outline-info fastLogin">Daniel</button>
		<button class="btn btn-outline-info fastLogin">Tobias</button>
		<button class="btn btn-outline-info fastLogin">Fiona</button>
		<button class="btn btn-outline-info fastLogin">Levi</button>
		<button class="btn btn-outline-info fastLogin">Olive</button>
		<button class="btn btn-outline-info fastLogin">Ella</button>
	</div>

</body>

<script type="text/javascript">
	const fastLoginBTNs = document.querySelectorAll(".fastLogin");
	const emailInput = document.querySelector("#emailInput");
	const passwordInput = document.querySelector("#passwordInput");
	const cancelBTN = document.querySelector("#cancelBTN");

	//快速登入
	fastLoginBTNs.forEach(button=>{
		button.addEventListener("click",function(){
				emailInput.value=this.innerHTML+"@gmail.com";
				passwordInput.value=this.innerHTML+1122;
			}
		)
	})

	cancelBTN.addEventListener("click",function(e){
		e.preventDefault();
		window.location.href="${root}/";
	})
	
</script>

</html>