<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<input name="uploadFiles" type="file" multiple>
<button class="uploadBtn">Upload</button>



<div class="uploadResult">

</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>


<script>

    $('.uploadBtn').click(function( ) {

        var formData = new FormData();

        var inputFile = $("input[type='file']");

        var files = inputFile[0].files;

        for (var i = 0; i < files.length; i++) {
            console.log(files[i]);
            formData.append("uploadFiles", files[i]);
        }

        //실제 업로드 부분
        //upload ajax
        $.ajax({
            url: '/CUpload/uploadAjax',
            processData: false, 
            contentType: false,
            data: formData,
            type: 'POST',
            dataType:'json',
            success: function(result){
                console.log(result);
                //나중에 화면 처리
                // 추가
                showUploadedImages(result);
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log(textStatus);
            }

        }); //$.ajax
    }); //end click


    
    function showUploadedImages(arr){

        console.log(arr);

        var divArea = $(".uploadResult");

        var str = "";

        for(var i = 0; i < arr.length; i++){
            str += "<div>";
            str += "<img src='/CUpload/display?fileName="+arr[i].thumbnailURL+"'>";
            str += "<button class='removeBtn' data-name='"+arr[i].imageURL+"'>REMOVE</button>"
            str += "<div>"
        }
        divArea.append(str);

    }

    $(".uploadResult").on("click", ".removeBtn", function(e){
		
        var target = $(this);
        var fileName = target.data("name");
        var targetDiv = $(this).closest("div");
        var targetImg = $(this).closest("img");

        console.log(fileName);

        $.post('/CUpload/removeFile', {fileName: fileName}, function(result){
            console.log(result);
            if(result === true){
                targetDiv.remove();
            }else{
            	console.log("이거 왜 팔스 뜨냐 ㅡㅡ" + targetDiv)
            }
        } )

    });

</script>


</body>
</html>