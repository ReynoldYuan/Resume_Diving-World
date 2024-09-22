<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <meta charset="UTF-8" />
    <title>${webName}-新增課程</title>
    
  </head>

  <jsp:include page="${component}/common_imports.jsp" />

  <body>
    <jsp:include page="${component}/header.jsp" />
    <main class="m-5">
      <div class="container">
        <div class="row">
          <div class="col text-center">
            <form action="${root}/AddLesson.do" method="POST" enctype="multipart/form-data" style="justify-content: center;">
              <div class="mb-3">
                <label
                  >課程名稱：<input
                    type="text"
                    name="lesson_name"
                    class="form-control w-100"
                /></label>
              </div>
              <div class="mb-3">
                <label
                  >課程地點：<input
                    type="text"
                    name="lesson_location"
                    class="form-control w-100"
                    min="0"
                /></label>
              </div>
              <div class="mb-3">
                <label>課程教練：</label>
                  <select
                    class="form-control w-100"
                    name="mem_id"
                  >
                    <option></option>
                    <option value="1">Daniel</option>
                    <option value="3">Fiona</option>
                    <option value="4">Levi</option>
                    </select>
                
              </div>
              <div class="mb-1">
                <label
                  >課程照片：<input
                    type="file"
                    name="lesson_photo"
                    class="form-control"
                    id="lessonPhotoInput"
                /></label>
              </div>
              <div class="mb-3">
                <img
                  src="${root}/assets/no_image.png"
                  id="previewPhotoIMG"
                  class="w-25"
                />
              </div>
              <button class="btn btn-secondary" id="cancelBTN">取消</button>
              <button class="btn btn-primary">新增</button>
            </form>
          </div>
        </div>
      </div>
    </main>
    <jsp:include page="${component}/footer.jsp" />
  </form>
  </body>

  <script type="text/javascript">
    // 選取元素
    const previewPhotoIMG = document.getElementById("previewPhotoIMG");
    const lessonPhotoInput = document.getElementById("lessonPhotoInput");
    const cancelBTN = document.getElementById("cancelBTN");

    // 預覽圖片
    lessonPhotoInput.addEventListener("change", function () {
      const file = this.files[0];
      if (file == null) {
        previewPhotoIMG.src = "${root}/assets/no_image.png";
        return;
      }
      const tempURL = URL.createObjectURL(file);
      previewPhotoIMG.src = tempURL;
    });

    // 點擊圖片觸發上傳檔案
    previewPhotoIMG.addEventListener("click", function () {
      lessonPhotoInput.click();
    });

    // 點擊取消按鈕返回上一頁
    cancelBTN.addEventListener("click", function (e) {
      e.preventDefault();
      window.history.back();
    });
  </script>
</html>
