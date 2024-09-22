<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${webName}</title>

	<jsp:include page="${component}/common_imports.jsp"></jsp:include>


<style type="text/css">

	.wrap {
        width: 1000px;
        height: 500px;
        background-color: black;
        position: relative;
        margin: 0 auto;
        /* 溢出內容 */
        overflow: hidden;
    }
    .slide-imgs {
        position: absolute;
        margin: 0;
        padding: 0;
        list-style: none;
        display: flex;
        width: 5000px;
        left: 0;
    }

    .slide-imgs li {
        width: 1000px;
        height: 500px;
    }
    .slide-imgs li img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .pages {
        position: absolute;
        list-style: none;
        margin: 0;
        padding: 0;
        bottom: 10px;
        display: flex;
        width: 100%;
        justify-content: center;
    }
    .pages li {
        border: 1px solid #fff;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        margin: 0 5px;
    }
    .slide-arrow {
        position: absolute;
        z-index: 1;
        width: 30px;
        font-size: 36px;
        color: #fff;
        opacity: 0.6;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }
    .right {
        right: 0;
    }
    .slide-arrow:hover {
        opacity: 1;
    }
	h3{
		text-align: center;
		color: #000;
	}
</style>

</head>

<body>

	<jsp:include page="${component}/header.jsp"></jsp:include>
	<h3>Welcome to Diving World</h3>
	<main>
		<div class="container">
			<div class="wrap">
				<a class="slide-arrow" id="arrowPrev"
					><i class="fa-solid fa-chevron-left"></i
				></a>
				<a class="slide-arrow right" id="arrowNext"
					><i class="fa-solid fa-chevron-right"></i
				></a>
				<ul class="slide-imgs" id="slideImgs">
					<li><img src='assets/diving1.jpg'  alt="" /></li>
					<li><img src='assets/diving2.jpg'  alt="" /></li>
					<li><img src='assets/diving3.jpg'  alt="" /></li>
					<li><img src='assets/diving4.jpg'  alt="" /></li>
					<li><img src='assets/diving5.jpg'  alt="" /></li>
				</ul>
				<ul class="pages">
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
	</main>

	<jsp:include page="${component}/footer.jsp"></jsp:include>
<script
	src="https://kit.fontawesome.com/a260a05f7c.js"
	crossorigin="anonymous"
></script>
<script>
	window.onload = function () {
		let ul = document.getElementById("slideImgs");
		let pages = document.querySelectorAll(".pages li");
		let index = 0;
		pages[index].style.backgroundColor = "white";
		for (let i = 0; i < pages.length; i++) {
			pages[i].num = i;
			pages[i].addEventListener("mouseenter", function () {
				index = this.num;
				moveImg();
			});
		}

		let arrowNext = document.querySelector("#arrowNext");
		arrowNext.addEventListener("click", function () {
			index++;
			if(index>=pages.length){
				index=0;
			}
			moveImg();
		});

		let arrowPrev=document.querySelector('#arrowPrev');
		arrowPrev.addEventListener('click',function(){
			index--;
			if(index<0){
				index=pages.length-1;
			}
			moveImg();
		})

		function moveImg(){
		  let slideMove = index * -1000;
			ul.style.left = slideMove + "px";
			for (let j = 0; j < pages.length; j++) {
				pages[j].style.backgroundColor = "";
			}
			pages[index].style.backgroundColor = "white";
		}

		// 定時器
		setInterval(autoImg,2000);
		function autoImg(){
			index++;
			if(index>=pages.length){
				index=0
			}
			moveImg();
		}
	};
</script>
</body>

</html>

