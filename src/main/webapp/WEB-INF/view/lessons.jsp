<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>${webName}-所有課程</title>

    <jsp:include page="${component}/common_imports.jsp" />
  </head>
  <body>
    <jsp:include page="${component}/header.jsp" />

    <main>
      <!-- <section class="py-3 text-center container">
        <div class="row py-lg-5">
          <div class="col-lg-6 col-md-8 mx-auto">
            <p class="lead text-muted">快來一起探索海底世界</p>
          </div>
        </div>
      </section> -->

      <div class="album py-5 bg-light">
        <div class="container">
          <div
            class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 text-center"
            id="lessonsHome"
          >
            <c:forEach items="${lessons}" var="l">
              <div class="col">
                <div class="card shadow-sm">
                  <img src="${l.lesson_photo}" class="w-100" />
                  <p class="card-text fs-5">${l.lesson_name }</p>

                  <div class="m-2" style="justify-content: center">
                    <p>上課地點: ${l.lesson_location }</p>
                    <p>教練: <a href="#">${l.member.mem_name }</a></p>
                    <c:if test="${loggedInMember!=null }">
                      <button
                        data-id="${l.lesson_id}"
                        class="deleteBtn"
                        style="
                          width: 100px;
                          background-color: #003c9d;
                          color: #fff;
                          border-radius: 10px;
                        "
                      >刪除
                      </button>
                      <button
                        data-id="${l.lesson_id}"
                        class="updateBtn"
                        style="
                          width: 100px;
                          background-color: #18c4b9;
                          color: #fff;
                          border-radius: 10px;
                        "
                      >修改
                      </button>
                    </c:if>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </main>

    <jsp:include page="${component}/footer.jsp" />
    <script type="text/javascript">
      const deleteBtns = document.querySelectorAll(".deleteBtn");
      const updateBtns = document.querySelectorAll(".updateBtn");

      for (let btn of deleteBtns) {
        btn.addEventListener("click", () => {
          if (confirm("確定刪除?")) {
            fetch("/DivingWorld/DeleteLessonById.do?id=" + btn.dataset["id"])
              .then((rs) => rs.text())
              .then((message) => {
                if (message == "done") {
                  btn.parentElement.parentElement.parentElement.remove();
                }
              });
          }
        });
      }

      for(let btn of updateBtns){
        btn.addEventListener("click",()=>{
          const lessonId = btn.dataset.id;
          window.location ="${root}/update_lesson?id=" +btn.dataset["id"];
        })
      }
 
    </script>
  </body>
</html>
