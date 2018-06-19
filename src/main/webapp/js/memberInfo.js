$(document).ready(function () {
    // <!-- infomation -->
    //===========連結資料庫後解開註解=============
    // $('.sidebarInfo').click(function () {
    //     alert('123');
    // $.getJSON("/Food//MemberServlet", {
    //     userAccount: 'taipeitec00801@gmail.com'
    // }, function (data) {
    // $('.infomation').html(
    //     '<div class="taitle test">' +
    //     '<H2>基本資料</H2>' +
    //     '</div>' +
    //     '<div class="fotoSetting test">' +
    //     '<div class="fotoCase test">' +
    //     '<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">' +
    //     '<div class="fsWord test">' +
    //     '<a href="#">更改頭像</a>' +
    //     '</div>' +
    //     '</div>' +
    //     '<div class="nameAndEmail test">' +
    //     '<div class="memberId test">' +
    //     '<p>帳號</p>' +


    //     '<input type="text" class="form-control" placeholder="memberId" aria-describedby="sizing-addon1" value=' + data.userAccount + '>' +
    //     '</div>' +

    //     '<div class="nickName test">' +
    //     '<p>姓名</p>' +


    //     '<input type="text" class="form-control" placeholder="nickName" aria-describedby="sizing-addon1" value=' + data.nickName + '>' +
    //     '</div>' +
    //     '<div class="birthday test">' +
    //     '<p>生日</p>' +


    //     '<input type="text" class="form-control" placeholder="birthday" aria-describedby="sizing-addon1" value=' + data.birthday + '>' +
    //     '</div>' +
    //     '<div class="memIntroduce test">' +
    //     '<p>關於我 :</p>' +

    //     '<textarea name="talk" cols="25" rows="3"></textarea>' +
    //     '<hr>' +

    //     '</div>' +

    //     '<div class="btChange btn-group test" role="group">' +
    //     '<button type="button" class="btn btn-default">Update Account</button>' +
    //     '</div>' +
    //     '</div>');
    // });
    // });
    
    
    // ===========照片特效==============

   
    $('.facebox').on("click", function () {
       
        // $.facebox.settings.loadingImage = '../image/loading.gif',
		// 			$.facebox.settings.closeImage =
		// 			'../image/closelabel.png',
        
        var imgSrc = $(this).children().attr("src");
        var thisImg = $(this).parent('div');
        alert(imgSrc );
        $('.popup').html(
            '<img src="../image/arrow_left.gif" id="leftDiv" alt="...">'
            + '<img src="../image/arrow_right.gif" id="rightDiv" alt="...">'
            + '<div class="content">'
            + '</div>'
        );
        $(".content").on("click", function () {
            if ($(thisImg).is("div")) {
                // alert('true1');
                nextImg = $(thisImg).next().find('img').attr("src");
                // alert(nextImg);
                thisImg = $(thisImg).next();
                $(".content").find('img').attr("src", nextImg);
            } else {
                alert("END");
            }

        });
        $("#rightDiv").click(function () {
            // alert('true2');
            if ($(thisImg).is("div")) {
                // alert('true3');
                nextImg = $(thisImg).next().find('img').attr("src");
                if (nextImg != undefined) {
                    // alert(nextImg);
                    thisImg = $(thisImg).next();
                    $(".content").find('img').attr("src", nextImg);
                } else {
                    // alert(nextImg);
                    $('.facebox').trigger('close.facebox');
                }

            }

        });
        $("#leftDiv").on("click", function () {
            // alert('true4');
            if ($(thisImg).is("div")) {
                // alert('true5');
                nextImg = $(thisImg).prev().find('img').attr("src");

                if (nextImg != undefined) {
                    // alert(nextImg);
                    thisImg = $(thisImg).prev();
                    $(".content").find('img').attr("src", nextImg);
                } else {
                    // alert(nextImg);
                    $('.facebox').trigger('close.facebox');
                }

            }


        });
    });


});
