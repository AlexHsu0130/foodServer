$(document).ready(function () {
    // <!-- infomation -->
    // $('.sidebarInfo').click(function () {
    //     alert('123');
        // $.getJSON("/Food//MemberServlet", {
        //     userAccount: 'taipeitec00801@gmail.com'
        // }, function (data) {
            $('.infomation').html(
                '<div class="taitle test">' +
                '<H2>基本資料</H2>' +
                '</div>' +
                '<div class="fotoSetting test">' +
                '<div class="fotoCase test">' +
                '<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">' +
                '<div class="fsWord test">' +
                '<a href="#">更改頭像</a>' +
                '</div>' +
                '</div>' +
                '<div class="nameAndEmail test">' +
                '<div class="memberId test">' +
                '<p>帳號</p>' +


                '<input type="text" class="form-control" placeholder="memberId" aria-describedby="sizing-addon1" value=' + data.userAccount + '>' +
                '</div>' +

                '<div class="nickName test">' +
                '<p>姓名</p>' +


                '<input type="text" class="form-control" placeholder="nickName" aria-describedby="sizing-addon1" value=' + data.nickName + '>' +
                '</div>' +
                '<div class="birthday test">' +
                '<p>生日</p>' +


                '<input type="text" class="form-control" placeholder="birthday" aria-describedby="sizing-addon1" value=' + data.birthday + '>' +
                '</div>' +
                '<div class="memIntroduce test">' +
                '<p>關於我 :</p>' +

                '<textarea name="talk" cols="25" rows="3"></textarea>' +
                '<hr>' +

                '</div>' +

                '<div class="btChange btn-group test" role="group">' +
					'<button type="button" class="btn btn-default">Update Account</button>' +
				'</div>' +
                '</div>');
            // });
    // });



});
