# Diving World: 潛水課程平台

### 專案目的
我是一位剛開始學習程式設計的學生，這個專案是我的期中專題。**Diving World** 平台讓一般用戶報名潛水課程，讓教練可以建立自己的潛水課程。

**Diving World** 包含以下功能：
- **潛水課程的CRUD**

### 技術堆疊
- **後端**: JAVA JDBC、Servlet
- **前端**: JSP
- **資料庫**: Microsoft SQL Server (MSSQL)

### 安裝說明
按照以下步驟設置並運行此專案：

1. **更新 MSSQL 配置**：
   在 `ccontext.xml` 文件中，修改 MSSQL 的連接配置，包括port、用戶名和密碼。

2. **創建資料庫結構**：
   在`Initialize.java`中，程式會自動檢查您的MSSQL中是否有該專案所需的資料庫與資料表，若無，將會協助您自動建立，您無須再另外手動建立。

# 已知問題
由於專案開發時間有限，故僅實現部分功能：潛水課程的CRUD。<br>

