# NTUST_Movie_Theater

#組員: B11009003 江冠緯 B11009061 曾慶倫

#功能介紹:
     我們的app是一個電影訂票系統，從首頁開始可以使用左上角的menu進行訂票，menu包含 movielist和orderticket，
     movielist是包含我們索取api的電影資訊以及內容等， 選自己想要觀賞的電影，並使用+/-按鈕點按人數(最多4人)，選
     好人數後點按訂票即可完成訂票，且資訊會存入orderticket中，這時我們點選orderticket，就可以看到我們所訂到的
     電影，還可點選詳細資料了解實際內容以及取消訂單，訂票紀錄也是存在database裡，所以就算app終止，再次回到app時
     還能保有資料。而一開始我們有設定權限，原本是想要讓app能夠在使用者訂完票後，詢問可否回傳訂票成功的訊息給使用
     者，但這部份我們並沒有完全做出來，所以僅有使用權限的產生而已。

#備註1:
     因為我們使用的api並沒有提供圖片，而當時發現其他api都要花費一些金額，所以我們只能用網上隨意抓取幾張圖片，給圖
     片編號，指派圖片給電影。
#備註2:
     在測試訂票紀錄的取消訂票功能時，發現有刪除資料暫存在畫面的問題，可能是執行取消訂票動作未完成，而畫面就直接跳到下
     一個Fragment，所以recyclerView讀到的資料是刪除訂票前的資料，我們有嘗試在刪除資料的corutine加delay，但還是沒辦
     法解決這個問題，目前折衷的方法是重新點擊選單一次，這樣recyclerView就能讀到正確的資料。
#網址: 
     api網址:https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=8